package org.csid.autobody.dto;

public class UploadDto {

    private Long id;
    private byte[] path;

    public UploadDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPath() {
        return path;
    }

    public void setPath(byte[] path) {
        this.path = path;
    }
}
