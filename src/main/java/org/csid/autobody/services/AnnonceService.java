package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.*;
import org.csid.autobody.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        CategoryEntity category = categoryRepository.findByCategoryAndModel(annonceDto.getCategory(), model);

        UserEntity u = userService.getUserByToken(userToken);
        //if(u.isStat()) Throw new Exception();

        // TODO ANONYMOUS THROW Exception

        annonce.setUser(u);
        annonce.setMake(make);
        annonce.setModel(model);
        annonce.setCategory(category);
        annonce.setImages(images);
        this.annonceRepository.save(annonce);
    }

    public List<AnnonceDto> getAll() {
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllRecent(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPublishedDateAsc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }


    public List<AnnonceDto> getAllByUser(String userToken) {
        UserEntity user = userService.getUserByToken(userToken);
        List<AnnonceEntity> all = annonceRepository.findAllByUser(user);
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public void getAnnonceFiltred(String id, String value) {
        System.out.println("get annonce by filtre TODO");
    }

    /*
    public List<AnnonceDto> getAllByFilter(String filter){
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    } */
}
