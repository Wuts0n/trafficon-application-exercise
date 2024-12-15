package com.example.parking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.geo.Point;

@Entity
public class ParkingLotModel {
    @Id
    private Long id;
    private String fid;
    private Point point;
    private String bezeichnung;
    private String kapazitaet;
    private String typ;
    private String ansprechpartnerName;
    private String belegungTendenz;
    private String anmerkungen;
    private String freiePlaetze;
    private String tarif;
    private String oeffnungszeiten;
    private String ansprechpartnerUrl;
    private String datenquelleUrl;
    private String belegungAktualisiert;
    private String email;
    private String adresse;
    private String datenquelleName;
    private String telefon;
    private String url;
    private Integer freiePlaetzeStatus;
    private String fax;

    // for JPA only, no use
    public ParkingLotModel() {
    }

    public ParkingLotModel(String fid, Point point, Long id, String bezeichnung, String kapazitaet, String typ, String ansprechpartnerName, String belegungTendenz, String anmerkungen, String freiePlaetze, String tarif, String oeffnungszeiten, String ansprechpartnerUrl, String datenquelleUrl, String belegungAktualisiert, String email, String adresse, String datenquelleName, String telefon, String url, Integer freiePlaetzeStatus, String fax) {
        this.fid = fid;
        this.point = point;
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.kapazitaet = kapazitaet;
        this.typ = typ;
        this.ansprechpartnerName = ansprechpartnerName;
        this.belegungTendenz = belegungTendenz;
        this.anmerkungen = anmerkungen;
        this.freiePlaetze = freiePlaetze;
        this.tarif = tarif;
        this.oeffnungszeiten = oeffnungszeiten;
        this.ansprechpartnerUrl = ansprechpartnerUrl;
        this.datenquelleUrl = datenquelleUrl;
        this.belegungAktualisiert = belegungAktualisiert;
        this.email = email;
        this.adresse = adresse;
        this.datenquelleName = datenquelleName;
        this.telefon = telefon;
        this.url = url;
        this.freiePlaetzeStatus = freiePlaetzeStatus;
        this.fax = fax;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(String kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getAnsprechpartnerName() {
        return ansprechpartnerName;
    }

    public void setAnsprechpartnerName(String ansprechpartnerName) {
        this.ansprechpartnerName = ansprechpartnerName;
    }

    public String getBelegungTendenz() {
        return belegungTendenz;
    }

    public void setBelegungTendenz(String belegungTendenz) {
        this.belegungTendenz = belegungTendenz;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
    }

    public String getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(String freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getOeffnungszeiten() {
        return oeffnungszeiten;
    }

    public void setOeffnungszeiten(String oeffnungszeiten) {
        this.oeffnungszeiten = oeffnungszeiten;
    }

    public String getAnsprechpartnerUrl() {
        return ansprechpartnerUrl;
    }

    public void setAnsprechpartnerUrl(String ansprechpartnerUrl) {
        this.ansprechpartnerUrl = ansprechpartnerUrl;
    }

    public String getDatenquelleUrl() {
        return datenquelleUrl;
    }

    public void setDatenquelleUrl(String datenquelleUrl) {
        this.datenquelleUrl = datenquelleUrl;
    }

    public String getBelegungAktualisiert() {
        return belegungAktualisiert;
    }

    public void setBelegungAktualisiert(String belegungAktualisiert) {
        this.belegungAktualisiert = belegungAktualisiert;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDatenquelleName() {
        return datenquelleName;
    }

    public void setDatenquelleName(String datenquelleName) {
        this.datenquelleName = datenquelleName;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFreiePlaetzeStatus() {
        return freiePlaetzeStatus;
    }

    public void setFreiePlaetzeStatus(Integer freiePlaetzeStatus) {
        this.freiePlaetzeStatus = freiePlaetzeStatus;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
