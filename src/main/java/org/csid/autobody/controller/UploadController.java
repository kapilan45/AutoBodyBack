package org.csid.autobody.controller;

import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.services.ImageService;
import org.csid.autobody.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final ImageService imageService;
    private final UserService userService;

    public UploadController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    @PostMapping({"/image", "/file"})
    public UploadDto uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An image file is required");
        }
        ImageEntity image = imageService.saveImage(
                file.getBytes(), file.getContentType(), userService.getAuthenticatedUser());
        return DtoConverter.map(image, UploadDto.class);
    }

    @GetMapping("/image/get/{user}")
    public List<UploadDto> getPublicAnnouncementImages(@PathVariable("user") String username) {
        return imageService.getPublicImages(username);
    }

    @GetMapping("/image/mine")
    public List<UploadDto> getMyImages() {
        return imageService.getImagesOwnedBy(userService.getAuthenticatedUser().getUsername());
    }

    @DeleteMapping("/image/{id}")
    public void deleteMyUnattachedImage(@PathVariable Long id) {
        imageService.deleteUnattachedImage(id, userService.getAuthenticatedUser().getUsername());
    }
}
