package org.csid.autobody.dto;

public class VehiculeDto {

    private Long id;

    private String marque;

    private String categorie;

    private String energie;

    private String kilometrage;

    private int annee;

    private int stage;

    private String localisation;

    private double prix;

    public VehiculeDto(){
    }

    public VehiculeDto(Long id, String marque, String categorie, String energie, String kilometrage, int annee, int stage, String localisation, double prix) {
        this.id = id;
        this.marque = marque;
        this.categorie = categorie;
        this.energie = energie;
        this.kilometrage = kilometrage;
        this.annee = annee;
        this.stage = stage;
        this.localisation = localisation;
        this.prix = prix;
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

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
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
        return "VehiculeDTO{" +
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

    public static final class VehiculeDtoBuilder {

        private Long id;

        private String marque;

        private String categorie;

        private String energie;

        private String kilometrage;

        private int annee;

        private int stage;

        private String localisation;

        private double prix;

        private VehiculeDtoBuilder(){
        }

        public static VehiculeDtoBuilder create(){
            return new VehiculeDtoBuilder();
        }

        public VehiculeDtoBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public VehiculeDtoBuilder withMarque(String marque){
            this.marque = marque;
            return this;
        }

        public VehiculeDtoBuilder withCategorie(String categorie){
            this.categorie = categorie;
            return this;
        }

        public VehiculeDtoBuilder withEnergie(String energie){
            this.energie = energie;
            return this;
        }

        public VehiculeDtoBuilder withKilometrage(String kilometrage){
            this.kilometrage = kilometrage;
            return this;
        }

        public VehiculeDtoBuilder withAnnee(int annee){
            this.annee = annee;
            return this;
        }

        public VehiculeDtoBuilder withStage(int stage){
            this.stage = stage;
            return this;
        }

        public VehiculeDtoBuilder withLocalisation(String localisation){
            this.localisation = localisation;
            return this;
        }

        public VehiculeDtoBuilder withPrix (double prix){
            this.prix = prix;
            return this;
        }

        public VehiculeDto build(){
            return  new VehiculeDto(id,marque,categorie,energie,kilometrage,annee,stage,localisation,prix);
        }
    }
}
