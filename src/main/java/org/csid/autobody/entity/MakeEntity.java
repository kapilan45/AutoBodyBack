package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Make")
public class MakeEntity {


    @Id
    private String make;

    @OneToMany
    private List<ModelEntity> model;

    public MakeEntity() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

}
