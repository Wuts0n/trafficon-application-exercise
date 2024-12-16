package com.example.parking.controller;

import com.example.parking.model.ParkingLotModel;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsDTO;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsFeaturesDTO;
import com.example.parking.repository.ParkingLotRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.geo.Point;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// activate automatic startup and stop of containers
@Testcontainers
// JPA drop and create table, good for testing
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class ParkingLotControllerTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    // static, all tests share this postgres container
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine" // "kartoza/postgis:16-3"
    );

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        parkingLotRepository.deleteAll();

        ParkingLotModel lot1 = new ParkingLotModel();
        lot1.setAdresse("at home");
        lot1.setFax("54667/3465");
        lot1.setBezeichnung("Parkplatz Immervoll");
        lot1.setId(5487L);
        lot1.setFreiePlaetzeAbsolut(574855);
        lot1.setFreiePlaetzeProzent(15D);
        lot1.setFid("parkplatz.5487");
        lot1.setPoint(new Point(13.03783547D, 47.79744591D));

        ParkingLotModel lot2 = new ParkingLotModel();
        lot2.setAdresse("Salzbuaich");
        lot2.setFax("54667/34633333333335");
        lot2.setBezeichnung("Parkplatz Fisch");
        lot2.setId(55555L);
        lot2.setFreiePlaetzeAbsolut(5);
        lot2.setFreiePlaetzeProzent(80.95457D);
        lot2.setFid("parkplatz.55555");
        lot2.setPoint(new Point(13.04368224D, 47.81539145D));

        ParkingLotModel lot3 = new ParkingLotModel();
        lot3.setAdresse("Bambäich");
        lot3.setFax("54667/3463333223335");
        lot3.setBezeichnung("An der Straße");
        lot3.setId(2L);
        lot3.setFreiePlaetzeAbsolut(57);
        lot3.setFreiePlaetzeProzent(44D);
        lot3.setFid("parkplatz.2");
        lot3.setPoint(new Point(13.04368224D, 47.81539145D));

        parkingLotRepository.saveAll(List.of(lot1, lot2, lot3));
    }

    @Test
    void testFindAll() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parkingLots")
                .then()
                .statusCode(200)    // expecting HTTP 200 OK
                .contentType(ContentType.JSON) // expecting JSON response content
                .body(".", hasSize(3));
    }

    @Test
    void testFindById() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parkingLots/55555")
                .then()
                .statusCode(200)    // expecting HTTP 200 OK
                .contentType(ContentType.JSON) // expecting JSON response content
                .body("bezeichnung", equalTo("Parkplatz Fisch"),
                        "fid", equalTo("parkplatz.55555"),
                        "freiePlaetzeAbsolut", is(5),
                        "freiePlaetzeProzent", equalTo(80.95457F));
    }

    @Test
    public void testDeleteById() {
        Long id = 2L; // replace with a valid ID
        given()
                .pathParam("id", id)
                .when()
                .delete("/parkingLots/{id}")
                .then()
                .statusCode(204); // expecting HTTP 204 No Content
    }

    @Test
    public void testCreate() {

        given()
                .contentType(ContentType.JSON)
                .body("{ \"id\": 3, \"freiePlaetzeAbsolut\": 66, \"freiePlaetzeProzent\": 30, \"bezeichnung\": \"Bezeichnung\" }")
                .when()
                .post("/parkingLots")
                .then()
                .statusCode(201) // expecting HTTP 201 Created
                .contentType(ContentType.JSON); // expecting JSON response content

        // check that parking lot was created
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parkingLots")
                .then()
                .statusCode(200)    // expecting HTTP 200 OK
                .contentType(ContentType.JSON) // expecting JSON response content
                .body(".", hasSize(4));

        // check that creation fails without id
        given()
                .contentType(ContentType.JSON)
                .body("{ \"freiePlaetzeAbsolut\": 6677777, \"freiePlaetzeProzent\": 0.11, \"bezeichnung\": \"Bezeichnung 2\" }")
                .when()
                .post("/parkingLots")
                .then()
                .statusCode(422) // expecting HTTP 422 Unprocessable Entity
                .contentType(ContentType.JSON); // expecting JSON response content
    }

    @Test
    public void testUpdate() {

        ParkingLotModel parkingLotBefore = parkingLotRepository.findById(5487L).get();

        Long id = parkingLotBefore.getId();

        parkingLotBefore.setFax("7684/485784");
        parkingLotBefore.setFreiePlaetzeStatus(2);
        parkingLotBefore.setFreiePlaetzeAbsolut(777);

        given()
                .contentType(ContentType.JSON)
                .body(parkingLotBefore)
                .when()
                .put("/parkingLots")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        // get the updated book
        ParkingLotModel parkingLotAfter = parkingLotRepository.findById(id).orElseThrow();

        assertEquals(id, parkingLotAfter.getId());
        assertEquals("7684/485784", parkingLotAfter.getFax());
        assertEquals(2, parkingLotAfter.getFreiePlaetzeStatus());
        assertEquals(777, parkingLotAfter.getFreiePlaetzeAbsolut());
    }

    @Test
    public void testImportWfsJson() {

        ParkingLotWfsDTO wfs1 = new ParkingLotWfsDTO();
        wfs1.setType("FeatureCollectiones"); // wrong type
        wfs1.setFeatures(null);
        wfs1.setNumberMatched(0L);
        wfs1.setNumberReturned(0L);
        wfs1.setTotalFeatures(0L);

        given()
                .contentType(ContentType.JSON)
                .body(wfs1)
                .when()
                .post("/parkingLots/wfs")
                .then()
                .statusCode(405) // wrong request type (POST, should be PUT)
                .contentType(ContentType.JSON);

        given()
                .contentType(ContentType.JSON)
                .body(wfs1)
                .when()
                .put("/parkingLots/wfs")
                .then()
                .statusCode(422) // because wrong type
                .contentType(ContentType.JSON);


        ParkingLotWfsDTO wfs2 = new ParkingLotWfsDTO();
        wfs2.setType("FeatureCollection");
        ParkingLotWfsFeaturesDTO features2 = new ParkingLotWfsFeaturesDTO();
        features2.setType("Feet"); // wrong type again
        wfs2.setFeatures(List.of(features2).toArray(new ParkingLotWfsFeaturesDTO[1]));

        given()
                .contentType(ContentType.JSON)
                .body(wfs2)
                .when()
                .put("/parkingLots/wfs")
                .then()
                .statusCode(422) // because wrong type
                .contentType(ContentType.JSON);

        given()
                .contentType(ContentType.JSON)
                .body(SalzburgParking.DUMMY_WFS_JSON)
                .when()
                .put("/parkingLots/wfs")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testFindSumByIds() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parkingLots/sum")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(574917));

        ParkingLotModel lot4 = new ParkingLotModel();
        lot4.setAdresse("Ouagadougou");
        lot4.setFax("773");
        lot4.setBezeichnung("Mogho Naba Palace");
        lot4.setId(3L);
        lot4.setFreiePlaetzeAbsolut(14);
        lot4.setFreiePlaetzeProzent(100D);
        lot4.setFid("parkplatz.3");
        lot4.setAdresse("Bilbalogho, Ouagadougou, Burkina Faso");
        lot4.setPoint(new Point(12.360801071578313D, -1.5260550566737625D));
        parkingLotRepository.save(lot4);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parkingLots/sum")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(574931));
    }

}
