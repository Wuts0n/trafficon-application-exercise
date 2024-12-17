package com.example.parking.model.parkingLotWfsDTO;

public class ParkingLotWfsDTO {
    private String type;
    private ParkingLotWfsFeaturesDTO[] features;
    private Long totalFeatures;
    private Long numberMatched;
    private Long numberReturned;
    private String timeStamp;
    private ParkingLotWfsCrsDTO crs;


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

    public Long getTotalFeatures() {
        return totalFeatures;
    }

    public void setTotalFeatures(Long totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public Long getNumberMatched() {
        return numberMatched;
    }

    public void setNumberMatched(Long numberMatched) {
        this.numberMatched = numberMatched;
    }

    public Long getNumberReturned() {
        return numberReturned;
    }

    public void setNumberReturned(Long numberReturned) {
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
