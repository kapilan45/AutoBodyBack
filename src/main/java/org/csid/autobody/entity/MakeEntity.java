package org.csid.autobody.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Make")
public class MakeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make", unique = true)
    private String make;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany
    private List<ModelEntity> model;

    public MakeEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public List<ModelEntity> getModel() {
        return model;
    }

    public void setModel(List<ModelEntity> model) {
        this.model = model;
    }
}
