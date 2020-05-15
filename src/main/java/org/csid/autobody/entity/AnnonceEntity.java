package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Annonces")
public class AnnonceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placeNumber")
    private int placeNumber;

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

    @Column(name = "prix")
    private double prix;

    @ManyToOne(fetch = FetchType.LAZY)
    private MakeEntity make;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocalisationEntity localisation;

    public AnnonceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MakeEntity getMakes() {
        return make;
    }

    public void setMakes(MakeEntity makes) {
        this.make = makes;
    }

    public int getPlaceNumber() {
        return placeNumber;
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

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public LocalisationEntity getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationEntity localisation) {
        this.localisation = localisation;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public MakeEntity getMake() {
        return make;
    }

    public void setMake(MakeEntity make) {
        this.make = make;
    }
}
