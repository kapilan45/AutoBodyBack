package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Transmission")
public class TransmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private TransmissionEntity transmissionEntity;

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

    public TransmissionEntity getTransmissionEntity() {
        return transmissionEntity;
    }

    public void setTransmissionEntity(TransmissionEntity transmissionEntity) {
        this.transmissionEntity = transmissionEntity;
    }
}
