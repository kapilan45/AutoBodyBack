package org.csid.autobody.services;

import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ImageServiceTest {

    @Test
    void validPngIsStoredWithVerifiedOwnerAndMediaType() throws Exception {
        ImageRepository repository = mock(ImageRepository.class);
        when(repository.save(any(ImageEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ImageService service = new ImageService(repository, 1024);
        UserEntity owner = user("alice");
        byte[] png = png();

        ImageEntity saved = service.saveImage(png, "image/png", owner);

        assertArrayEquals(png, saved.getPath());
        assertEquals("image/png", saved.getContentType());
        assertEquals(owner, saved.getOwner());
        verify(repository).save(any(ImageEntity.class));
    }

    @Test
    void invalidBytesAndMismatchedContentTypeAreRejected() throws Exception {
        ImageService service = new ImageService(mock(ImageRepository.class), 1024);
        UserEntity owner = user("alice");

        ResponseStatusException invalidBytes = assertThrows(ResponseStatusException.class,
                () -> service.saveImage("not an image".getBytes(), "image/png", owner));
        assertEquals(HttpStatus.BAD_REQUEST, invalidBytes.getStatusCode());

        ResponseStatusException mismatchedType = assertThrows(ResponseStatusException.class,
                () -> service.saveImage(png(), "image/jpeg", owner));
        assertEquals(HttpStatus.BAD_REQUEST, mismatchedType.getStatusCode());
    }

    @Test
    void oversizedImageIsRejectedBeforeDecoding() throws Exception {
        ImageService service = new ImageService(mock(ImageRepository.class), 4);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.saveImage(png(), "image/png", user("alice")));

        assertEquals(HttpStatus.PAYLOAD_TOO_LARGE, exception.getStatusCode());
    }

    @Test
    void anImageOwnedByAnotherAccountCannotBeAttached() {
        ImageRepository repository = mock(ImageRepository.class);
        ImageService service = new ImageService(repository, 1024);
        ImageEntity image = new ImageEntity();
        image.setId(7L);
        image.setOwner(user("alice"));
        when(repository.findAllById(List.of(7L))).thenReturn(List.of(image));
        UploadDto requested = new UploadDto();
        requested.setId(7L);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.getImages(List.of(requested), "bob", null));

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

    private static UserEntity user(String username) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        return user;
    }

    private static byte[] png() throws Exception {
        BufferedImage source = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(source, "png", output);
        return output.toByteArray();
    }
}
