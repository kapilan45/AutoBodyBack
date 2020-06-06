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
    private final ModelRepository modelRepository;

    public AnnonceService(AnnonceRepository annonceRepository, CategoryRepository categoryRepository, UserRepository userRepository, MakeRepository makeRepository, ModelRepository modelRepository) {
        this.annonceRepository = annonceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
    }

    public void saveAnnonce(AnnonceDto annonceDto) {
        AnnonceEntity annonce = DtoConverter.map(annonceDto, AnnonceEntity.class);
       // MakeEntity makeEntity = makeRepository.findByName(annonceDto.getMake());
       // ModelEntity modelEntity = modelRepository.findModelByNameAndMake(annonceDto.getModel(),annonceDto.getMake());
        //CategoryEntity categoryEntity = categoryRepository.findCategoryByNameAndModel(annonceDto.getCategory(),annonceDto.getModel());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        UserEntity u = userRepository.findByUsername(username);

        // TO DO ANONYMOUS
        if (u == null)
            u = userRepository.getOne(1L);


        //AnnonceEntity annonce = new AnnonceEntity();

        annonce.setUser(u);
        annonce.setMake(null);
      //  annonce.setModel(modelEntity);
        annonce.setCategory(null);
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
