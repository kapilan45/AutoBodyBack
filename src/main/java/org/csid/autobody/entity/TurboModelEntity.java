package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Turbo_model")
public class TurboModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private TurboMarkEntity turboMarkEntity;

    @OneToMany(fetch = FetchType.LAZY)
    private SpecificationsEntity specificationsEntity;

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

    public TurboMarkEntity getTurboMarkEntity() {
        return turboMarkEntity;
    }

    public void setTurboMarkEntity(TurboMarkEntity turboMarkEntity) {
        this.turboMarkEntity = turboMarkEntity;
    }
}
