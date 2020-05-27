package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "db1")
public class UserEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "serial")
    private Long id;

    @Column(name = "username", length = 75)
    private String username;

    @Column(name = "password", length = 75)
    private String password;

    @Column(name = "mail", length = 150)
    private String mail;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
