package org.csid.autobody.controller;

import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UploadController {

    @Autowired
    private ImageService imageService;

    @PostMapping("image")
    public UploadDto uplaodImage(@RequestParam("image") MultipartFile file) throws IOException {
        UploadDto image = new UploadDto();
        image.setPath(file.getBytes());
        ImageEntity img = this.imageService.saveImage(image);
        return DtoConverter.map(img, UploadDto.class);
    }

    @GetMapping("image/get/{user}")
    public List<UploadDto> getImage(@PathVariable("user") String user) {
        List<UploadDto> images = imageService.getImageUser(user);

        return images;
    }

    @PostMapping("file")
    public UploadDto uploadFile(@RequestParam("image") MultipartFile file) throws IOException {
        UploadDto pj = new UploadDto();
        pj.setId((long) 1);
        pj.setPath(file.getBytes());
        // TODO créer un file service pour les PJ
        return DtoConverter.map(this.imageService.saveImage(pj), UploadDto.class);
    }



}
