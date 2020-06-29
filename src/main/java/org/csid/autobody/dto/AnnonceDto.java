package org.csid.autobody.dto;

import java.util.Date;
import java.util.List;

public class AnnonceDto {

    private Long id;
    private int maxSpeed;
    private boolean reinforcedClutch;
    private boolean firstHand;

    private int horsePower;
    private int horsePowerSinceTheLatestModification;
    private int fiscalHorsePower;
    private int stage;
    private int numberOfDoor;
    private int numberOfSeats;
    private double price;
    private Long mileage;
    private int mileageSince1stModification;
    private String trim;
    private String energy;
    private String fuelEconomy;
    private String fuelEconomySinceTheLatestModification;
    private String driveType;
    private String highPerformanceTuningCompany;
    private int torque;
    private int torqueSinceTheLatestModification;
    private String exaust;
    private String turbo;
    private String intercooler;
    private String airAdmission;
    private String dumpValve;
    private String airFilter;
    private int announceStatus;
    private String gearbox;
    private String outSideColor;
    private String inSideColor;
    private String numberOfOwner;
    private Date year;
    private String model;
    private String category;
    private String options;
    private String make;
    private String localisation;
    private List<UploadDto> images;
    private Date publishedDate;
    private UserDto user;

    public AnnonceDto(){}



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<UploadDto> getImages() {
        return images;
    }

    public void setImages(List<UploadDto> images) {
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeatss(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getHorsePowerSinceTheLatestModification() {
        return horsePowerSinceTheLatestModification;
    }

    public void setHorsePowerSinceTheLatestModification(int horsePowerSinceTheLatestModification) {
        this.horsePowerSinceTheLatestModification = horsePowerSinceTheLatestModification;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public int getMileageSince1stModification() {
        return mileageSince1stModification;
    }

    public void setMileageSince1stModification(int mileageSince1stModification) {
        this.mileageSince1stModification = mileageSince1stModification;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(String fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
    }

    public String getFuelEconomySinceTheLatestModification() {
        return fuelEconomySinceTheLatestModification;
    }

    public void setFuelEconomySinceTheLatestModification(String fuelEconomySinceTheLatestModification) {
        this.fuelEconomySinceTheLatestModification = fuelEconomySinceTheLatestModification;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getHighPerformanceTuningCompany() {
        return highPerformanceTuningCompany;
    }

    public void setHighPerformanceTuningCompany(String highPerformanceTuningCompany) {
        this.highPerformanceTuningCompany = highPerformanceTuningCompany;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getTorqueSinceTheLatestModification() {
        return torqueSinceTheLatestModification;
    }

    public void setTorqueSinceTheLatestModification(int torqueSinceTheLatestModification) {
        this.torqueSinceTheLatestModification = torqueSinceTheLatestModification;
    }

    public String getExaust() {
        return exaust;
    }

    public void setExaust(String exaust) {
        this.exaust = exaust;
    }

    public String getTurbo() {
        return turbo;
    }

    public void setTurbo(String turbo) {
        this.turbo = turbo;
    }

    public String getIntercooler() {
        return intercooler;
    }

    public void setIntercooler(String intercooler) {
        this.intercooler = intercooler;
    }

    public String getAirAdmission() {
        return airAdmission;
    }

    public void setAirAdmission(String airAdmission) {
        this.airAdmission = airAdmission;
    }

    public String getDumpValve() {
        return dumpValve;
    }

    public void setDumpValve(String dumpValve) {
        this.dumpValve = dumpValve;
    }

    public String getAirFilter() {
        return airFilter;
    }

    public void setAirFilter(String airFilter) {
        this.airFilter = airFilter;
    }

    public int getAnnounceStatus() {
        return announceStatus;
    }

    public void setAnnounceStatus(int announceStatus) {
        this.announceStatus = announceStatus;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isFirstHand() {
        return firstHand;
    }

    public void setFirstHand(boolean firstHand) {
        this.firstHand = firstHand;
    }

}
