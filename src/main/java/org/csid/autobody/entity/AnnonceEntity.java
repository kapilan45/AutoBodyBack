package org.csid.autobody.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AnnonceEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "maxSpeed")
    private int maxSpeed;

    @Column(name = "reinforcedClutch")
    private boolean reinforcedClutch;

    @Column(name = "horsePower")
    private int horsePower;

    @Column (name = "horsePowerSinceTheLatestModification")
    private int horsePowerSinceTheLatestModification;

    @Column (name = "numberOfSeats")
    private int numberOfSeats;

    @Column(name = "fiscalHorsePower")
    private int fiscalHorsePower;

    @Column(name = "year")
    private Date year;

    @Column(name = "mileage")
    private Long mileage;

    @Column (name = "mileageSince1stModification")
    private int mileageSince1stModification;
    
    // Version/Finition
    @Column (name = "trim")
    private String trim;

    @Column(name = "stage")
    private int stage;

    @Lob
    @Column(name = "options")
    private String options;

    @Column(name = "gearbox")
    private String gearbox;

    @Column(name = "outSideColor")
    private String outSideColor;

    @Column(name = "inSideColor")
    private String inSideColor;

    @Column (name = "fuelEconomySinceTheLatestModification")
    private String fuelEconomySinceTheLatestModification;

    @Column (name = "fuelEconomy")
    private String fuelEconomy;

    public String getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(String fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
    }

    // Type de transmission (4 roues motrices/ Propulsion/ Traction)
    @Column (name = "driveType")
    private String driveType;

    //préparateur
    @Column (name = "highPerformanceTuningCompany")
    private String highPerformanceTuningCompany;

    // couple moteur
    @Column (name = "torque")
    private int torque;

    @Column (name = "torqueSinceTheLatestModification")
    private int torqueSinceTheLatestModification;

    // échappement
    @Column (name = "exaust")
    private String exaust;

    @Column (name = "turbo")
    private String turbo;

    @Column (name = "intercooler")
    private String intercooler;

    @Column (name = "airAdmission")
    private String airAdmission;

    @Column (name = "dumpValve")
    private String dumpValve;

    @Column (name = "airFilter")
    private String airFilter;

    @Column(name = "firstHand")
    private boolean firstHand;

    @Column(name = "price")
    private double price;

    @Column(name = "numberOfDoor")
    private int numberOfDoor;

    @Column(name = "energy")
    private String energy;

    @Column(name = "publishedDate")
    private Date publishedDate;

    @Column (name = "announceStatus")
    private int announceStatus;

    @Column(name = "localisation")
    private String localisation;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY)
    private List<ImageEntity> images;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private ModelEntity model;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private MakeEntity make;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    public AnnonceEntity() { }

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

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
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

    public boolean isFirstHand() {
        return firstHand;
    }

    public void setFirstHand(boolean firstHand) {
        this.firstHand = firstHand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getMilage() {
        return mileage;
    }

    public void setMilage(Long mileage) {
        this.mileage = mileage;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getNumberOfDoor() {
        return numberOfDoor;
    }

    public void setNumberOfDoor(int numberOfDoor) {
        this.numberOfDoor = numberOfDoor;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public MakeEntity getMake() {
        return make;
    }

    public void setMake(MakeEntity make) {
        this.make = make;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getHorsePowerSinceTheLatestModification() {
        return horsePowerSinceTheLatestModification;
    }

    public void setHorsePowerSinceTheLatestModification(int horsePowerSinceTheLatestModification) {
        this.horsePowerSinceTheLatestModification = horsePowerSinceTheLatestModification;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
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

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public int getAnnounceStatus() {
        return announceStatus;
    }

    public void setAnnounceStatus(int announceStatus) {
        this.announceStatus = announceStatus;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }


}
