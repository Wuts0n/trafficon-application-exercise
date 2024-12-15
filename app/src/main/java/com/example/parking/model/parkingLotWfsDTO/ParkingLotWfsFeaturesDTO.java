package com.example.parking.model.parkingLotWfsDTO;

public class ParkingLotWfsFeaturesDTO {
    private String type;
    private String id;
    private ParkingLotWfsFeaturesGeometryDTO geometry;
    private ParkingLotWfsFeaturesPropertiesDTO properties;

    public ParkingLotWfsFeaturesDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParkingLotWfsFeaturesGeometryDTO getGeometry() {
        return geometry;
    }

    public void setGeometry(ParkingLotWfsFeaturesGeometryDTO geometry) {
        this.geometry = geometry;
    }

    public ParkingLotWfsFeaturesPropertiesDTO getProperties() {
        return properties;
    }

    public void setProperties(ParkingLotWfsFeaturesPropertiesDTO properties) {
        this.properties = properties;
    }
}
