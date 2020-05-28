package org.csid.autobody.dto;

import java.util.Date;
import java.util.List;

public class AnnonceDto {

    private Long id;
    private int maxSpeed;
    private boolean reinforcedClutch;
    private int horsePower;
    private int fiscalHorsePower;
    private int stage;
    private int numberOfDoor;
    private int numberOfPlaces;
    private double prix;
    private Long kilometrage;
    private String energy;
    private String gearbox;
    private String outSideColor;
    private String inSideColor;
    private String numberOfOwner;
    private String firstHand;
    private String euroNorme;
    private int co2;
    private Date annee;
    private ModelDto model;
    private CategoryDto category;
    private String options;
    private MakeDto make;
    private LocalisationDto localisation;
    private List<ImageDto> images;
    private Date publishedDate;

    public AnnonceDto(){}

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Long kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public int getNumberOfDoor() {
        return numberOfDoor;
    }

    public void setNumberOfDoor(int numberOfDoor) {
        this.numberOfDoor = numberOfDoor;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public String getOutSideColor() {
        return outSideColor;
    }

    public void setOutSideColor(String outSideColor) {
        this.outSideColor = outSideColor;
    }

    public String getInSideColor() {
        return inSideColor;
    }

    public void setInSideColor(String inSideColor) {
        this.inSideColor = inSideColor;
    }

    public String getNumberOfOwner() {
        return numberOfOwner;
    }

    public void setNumberOfOwner(String numberOfOwner) {
        this.numberOfOwner = numberOfOwner;
    }

    public String getFirstHand() {
        return firstHand;
    }

    public void setFirstHand(String firstHand) {
        this.firstHand = firstHand;
    }

    public String getEuroNorme() {
        return euroNorme;
    }

    public void setEuroNorme(String euroNorme) {
        this.euroNorme = euroNorme;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public Date getAnnee() {
        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
