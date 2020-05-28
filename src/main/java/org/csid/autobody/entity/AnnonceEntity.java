package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Annonces")
public class AnnonceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maxSpeed")
    private int maxSpeed;

    @Column(name = "reinforcedClutch")
    private boolean reinforcedClutch;

    @Column(name = "horsePower")
    private int horsePower;

    @Column(name = "fiscalHorsePower")
    private int fiscalHorsePower;

    @Column(name = "stage")
    private int stage;

    @Column(name = "options")
    private String options;

    @Column(name = "gearbox")
    private String gearbox;

    @Column(name = "outSideColor")
    private String outSideColor;

    @Column(name = "inSideColor")
    private String inSideColor;

    @Column(name = "numberOfOwner")
    private String numberOfOwner;

    @Column(name = "firstHand")
    private String firstHand;

    @Column(name = "euroNorme")
    private String euroNorme;

    @Column(name = "co2")
    private int co2;

    @Column(name = "prix")
    private double prix;

    @Column(name = "kilometrage")
    private Long kilometrage;

    @Column(name = "numberOfPlaces")
    private int numberOfPlaces;

    @Column(name = "numberOfDoor")
    private int numberOfDoor;

    @Column(name = "energy")
    private String energy;

    @Column(name = "publishedDate")
    private Date publishedDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ImageEntity> images;

    @ManyToOne(fetch = FetchType.LAZY)
    private ModelEntity model;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

     @ManyToOne(fetch = FetchType.LAZY)
    private MakeEntity make;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocalisationEntity localisation;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;


    public AnnonceEntity() {
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Long getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Long kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
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

    public LocalisationEntity getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationEntity localisation) {
        this.localisation = localisation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
