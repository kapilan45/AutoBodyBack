package org.csid.autobody.dto;

public class AnnonceDto {

    private Long id;
    private int placeNumber;
    private int maxSpeed;
    private boolean reinforcedClutch;
    private int horsePower;
    private int fiscalHorsePower;
    private int stage;
    private String options;
    private MakeDto make;
    private LocalisationDto localisation;

    public AnnonceDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean isReinforcedClutch() {
        return reinforcedClutch;
    }

    public void setReinforcedClutch(boolean reinforcedClutch) {
        this.reinforcedClutch = reinforcedClutch;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getFiscalHorsePower() {
        return fiscalHorsePower;
    }

    public void setFiscalHorsePower(int fiscalHorsePower) {
        this.fiscalHorsePower = fiscalHorsePower;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public MakeDto getMake() {
        return make;
    }

    public void setMake(MakeDto make) {
        this.make = make;
    }

    public LocalisationDto getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationDto localisation) {
        this.localisation = localisation;
    }
}
