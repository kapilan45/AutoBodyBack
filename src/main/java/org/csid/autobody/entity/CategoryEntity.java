package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "Category")
public class CategoryEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private ModelEntity modelEntity;

}
