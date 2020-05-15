package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class CategoryEntity {

    @Id
    private String category;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ModelEntity> modelEntity;

    public CategoryEntity() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ModelEntity> getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(List<ModelEntity> modelEntity) {
        this.modelEntity = modelEntity;
    }
}
