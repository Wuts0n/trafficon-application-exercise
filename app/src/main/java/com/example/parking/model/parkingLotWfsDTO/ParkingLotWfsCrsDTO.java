package com.example.parking.model.parkingLotWfsDTO;

public class ParkingLotWfsCrsDTO {
    private String type;
    private ParkingLotWfsCrsPropertiesDTO properties;

    public ParkingLotWfsCrsDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ParkingLotWfsCrsPropertiesDTO getProperties() {
        return properties;
    }

    public void setProperties(ParkingLotWfsCrsPropertiesDTO properties) {
        this.properties = properties;
    }
}
