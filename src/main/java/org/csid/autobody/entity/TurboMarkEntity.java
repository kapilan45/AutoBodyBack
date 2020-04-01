package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Turbo_mark")
public class TurboMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<TurboModelEntity> turboModelEntity;

    public TurboMarkEntity() {
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

    public List<TurboModelEntity> getTurboModelEntity() {
        return turboModelEntity;
    }

    public void setTurboModelEntity(List<TurboModelEntity> turboModelEntity) {
        this.turboModelEntity = turboModelEntity;
    }
}
