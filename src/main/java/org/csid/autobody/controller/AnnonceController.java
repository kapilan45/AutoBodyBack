package org.csid.autobody.controller;

import org.csid.autobody.dto.*;
import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.repository.AnnonceRepository;
import org.csid.autobody.repository.CategoryRepository;
import org.csid.autobody.repository.MakeRepository;
import org.csid.autobody.repository.ModelRepository;
import org.csid.autobody.services.*;
import org.csid.autobody.services.MakeService;
import org.csid.autobody.specifications.AnnoncesSpecificationsBuilder;
import org.csid.autobody.specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

    @Autowired
    private MakeRepository makeRepository;
    private ModelRepository modelRepository;
    private CategoryRepository categoryRepository;

    private final AnnonceService annonceService;
    private final MakeService makeService;
    private final ModelService modelService;
    private final CategoryService categoryService;
    private final UserService userService;

    public AnnonceController(AnnonceService annonceService, MakeService makeService, ModelService modelService, CategoryService categoryService, UserService userService) {
        this.annonceService = annonceService;
        this.makeService = makeService;
        this.modelService = modelService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public List<AnnonceDto> getAllRecent(){
        return annonceService.getAllRecent();
    }


    @GetMapping("/all")
    public List<AnnonceDto> getAll(){ return annonceService.getAll(); }

    @GetMapping("/user")
    public List<AnnonceDto> getAllByUser(@RequestHeader("Authorization") String authorization) {
        return annonceService.getAllByUser(authorization);
    }

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto, @RequestHeader("Authorization") String authorization){
        this.annonceService.saveAnnonce(annonceDto, authorization);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filtre")
    @ResponseBody
    public List<AnnonceDto> getAnnonceFiltred(@RequestParam(value = "search") String search ){
        AnnoncesSpecificationsBuilder builder = new AnnoncesSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + "&");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        builder = this.annonceService.mapAnnonceObjects(builder);

        for ( SearchCriteria searchCriteria : builder.getParams()){
            System.out.println(searchCriteria.getKey() + " : " + searchCriteria.getValue());
        }

        Specification<AnnonceEntity> spec = builder.build();

        List<AnnonceEntity> all = this.annonceService.getAnnonceFiltered(spec);
        return DtoConverter.mapAsList(all,AnnonceDto.class);
    }


}
