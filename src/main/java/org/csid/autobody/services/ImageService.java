package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.ImageDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.repository.ImageRepository;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public boolean saveImage(ImageDto image) {

        ImageEntity imageEntity = DtoConverter.map(image, ImageEntity.class);
        this.imageRepository.save(imageEntity);
        return true;
    }

    public List<ImageDto> getImageUser(String user) {

        // TODO getByUser
        List<ImageEntity> images = this.imageRepository.findAll();

        return DtoConverter.mapAsList(images, ImageDto.class);
    }
}
