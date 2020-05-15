package org.csid.autobody.dto;

public class ImageDto {

    private Long id;
    private String path;

    public ImageDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
