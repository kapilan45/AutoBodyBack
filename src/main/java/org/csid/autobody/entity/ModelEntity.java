package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Model")
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    private MakeEntity make;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CategoryEntity> category;

    public ModelEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }
}
