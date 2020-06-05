package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Model")
public class ModelEntity {

    @Id
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    private MakeEntity make;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CategoryEntity> category;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public MakeEntity getMake() {
        return make;
    }

    public void setMake(MakeEntity make) {
        this.make = make;
    }
}
