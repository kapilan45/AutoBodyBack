package org.csid.autobody.controller;

import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.UploadDto;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public final class DtoConverter {

    private DtoConverter() {
    }

    public static <S, D> D map(S source, Class<D> destinationClass) {
        if (source == null) {
            return null;
        }
        D destination = BeanUtils.instantiateClass(destinationClass);

        if (source instanceof AnnonceEntity annonce && destination instanceof AnnonceDto dto) {
            BeanUtils.copyProperties(source, destination, "category", "model", "make", "images", "user");
            dto.setCategory(annonce.getCategory() == null ? null : annonce.getCategory().getCategory());
            dto.setModel(annonce.getModel() == null ? null : annonce.getModel().getModel());
            dto.setMake(annonce.getMake() == null ? null : annonce.getMake().getMake());
            dto.setUser(map(annonce.getUser(), UserDto.class));
            dto.setImages(annonce.getImages() == null ? null : annonce.getImages().stream()
                    .map(image -> map(image, UploadDto.class))
                    .toList());
        } else if (source instanceof AnnonceDto && destination instanceof AnnonceEntity) {
            BeanUtils.copyProperties(source, destination, "category", "model", "make", "images", "user");
        } else if (source instanceof UserEntity user && destination instanceof UserDto dto) {
            BeanUtils.copyProperties(source, destination, "role");
            dto.setRole(user.getRole() == null ? null : user.getRole().getRole());
            dto.setPassword(null);
        } else if (source instanceof UserDto && destination instanceof UserEntity) {
            BeanUtils.copyProperties(source, destination, "role");
        } else {
            BeanUtils.copyProperties(source, destination);
        }
        return destination;
    }

    public static <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        List<D> mapped = new ArrayList<>();
        for (S item : source) {
            mapped.add(map(item, destinationClass));
        }
        return mapped;
    }

    public static void copyAnnonceFields(AnnonceDto source, AnnonceEntity destination) {
        BeanUtils.copyProperties(source, destination, "category", "model", "make", "images", "user");
    }

    public static UserDto toSafeUserDto(UserEntity user) {
        return map(user, UserDto.class);
    }
}
