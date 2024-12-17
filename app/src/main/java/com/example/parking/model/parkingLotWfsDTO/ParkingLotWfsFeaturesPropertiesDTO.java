package com.example.parking.model.parkingLotWfsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParkingLotWfsFeaturesPropertiesDTO {
    @JsonProperty("ID")
    private Long id;
    @JsonProperty("BEZEICHNUNG")
    private String bezeichnung;
    @JsonProperty("KAPAZITAET")
    private Integer kapazitaet;
    @JsonProperty("TYP")
    private String typ;
    @JsonProperty("ANSPRECHPARTNER_NAME")
    private String ansprechpartnerName;
    @JsonProperty("BELEGUNG_TENDENZ")
    private String belegungTendenz;
    @JsonProperty("ANMERKUNGEN")
    private String anmerkungen;
    @JsonProperty("FREIE_PLAETZE")
    private String freiePlaetze;
    @JsonProperty("TARIF")
    private String tarif;
    @JsonProperty("OEFFNUNGSZEITEN")
    private String oeffnungszeiten;
    @JsonProperty("ANSPRECHPARTNER_URL")
    private String ansprechpartnerUrl;
    @JsonProperty("DATENQUELLE_URL")
    private String datenquelleUrl;
    @JsonProperty("BELEGUNG_AKTUALISIERT")
    private String belegungAktualisiert;
    @JsonProperty("EMAIL")
    private String email;
    @JsonProperty("ADDRESSE")
    private String addresse;
    @JsonProperty("DATENQUELLE_NAME")
    private String datenquelleName;
    @JsonProperty("TELEFON")
    private String telefon;
    @JsonProperty("URL")
    private String url;
    @JsonProperty("FREIE_PLAETZE_STATUS")
    private Integer freiePlaetzeStatus;
    @JsonProperty("FAX")
    private String fax;

    public ParkingLotWfsFeaturesPropertiesDTO() {
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

    public Integer getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(Integer kapazitaet) {
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

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
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
