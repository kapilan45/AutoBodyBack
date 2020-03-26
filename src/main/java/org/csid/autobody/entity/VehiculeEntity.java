package org.csid.autobody.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicule")
public class VehiculeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicule")
    private Long id;

    @Column(name = "marque")
    private String marque;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "energie")
    private String energie;

    @Column(name = "kilometrage")
    private int kilometrage;

    @Column(name = "annee")
    private int annee;

    @Column(name = "stage")
    private int stage;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "prix")
    private double prix;

    public VehiculeEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public static final class VehiculeEntityBuilder {

        private Long id;

        private String marque;
        private String modele;

        private String categorie;

        private String energie;

        private int kilometrage;

        private int annee;

        private int stage;

        private String localisation;

        private double prix;
        private String image;

        private VehiculeEntityBuilder(){}

        public static VehiculeEntityBuilder create(){
            return new VehiculeEntityBuilder();
        }

        public VehiculeEntityBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public VehiculeEntityBuilder withMarque(String marque){
            this.marque = marque;
            return this;
        }

        public VehiculeEntityBuilder withCategorie(String categorie){
            this.categorie = categorie;
            return this;
        }

        public VehiculeEntityBuilder withEnergie(String energie){
            this.energie = energie;
            return this;
        }

        public VehiculeEntityBuilder withKilometrage(int kilometrage){
            this.kilometrage = kilometrage;
            return this;
        }

        public VehiculeEntityBuilder withAnnee(int annee){
            this.annee = annee;
            return this;
        }

        public VehiculeEntityBuilder withStage(int stage){
            this.stage = stage;
            return this;
        }

        public VehiculeEntityBuilder withLocalisation(String localisation){
            this.localisation = localisation;
            return this;
        }

        public VehiculeEntityBuilder withPrix(double prix){
            this.prix = prix;
            return this;
        }

        public VehiculeEntityBuilder withModele(String modele){
            this.modele = modele;
            return this;
        }

        public VehiculeEntityBuilder withImage(String image){
            this.image = image;
            return this;
        }

        public VehiculeEntity build(){
            VehiculeEntity vehiculeEntity = new VehiculeEntity();

            vehiculeEntity.setId(id);
            vehiculeEntity.setMarque(marque);
            vehiculeEntity.setCategorie(categorie);
            vehiculeEntity.setEnergie(energie);
            vehiculeEntity.setKilometrage(kilometrage);
            vehiculeEntity.setAnnee(annee);
            vehiculeEntity.setStage(stage);
            vehiculeEntity.setLocalisation(localisation);
            vehiculeEntity.setPrix(prix);
            return vehiculeEntity;
        }
    }
}
