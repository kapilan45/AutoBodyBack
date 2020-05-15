package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Localisation")
public class LocalisationEntity {

    @Id
    private String city;

    @Column(name = "postal_code")
    private String postal_code;

    @OneToMany(mappedBy = "localisation", fetch = FetchType.LAZY)
    private List<AnnonceEntity> annonces;

    public LocalisationEntity() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public List<AnnonceEntity> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<AnnonceEntity> annonces) {
        this.annonces = annonces;
    }
}
