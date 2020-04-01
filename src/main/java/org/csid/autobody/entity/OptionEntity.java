package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Option")
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private SpecificationsEntity specificationsEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SpecificationsEntity getSpecificationsEntity() {
        return specificationsEntity;
    }

    public void setSpecificationsEntity(SpecificationsEntity specificationsEntity) {
        this.specificationsEntity = specificationsEntity;
    }
}
