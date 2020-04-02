package org.csid.autobody.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Annonces")
public class AnnonceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "annonce", fetch = FetchType.LAZY)
    private MakeEntity makes;

    @OneToOne(mappedBy = "annonce", fetch = FetchType.LAZY)
    private SpecificationsEntity specifications;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocalisationEntity localisation;

    public AnnonceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MakeEntity getMakes() {
        return makes;
    }

    public void setMakes(MakeEntity makes) {
        this.makes = makes;
    }

    public SpecificationsEntity getSpecifications() {
        return specifications;
    }

    public void setSpecifications(SpecificationsEntity specifications) {
        this.specifications = specifications;
    }

    public LocalisationEntity getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationEntity localisation) {
        this.localisation = localisation;
    }
}
