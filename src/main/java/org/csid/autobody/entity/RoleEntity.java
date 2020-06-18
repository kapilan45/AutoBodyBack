package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
