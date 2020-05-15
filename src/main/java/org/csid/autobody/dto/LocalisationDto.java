package org.csid.autobody.dto;

public class LocalisationDto {

    private String city;
    private String postalCode;

    public LocalisationDto(){

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
