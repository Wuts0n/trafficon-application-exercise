package com.example.parking.service;

import com.example.exception.BadRequestException;
import com.example.parking.model.ParsedFreiePlaetze;
import com.example.parking.model.SumResponse;
import com.example.parking.model.ParkingLotModel;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsDTO;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsFeaturesDTO;
import com.example.parking.model.parkingLotWfsDTO.ParkingLotWfsFeaturesPropertiesDTO;
import com.example.parking.repository.ParkingLotRepository;
import com.example.parking.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public List<ParkingLotModel> findAll() {
        return parkingLotRepository.findAll();
    }

    public Optional<ParkingLotModel> findById(Long id) {
        return parkingLotRepository.findById(id);
    }

    public ParkingLotModel save(ParkingLotModel parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public void deleteById(Long id) {
        parkingLotRepository.deleteById(id);
    }

    /**
     * Converts idsParam to a list of Long values.
     * Then, forwards this list to the ParkingLotRepository.
     *
     * @param idsParam a string of integers separated by ",". E.g. "A,B,C,…"
     * @return the result of the ParkingLotRepository
     * @throws BadRequestException if the idsParam string is not properly delimited by "," or if a value is not an integer
     */
    public Long findSumByIds(List<Long> ids) throws BadRequestException {
        Long sum;
        if (ids == null || ids.isEmpty()) {
            sum = parkingLotRepository.findSum();
        } else {
            sum = parkingLotRepository.findSumByIds(ids);
        }
        return sum;
    }

    /**
     * Takes a wfs json that is formatted in the very specific way that Salzburg formats it and converts it to an entity to be stored in this database
     *
     * @param wfs wfs json that is formatted in the very specific way that Salzburg formats it
     * @return a list of all saved entities
     * @throws BadRequestException if the wfs json is not formatted in the very specific way that Salzburg formats it
     */
    public List<ParkingLotModel> save(ParkingLotWfsDTO wfs) throws BadRequestException {
        if (wfs.getType() == null) {
            throw new BadRequestException("WFS Json is not properly formatted: Missing Type");
        }
        if (!wfs.getType().equals("FeatureCollection")) {
            throw new BadRequestException("WFS Json is not properly formatted: Type is not FeatureCollection");
        }
        List<ParkingLotModel> parkingLotList = new ArrayList<>();
        if (wfs.getFeatures() != null) {
            for (ParkingLotWfsFeaturesDTO features : wfs.getFeatures()) {
                ParkingLotModel parkingLot = new ParkingLotModel();
                if (features.getType() == null) {
                    throw new BadRequestException("WFS Json is not properly formatted: Missing Type");
                }
                if (!features.getType().equals("Feature")) {
                    throw new BadRequestException("WFS Json is not properly formatted: Type of Feature is not Feature");
                }
                parkingLot.setFid(features.getId());
                if (features.getGeometry() != null) {
                    Point point = new Point(features.getGeometry().getCoordinates()[0], features.getGeometry().getCoordinates()[1]);
                    parkingLot.setPoint(point);
                }
                ParkingLotWfsFeaturesPropertiesDTO properties = features.getProperties();
                if (properties != null) {
                    parkingLot.setId(features.getProperties().getId());
                    parkingLot.setId(properties.getId());
                    parkingLot.setBezeichnung(properties.getBezeichnung());
                    parkingLot.setKapazitaet(properties.getKapazitaet());
                    parkingLot.setTyp(properties.getTyp());
                    parkingLot.setAnsprechpartnerName(properties.getAnsprechpartnerName());
                    parkingLot.setBelegungTendenz(properties.getBelegungTendenz());
                    parkingLot.setAnmerkungen(properties.getAnmerkungen());
                    ParsedFreiePlaetze parsedFreiePlaetze;
                    if (properties.getFreiePlaetze() == null || properties.getFreiePlaetze().isEmpty() || properties.getFreiePlaetze().equals("nicht bekannt") || properties.getFreiePlaetze().equals("derzeit nicht bekannt")) {
                        parsedFreiePlaetze = new ParsedFreiePlaetze();
                        parsedFreiePlaetze.setAbsolut(null);
                        parsedFreiePlaetze.setProzent(null);
                    } else {
                        try {
                            parsedFreiePlaetze = Parser.parseFreiePlaetze(properties.getFreiePlaetze());
                        } catch (NumberFormatException ex) {
                            throw new BadRequestException("WFS Json is not properly formatted: FREIE_PLAETZE is not formatted as \"XX (YY%)\"");
                        }
                    }
                    parkingLot.setFreiePlaetzeAbsolut(parsedFreiePlaetze.getAbsolut());
                    parkingLot.setFreiePlaetzeProzent(parsedFreiePlaetze.getProzent());
                    parkingLot.setTarif(properties.getTarif());
                    parkingLot.setOeffnungszeiten(properties.getOeffnungszeiten());
                    parkingLot.setAnsprechpartnerUrl(properties.getAnsprechpartnerUrl());
                    parkingLot.setDatenquelleUrl(properties.getDatenquelleUrl());
                    parkingLot.setBelegungAktualisiert(properties.getBelegungAktualisiert());
                    parkingLot.setEmail(properties.getEmail());
                    parkingLot.setAdresse(properties.getAddresse());
                    parkingLot.setDatenquelleName(properties.getDatenquelleName());
                    parkingLot.setTelefon(properties.getTelefon());
                    parkingLot.setUrl(properties.getUrl());
                    parkingLot.setFreiePlaetzeStatus(properties.getFreiePlaetzeStatus());
                    parkingLot.setFax(properties.getFax());
                }
                parkingLotList.add(parkingLot);
            }
        }
        return parkingLotRepository.saveAll(parkingLotList);
    }
}
