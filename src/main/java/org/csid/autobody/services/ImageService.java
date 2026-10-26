package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@Transactional
public class ImageService {

    private static final long MAX_IMAGE_PIXELS = 20_000_000L;
    private final ImageRepository imageRepository;
    private final long maxBytes;

    public ImageService(ImageRepository imageRepository,
                        @Value("${autobody.images.max-bytes:5242880}") long maxBytes) {
        if (maxBytes < 1) {
            throw new IllegalArgumentException("autobody.images.max-bytes must be positive");
        }
        this.imageRepository = imageRepository;
        this.maxBytes = maxBytes;
    }

    public ImageEntity saveImage(byte[] bytes, String declaredContentType, UserEntity owner) {
        if (bytes == null || bytes.length == 0 || bytes.length > maxBytes) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "Image must be between 1 byte and the configured limit");
        }
        String detectedContentType = detectContentType(bytes);
        if (!detectedContentType.equalsIgnoreCase(declaredContentType == null ? "" : declaredContentType.trim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image content type does not match its contents");
        }

        ImageEntity image = new ImageEntity();
        image.setPath(bytes);
        image.setContentType(detectedContentType);
        image.setOwner(owner);
        return imageRepository.save(image);
    }

    @Transactional(readOnly = true)
    public List<UploadDto> getPublicImages(String username) {
        return DtoConverter.mapAsList(
                imageRepository.findAllByOwner_UsernameAndAnnonceIsNotNull(username), UploadDto.class);
    }

    @Transactional(readOnly = true)
    public List<UploadDto> getImagesOwnedBy(String username) {
        return DtoConverter.mapAsList(imageRepository.findAllByOwner_Username(username), UploadDto.class);
    }

    public void deleteUnattachedImage(Long imageId, String username) {
        ImageEntity image = imageRepository.findByIdAndOwner_Username(imageId, username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
        if (image.getAnnonce() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Detach the image from its announcement before deleting it");
        }
        imageRepository.delete(image);
    }

    @Transactional(readOnly = true)
    public List<ImageEntity> getImages(List<UploadDto> requestedImages, String username, Long targetAnnonceId) {
        if (requestedImages == null || requestedImages.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> ids = requestedImages.stream()
                .map(UploadDto::getId)
                .toList();
        if (ids.stream().anyMatch(id -> id == null) || ids.stream().distinct().count() != ids.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image identifiers must be unique and present");
        }

        Map<Long, ImageEntity> imagesById = new HashMap<>();
        imageRepository.findAllById(ids).forEach(image -> imagesById.put(image.getId(), image));
        if (imagesById.size() != ids.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more images were not found");
        }
        List<ImageEntity> images = ids.stream().map(imagesById::get).toList();
        for (ImageEntity image : images) {
            AnnonceEntity currentAnnonce = image.getAnnonce();
            boolean alreadyAttachedToTarget = currentAnnonce != null && targetAnnonceId != null
                    && currentAnnonce.getId().equals(targetAnnonceId);
            if (image.getOwner() == null) {
                if (!alreadyAttachedToTarget) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "An image belongs to another account");
                }
            } else if (!image.getOwner().getUsername().equals(username)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "An image belongs to another account");
            }
            if (currentAnnonce != null && !alreadyAttachedToTarget) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "An image is already attached to another announcement");
            }
        }
        return images;
    }

    public void saveImages(List<ImageEntity> images) {
        imageRepository.saveAll(images);
    }

    private String detectContentType(byte[] bytes) {
        try (ImageInputStream input = ImageIO.createImageInputStream(new ByteArrayInputStream(bytes))) {
            if (input == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported image data");
            }
            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
            if (!readers.hasNext()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only JPEG, PNG, and GIF images are accepted");
            }

            ImageReader reader = readers.next();
            try {
                reader.setInput(input, true, true);
                long pixelCount = (long) reader.getWidth(0) * reader.getHeight(0);
                if (pixelCount <= 0 || pixelCount > MAX_IMAGE_PIXELS) {
                    throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "Image dimensions exceed the configured limit");
                }
                reader.read(0);
                return switch (reader.getFormatName().toLowerCase(Locale.ROOT)) {
                    case "jpg", "jpeg" -> "image/jpeg";
                    case "png" -> "image/png";
                    case "gif" -> "image/gif";
                    default -> throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Only JPEG, PNG, and GIF images are accepted");
                };
            } finally {
                reader.dispose();
            }
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image data could not be decoded", exception);
        }
    }
}
