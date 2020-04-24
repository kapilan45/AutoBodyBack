package org.csid.autobody.dto;

public class AnnonceDto {

    private Long id;
    private String marque;
   // private String modele;
    private String categorie;
    private String energie;
    private int kilometrage;
    private int annee;
    private int stage;
    private String localisation;
    private double prix;
    private String image;

    public AnnonceDto() {
    }

    public Long getId(){
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "VehiculeDTO{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", categorie='" + categorie + '\'' +
                ", energie='" + energie + '\'' +
                ", kilometrage='" + kilometrage + '\'' +
                ", annee=" + annee +
                ", stage=" + stage +
                ", localisation='" + localisation + '\'' +
                ", prix=" + prix + + '\'' +
                '}';
    }

}
