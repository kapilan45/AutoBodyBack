package org.csid.autobody.entity;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Table(name = "Make")
public class MakeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public MakeEntity() {
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
}
