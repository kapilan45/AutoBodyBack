package org.csid.autobody.entity;

public class Vehicule {

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

    public Vehicule(Long id, String marque, String modele, String categorie, String energie, int kilometrage, int annee, int stage, String localisation, double prix, String image) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
        this.energie = energie;
        this.kilometrage = kilometrage;
        this.annee = annee;
        this.stage = stage;
        this.localisation = localisation;
        this.prix = prix;
        this.image = image;
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

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", categorie='" + categorie + '\'' +
                ", energie='" + energie + '\'' +
                ", kilometrage='" + kilometrage + '\'' +
                ", annee=" + annee +
                ", stage=" + stage +
                ", localisation='" + localisation + '\'' +
                ", prix=" + prix +
                '}';
    }

    public Vehicule mergeWith (Vehicule other){
        return VehiculeBuilder.create()
                .withId(id)
                .withMarque(other.marque)
                .withCategorie(other.categorie)
                .withEnergie(other.energie)
                .withKilometrage(other.kilometrage)
                .withAnnee(other.annee)
                .withStage(other.stage)
                .withLocalisation(other.localisation)
                .withPrix(other.prix)
                .build();

        }

    public static final class VehiculeBuilder {

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

        public static VehiculeBuilder create(){
            return new VehiculeBuilder();
        }

        public VehiculeBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public VehiculeBuilder withMarque(String marque){
            this.marque = marque;
            return this;
        }

        public VehiculeBuilder withCategorie(String categorie){
            this.categorie = categorie;
            return this;
        }

        public VehiculeBuilder withEnergie(String energie){
            this.energie = energie;
            return this;
        }

        public VehiculeBuilder withKilometrage(int kilometrage){
            this.kilometrage = kilometrage;
            return this;
        }

        public VehiculeBuilder withAnnee(int annee){
            this.annee = annee;
            return this;
        }

        public VehiculeBuilder withStage(int stage){
            this.stage = stage;
            return this;
        }

        public VehiculeBuilder withLocalisation(String localisation){
            this.localisation = localisation;
            return this;
        }

        public VehiculeBuilder withPrix(double prix){
            this.prix = prix;
            return this;
        }

        public VehiculeBuilder withModele(String modele){
            this.modele = modele;
            return this;
        }

        public VehiculeBuilder withImage(String image){
            this.image = image;
            return this;
        }

        public Vehicule build(){
            return new Vehicule(id,marque, modele, categorie,energie,kilometrage,annee,stage,localisation,prix, image);
        }
    }
}
