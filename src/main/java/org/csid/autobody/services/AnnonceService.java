package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.AnnonceFilterDto;
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


    public List<AnnonceDto> getAllByUser(UserDto userDto) {
        UserEntity user = DtoConverter.map(userDto, UserEntity.class);
        List<AnnonceEntity> all = annonceRepository.findByUser(user.getId());
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }

    /*
    public List<AnnonceDto> getAllFiltered(AnnonceFilterDto annonceFilterDto){

        List<AnnonceEntity> allFiltered = null;

        MakeEntity makeEntity = makeRepository.findByMake(annonceFilterDto.getMake());
        ModelEntity modelEntity = modelRepository.findByModel(annonceFilterDto.getModel());
        CategoryEntity categoryEntity = categoryRepository.findByCategoryAndModel(annonceFilterDto.getCategory(),modelEntity);



        if(annonceFilterDto.getMaxPrice() == 0){
            annonceFilterDto.setMaxPrice(1000000);
        }

        if(annonceFilterDto.getMaxFiscalHorsePower() == 0){
            annonceFilterDto.setMaxFiscalHorsePower(1000000);
        }

        if(annonceFilterDto.getMaxHorsePower() == 0){
            annonceFilterDto.setMaxHorsePower(1000000);
        }

        if(annonceFilterDto.getMaxFuelEconomy() == 0){
            annonceFilterDto.setMaxFuelEconomy(1000000);
        }

        if(annonceFilterDto.getMinMileage() == null){
            annonceFilterDto.setMinMileage(0L);
        }

        if(annonceFilterDto.getMaxMileage() == null){
            annonceFilterDto.setMaxMileage(1000000L);
        }

        if(annonceFilterDto.getMaxMileageSinceModification() == 0){
            annonceFilterDto.setMaxMileageSinceModification(1000000);
        }

        if(annonceFilterDto.getMaxTorque() == 0){
            annonceFilterDto.setMaxTorque(1000000);
        }

        if(annonceFilterDto.getMaxTorqueSinceModification() == 0){
            annonceFilterDto.setMaxTorque(1000000);
        }

        if(annonceFilterDto.getMaxYear() == 0){
            annonceFilterDto.setMaxYear(1000000);
        }


        //Si pas de Marque, Model et Category
        if(annonceFilterDto.getMake().isEmpty() && annonceFilterDto.getModel().isEmpty() && annonceFilterDto.getCategory().isEmpty()){

            allFiltered = annonceRepository.findFiltered(annonceFilterDto.getMinHorsePower(),annonceFilterDto.getMaxHorsePower(),annonceFilterDto.getMinPrice(),annonceFilterDto.getMaxPrice(),
                    annonceFilterDto.getMinTorque(),annonceFilterDto.getMaxTorque(),annonceFilterDto.getMinTorqueSinceModification(),annonceFilterDto.getMaxTorqueSinceModification(),
                    annonceFilterDto.getMinFuelEconomy(),annonceFilterDto.getMaxFuelEconomy(),annonceFilterDto.getMinMileage(),annonceFilterDto.getMaxMileage(),annonceFilterDto.getMinMileageSinceModification(),
                    annonceFilterDto.getMaxMileageSinceModification(),annonceFilterDto.getMinYear(),annonceFilterDto.getMaxYear(),annonceFilterDto.getMinFiscalHorsePower(),annonceFilterDto.getMaxFiscalHorsePower());
        }
        //Si on a une marque mais pas de model et de category
        else if(annonceFilterDto.getMake().isEmpty() && annonceFilterDto.getModel().isEmpty() && annonceFilterDto.getCategory().isEmpty()){

            allFiltered = annonceRepository.findFilteredWithMake(makeEntity,annonceFilterDto.getMinHorsePower(),annonceFilterDto.getMaxHorsePower(),annonceFilterDto.getMinPrice(),annonceFilterDto.getMaxPrice(),
                    annonceFilterDto.getMinTorque(),annonceFilterDto.getMaxTorque(),annonceFilterDto.getMinTorqueSinceModification(),annonceFilterDto.getMaxTorqueSinceModification(),
                    annonceFilterDto.getMinFuelEconomy(),annonceFilterDto.getMaxFuelEconomy(),annonceFilterDto.getMinMileage(),annonceFilterDto.getMaxMileage(),annonceFilterDto.getMinMileageSinceModification(),
                    annonceFilterDto.getMaxMileageSinceModification(),annonceFilterDto.getMinYear(),annonceFilterDto.getMaxYear(),annonceFilterDto.getMinFiscalHorsePower(),annonceFilterDto.getMaxFiscalHorsePower());
        }
        //Si on a une marque et un model mais pas de category
        else if(!annonceFilterDto.getMake().isEmpty() && !annonceFilterDto.getModel().isEmpty() && annonceFilterDto.getCategory().isEmpty()){

           allFiltered = annonceRepository.findFilteredWithMakeAndModel(makeEntity,modelEntity,annonceFilterDto.getMinPrice(),annonceFilterDto.getMaxPrice(),
                    annonceFilterDto.getMinTorque(),annonceFilterDto.getMaxTorque(),annonceFilterDto.getMinTorqueSinceModification(),annonceFilterDto.getMaxTorqueSinceModification(),
                    annonceFilterDto.getMinFuelEconomy(),annonceFilterDto.getMaxFuelEconomy(),annonceFilterDto.getMinMileage(),annonceFilterDto.getMaxMileage(),annonceFilterDto.getMinMileageSinceModification(),
                    annonceFilterDto.getMaxMileageSinceModification(),annonceFilterDto.getMinYear(),annonceFilterDto.getMaxYear(),annonceFilterDto.getMinFiscalHorsePower(),annonceFilterDto.getMaxFiscalHorsePower(),
                    annonceFilterDto.getMinHorsePower(),annonceFilterDto.getMaxHorsePower());
        }
        //Si on a une marque un model et une category
        else if(!annonceFilterDto.getMake().isEmpty() && !annonceFilterDto.getModel().isEmpty() && !annonceFilterDto.getCategory().isEmpty()){

            allFiltered = annonceRepository.findFilteredWithMakeAndModelAndCategory(makeEntity,modelEntity,categoryEntity,annonceFilterDto.getMinPrice(),annonceFilterDto.getMaxPrice(),
                    annonceFilterDto.getMinTorque(),annonceFilterDto.getMaxTorque(),annonceFilterDto.getMinTorqueSinceModification(),annonceFilterDto.getMaxTorqueSinceModification(),
                    annonceFilterDto.getMinFuelEconomy(),annonceFilterDto.getMaxFuelEconomy(),annonceFilterDto.getMinMileage(),annonceFilterDto.getMaxMileage(),annonceFilterDto.getMinMileageSinceModification(),
                    annonceFilterDto.getMaxMileageSinceModification(),annonceFilterDto.getMinYear(),annonceFilterDto.getMaxYear(),annonceFilterDto.getMinFiscalHorsePower(),annonceFilterDto.getMaxFiscalHorsePower(),
                    annonceFilterDto.getMinHorsePower(),annonceFilterDto.getMaxHorsePower());
        }

        return DtoConverter.mapAsList(allFiltered, AnnonceDto.class);


    }


     */
    /*
    public List<AnnonceDto> getAllByFilter(String filter){
        List<AnnonceEntity> all = annonceRepository.findAll();
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    } */
}
