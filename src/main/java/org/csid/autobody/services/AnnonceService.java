package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.specifications.AnnoncesSpecificationsBuilder;
import org.csid.autobody.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.csid.autobody.entity.*;
import org.csid.autobody.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final CategoryRepository categoryRepository;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;
    private final UserService userService;
    private final ImageService imageService;

    public AnnonceService(AnnonceRepository annonceRepository, CategoryRepository categoryRepository,
                          MakeRepository makeRepository, ModelRepository modelRepository,
                          UserService userService, ImageService imageService) {
        this.annonceRepository = annonceRepository;
        this.categoryRepository = categoryRepository;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    public void saveAnnonce(AnnonceDto annonceDto) {
        if (annonceDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Announcement is required");
        }
        UserEntity user = userService.getAuthenticatedUser();
        AnnonceEntity existingAnnonce = null;
        if (annonceDto.getId() != null) {
            existingAnnonce = annonceRepository.findById(annonceDto.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            ensureOwner(existingAnnonce, user);
        }

        List<ImageEntity> previousImages = existingAnnonce == null || existingAnnonce.getImages() == null
                ? List.of()
                : existingAnnonce.getImages();
        List<ImageEntity> images = annonceDto.getImages() == null && existingAnnonce != null
                ? previousImages
                : imageService.getImages(annonceDto.getImages(), user.getUsername(), annonceDto.getId());
        List<ImageEntity> detachedImages = previousImages.stream()
                .filter(previous -> images.stream().noneMatch(image -> image.getId().equals(previous.getId())))
                .toList();
        detachedImages.forEach(image -> image.setAnnonce(null));
        imageService.saveImages(detachedImages);
        AnnonceEntity annonce = existingAnnonce == null ? new AnnonceEntity() : existingAnnonce;
        DtoConverter.copyAnnonceFields(annonceDto, annonce);
        MakeEntity make = makeRepository.findByMake(annonceDto.getMake());
        ModelEntity model = modelRepository.findByModel(annonceDto.getModel());
        CategoryEntity category = categoryRepository.findByCategory(annonceDto.getCategory());

        annonce.setUser(user);
        annonce.setMake(make);
        annonce.setModel(model);
        annonce.setCategory(category);
        annonce.setImages(images);
        if (images != null) {
            images.forEach(image -> image.setAnnonce(annonce));
        }

        annonce.setPublishedDate(new Date());
        this.annonceRepository.save(annonce);
    }

    public List<AnnonceDto> deleteAnnonce(AnnonceDto annonceDto) {
        if (annonceDto == null || annonceDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Announcement identifier is required");
        }
        UserEntity user = userService.getAuthenticatedUser();
        AnnonceEntity annonce = annonceRepository.findById(annonceDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ensureOwner(annonce, user);
        List<ImageEntity> images = annonce.getImages() == null ? List.of() : annonce.getImages();
        images.forEach(image -> image.setAnnonce(null));
        imageService.saveImages(images);
        annonceRepository.delete(annonce);
        return DtoConverter.mapAsList(annonceRepository.findAllByUser(user), AnnonceDto.class);
    }

    private void ensureOwner(AnnonceEntity annonce, UserEntity user) {
        if (annonce.getUser() == null || !Objects.equals(annonce.getUser().getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public List<AnnonceDto> getAll() {
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllRecent(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPublishedDateAsc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllDecroissant(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPublishedDateDesc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getByLessPrice(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPriceDesc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getByHighPrice(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPriceAsc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllByUser() {
        UserEntity user = userService.getAuthenticatedUser();
        List<AnnonceEntity> all = annonceRepository.findAllByUser(user);
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }



    public List<AnnonceEntity> getAnnonceFiltered(Specification<AnnonceEntity> annonceSpecification) {
        return annonceRepository.findAll(annonceSpecification);
    }


    public AnnoncesSpecificationsBuilder mapAnnonceObjects(AnnoncesSpecificationsBuilder builder){

        ModelEntity modelEntity = new ModelEntity();

        for (SearchCriteria searchCriteria : builder.getParams()) {

            if(searchCriteria.getKey().equalsIgnoreCase("make")){
                MakeEntity makeEntity = makeRepository.findByMake(searchCriteria.getValue().toString());
                searchCriteria.setValue(makeEntity);
            }else if(searchCriteria.getKey().equalsIgnoreCase("model")){
                modelEntity = modelRepository.findByModel(searchCriteria.getValue().toString());
                searchCriteria.setValue(modelEntity);
            }else if(searchCriteria.getKey().equalsIgnoreCase("category")){
                CategoryEntity categoryEntity = categoryRepository.findByCategory(searchCriteria.getValue().toString());
                searchCriteria.setValue(categoryEntity);
            }
        }
        return builder;
    }

    public List<AnnonceDto> getBasicFiltredAnnonce(String filter) {

        List<AnnonceDto> all;
        if(filter.equals("croissant")){
            all = getByHighPrice();
        }else if (filter.equals("decroissant")){
            all = getByLessPrice();
        }else if (filter.equals("anciennes")) {
            all = getAllDecroissant();
        }else {
            // récentes
            all = getAllRecent();
        }
        return all;

    }


}
