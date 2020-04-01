package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Energy")
public class EnergyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private Long name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SpecificationsEntity> specificationsEntity;

    public EnergyEntity() {
    }

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

    public List<SpecificationsEntity> getSpecificationsEntity() {
        return specificationsEntity;
    }

    public void setSpecificationsEntity(List<SpecificationsEntity> specificationsEntity) {
        this.specificationsEntity = specificationsEntity;
    }
}
