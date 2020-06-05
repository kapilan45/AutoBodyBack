package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.*;
import org.csid.autobody.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MakeRepository makeRepository;
    private final LocalisationRepository localisationRepository;

    public AnnonceService(AnnonceRepository annonceRepository, CategoryRepository categoryRepository, UserRepository userRepository, MakeRepository makeRepository, LocalisationRepository localisationRepository) {
        this.annonceRepository = annonceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.makeRepository = makeRepository;
        this.localisationRepository = localisationRepository;
    }

    public void saveAnnonce(AnnonceDto annonceDto) {
        AnnonceEntity annonce = DtoConverter.map(annonceDto, AnnonceEntity.class);
        CategoryEntity categoryEntity = categoryRepository.findById(annonceDto.getCategory()).orElse(null);
        MakeEntity makeEntity = makeRepository.findById(annonceDto.getMake()).orElse(null);
        LocalisationEntity localisationEntity = localisationRepository.findById(annonceDto.getLocalisation()).orElse(null);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        UserEntity u = userRepository.findByUsername(username);

        // TO DO ANONYMOUS
        if (u == null)
            u = userRepository.getOne(2L);


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

    public List<AnnonceDto> getAllByPublishedDate(){
        List<AnnonceEntity> all = annonceRepository.findAllByOrderByPublishedDateAsc();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }


    public List<AnnonceDto> getAllByUser(UserDto userDto) {
        UserEntity user = DtoConverter.map(userDto, UserEntity.class);
        List<AnnonceEntity> all = annonceRepository.findByUser(user.getId());
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    /*
    public List<AnnonceDto> getAllByFilter(String filter){
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    } */
}
