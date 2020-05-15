package org.csid.autobody.dto;


public class ModelDto {

    private String model;
    private MakeDto makeDto;

    public ModelDto(){

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public MakeDto getMakeDto() {
        return makeDto;
    }

    public void setMakeDto(MakeDto makeDto) {
        this.makeDto = makeDto;
    }
}
