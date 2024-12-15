package com.example.parking.model.parkingLotWfsDTO;

public class ParkingLotWfsFeaturesGeometryDTO {
    private String type;
    private Double[] coordinates;
    private String geometry_name;

    public ParkingLotWfsFeaturesGeometryDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getGeometry_name() {
        return geometry_name;
    }

    public void setGeometry_name(String geometry_name) {
        this.geometry_name = geometry_name;
    }
}
