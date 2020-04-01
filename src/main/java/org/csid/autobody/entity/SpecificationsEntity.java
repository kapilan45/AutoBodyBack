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




}
