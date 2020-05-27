package org.csid.autobody.controller;

import org.csid.autobody.dto.ImageDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping()
    public boolean uplaodImage(@RequestParam("image") MultipartFile file) throws IOException {
        ImageDto image = new ImageDto();
        image.setId((long) 1);
        image.setPath(file.getBytes());
        return this.imageService.saveImage(image);
    }

    @GetMapping(path = { "/get/{user}" })
    public List<ImageDto> getImage(@PathVariable("user") String user) {
        List<ImageDto> images = imageService.getImageUser(user);

        return images;
    }



}
