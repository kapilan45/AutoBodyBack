package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity saveImage(UploadDto image) {

        ImageEntity imageEntity = DtoConverter.map(image, ImageEntity.class);
        return this.imageRepository.save(imageEntity);
    }

    public List<UploadDto> getImageUser(String user) {

        // TODO getByUser
        List<ImageEntity> images = this.imageRepository.findAll();

        return DtoConverter.mapAsList(images, UploadDto.class);
    }
}
