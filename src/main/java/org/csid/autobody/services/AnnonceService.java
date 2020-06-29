package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.specifications.AnnoncesSpecificationsBuilder;
import org.csid.autobody.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.csid.autobody.entity.*;
import org.csid.autobody.repository.*;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;
    private final UserService userService;
    private final ImageService imageService;

    public AnnonceService(AnnonceRepository annonceRepository, CategoryRepository categoryRepository, UserRepository userRepository, MakeRepository makeRepository, ModelRepository modelRepository, UserService userService, ImageService imageService) {
        this.annonceRepository = annonceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    public void saveAnnonce(AnnonceDto annonceDto, String userToken) {

        List<ImageEntity> images = imageService.getImages(annonceDto.getImages());
        AnnonceEntity annonce = DtoConverter.map(annonceDto, AnnonceEntity.class);
        MakeEntity make = makeRepository.findByMake(annonceDto.getMake());
        ModelEntity model = modelRepository.findByModel(annonceDto.getModel());
        CategoryEntity category = categoryRepository.findByCategory(annonceDto.getCategory());

        UserEntity u = userService.getUserByToken(userToken);

        annonce.setUser(u);
        annonce.setMake(make);
        annonce.setModel(model);
        annonce.setCategory(category);
        annonce.setImages(images);

        annonce.setPublishedDate(new Date());
        this.annonceRepository.save(annonce);
    }

    public List<AnnonceDto> deleteAnnonce(AnnonceDto annonceDto, String authorization) {
        AnnonceEntity annonce = annonceRepository.getOne(annonceDto.getId());
        annonceRepository.delete(annonce);
        return getAllByUser(authorization);
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

    public List<AnnonceDto> getAllByUser(String userToken) {
        UserEntity user = userService.getUserByToken(userToken);
        List<AnnonceEntity> all = annonceRepository.findAllByUser(user);
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }



    public List<AnnonceEntity> getAnnonceFiltered(Specification annonceSpecification) {
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
