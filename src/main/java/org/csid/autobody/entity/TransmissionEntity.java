package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Transmission")
public class TransmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SpecificationsEntity> specificationsEntities;

    public TransmissionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecificationsEntity> getSpecificationsEntities() {
        return specificationsEntities;
    }

    public void setSpecificationsEntities(List<SpecificationsEntity> specificationsEntities) {
        this.specificationsEntities = specificationsEntities;
    }
}
