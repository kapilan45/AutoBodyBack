package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Energy")
public class EnergyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private Long name;

    @OneToMany(fetch = FetchType.LAZY)
    private SpecificationsEntity specificationsEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public SpecificationsEntity getSpecificationsEntity() {
        return specificationsEntity;
    }

    public void setSpecificationsEntity(SpecificationsEntity specificationsEntity) {
        this.specificationsEntity = specificationsEntity;
    }
}