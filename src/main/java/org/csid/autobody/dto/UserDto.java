package org.csid.autobody.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserDto {

    @Size(max = 75)
    private String username;

    @Size(max = 72)
    private String password = null;

    @Email
    @Size(max = 150)
    private String mail;
    private String role;
    private boolean stat;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }
}
