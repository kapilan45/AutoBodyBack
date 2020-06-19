package org.csid.autobody.services;

import java.util.List;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ImageEntity> getImages(List<UploadDto> images) {

        System.out.println(images.size());
        List<ImageEntity> imageEntities = new ArrayList<>();
        for (UploadDto image : images){
            ImageEntity img = imageRepository.getOne((long) image.getId());
            imageEntities.add(img);
        }
      /*  for (int image : images) {
            //ImageEntity im = imageRepository.getOne((long) image);
            ImageEntity im = imageRepository.getOne((long) image);
            imageEntities.add(im);
        } */

        return imageEntities;
    }
}
