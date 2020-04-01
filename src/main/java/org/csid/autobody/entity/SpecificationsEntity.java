package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Specifications")
public class SpecificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place_number")
    private int placeNumber;

    @Column(name = "max_speed")
    private int maxSpeed;

    @Column(name = "reinforced_clutch")
    private boolean reinforcedClutch;

    @Column(name = "horse_power")
    private int horsePower;

    @Column(name = "fiscal_horsepower")
    private int fiscalHorsepower;

    @Column(name = "stage")
    private int stage;

    @Column(name = "gearbox")
    private String gearbox;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransmissionEntity transmissionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private EnergyEntity energyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private TurboModelEntity turboModelEntity;

    @OneToOne(fetch = FetchType.LAZY)
    private OptionEntity optionEntity;

    public SpecificationsEntity() {
    }

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

    public int getFiscalHorsepower() {
        return fiscalHorsepower;
    }

    public void setFiscalHorsepower(int fiscalHorsepower) {
        this.fiscalHorsepower = fiscalHorsepower;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public TransmissionEntity getTransmissionEntity() {
        return transmissionEntity;
    }

    public void setTransmissionEntity(TransmissionEntity transmissionEntity) {
        this.transmissionEntity = transmissionEntity;
    }

    public EnergyEntity getEnergyEntity() {
        return energyEntity;
    }

    public void setEnergyEntity(EnergyEntity energyEntity) {
        this.energyEntity = energyEntity;
    }

    public TurboModelEntity getTurboModelEntity() {
        return turboModelEntity;
    }

    public void setTurboModelEntity(TurboModelEntity turboModelEntity) {
        this.turboModelEntity = turboModelEntity;
    }

    public OptionEntity getOptionEntity() {
        return optionEntity;
    }

    public void setOptionEntity(OptionEntity optionEntity) {
        this.optionEntity = optionEntity;
    }
}
