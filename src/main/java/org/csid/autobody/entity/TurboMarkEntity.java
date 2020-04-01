package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Turbo_mark")
public class TurboMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private TurboModelEntity turboModelEntity;

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

    public TurboModelEntity getTurboModelEntity() {
        return turboModelEntity;
    }

    public void setTurboModelEntity(TurboModelEntity turboModelEntity) {
        this.turboModelEntity = turboModelEntity;
    }
}
