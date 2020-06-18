package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "db1")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "serial")
    private Integer id;

    @Column(name = "role", length = 65)
    private String role;

    public RoleEntity() { }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
