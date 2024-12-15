package com.example.parking.model.parkingLotWfsDTO;

public class ParkingLotWfsDTO {
    private String type;
    private ParkingLotWfsFeaturesDTO[] features;
    private String totalFeatures;
    private String numberMatched;
    private String numberReturned;
    private String timeStamp;
    private ParkingLotWfsCrsDTO crs;

    public ParkingLotWfsDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ParkingLotWfsFeaturesDTO[] getFeatures() {
        return features;
    }

    public void setFeatures(ParkingLotWfsFeaturesDTO[] features) {
        this.features = features;
    }

    public String getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(String totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public String getNumberMatched() {
        return numberMatched;
    }

    public void setNumberMatched(String numberMatched) {
        this.numberMatched = numberMatched;
    }

    public String getNumberReturned() {
        return numberReturned;
    }

    public void setNumberReturned(String numberReturned) {
        this.numberReturned = numberReturned;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ParkingLotWfsCrsDTO getCrs() {
        return crs;
    }

    public void setCrs(ParkingLotWfsCrsDTO crs) {
        this.crs = crs;
    }
}
