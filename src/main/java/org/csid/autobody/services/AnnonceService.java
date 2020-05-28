package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.entity.*;
import org.csid.autobody.repository.AnnonceRepository;
import org.csid.autobody.repository.CategoryRepository;
import org.csid.autobody.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.util.List;

@Service
@Transactional
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public AnnonceService(AnnonceRepository annonceRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.annonceRepository = annonceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public void saveAnnonce(AnnonceDto annonceDto) {
        AnnonceEntity annonce = DtoConverter.map(annonceDto, AnnonceEntity.class);
        CategoryEntity categoryEntity = categoryRepository.findById(annonceDto.getCategory()).orElse(null);

        // TODO
        MakeEntity makeEntity = null;
        LocalisationEntity localisationEntity = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        UserEntity u = userRepository.findByUsername(username);

        // TO DO ANONYMOUS
        if (u == null)
            u = userRepository.getOne(1L);


        //AnnonceEntity annonce = new AnnonceEntity();
        annonce.setCategory(categoryEntity);
        annonce.setUser(u);
        annonce.setMake(makeEntity);
        annonce.setLocalisation(localisationEntity);
        this.annonceRepository.save(annonce);
    }

    public List<AnnonceDto> getAll() {
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    /* TODO
    public List<AnnonceDto> getAllByPublishedDate(){
        List<AnnonceEntity> all = annonceRepository.findByPublishedDate();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllByUser(UserDto userDto) {
        UserEntity user = DtoConverter.map(userDto, UserEntity.class);
        List<AnnonceEntity> all = annonceRepository.findByUser(user);
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    public List<AnnonceDto> getAllByFilter(String filter){
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    } */
}
