package org.csid.autobody.controller;

import org.csid.autobody.dto.*;
import org.csid.autobody.services.*;
import org.csid.autobody.services.MakeService;
import org.springframework.web.bind.annotation.*;
import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.specifications.AnnoncesSpecificationsBuilder;
import org.csid.autobody.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

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


    @GetMapping("/user")
    public List<AnnonceDto> getAllByUser(@RequestHeader("Authorization") String authorization) {
        return annonceService.getAllByUser(authorization);
    }

    @GetMapping("/publishedAsc")
    public List<AnnonceDto> getAllByPublishedDateAsc(){
        return annonceService.getAllByPublishedDateAsc();
    }

    @GetMapping("/publishedDesc")
    public List<AnnonceDto> getAllByPublishedDateDesc(){
        return annonceService.getAllByPublishedDateDesc();
    }

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto, @RequestHeader("Authorization") String authorization){
        this.annonceService.saveAnnonce(annonceDto, authorization);
    }

    @GetMapping("/basicfilter")
    public List<AnnonceDto> basicFilter(@RequestParam("basicFilter") String filter){
        System.out.println(filter);
        List<AnnonceDto> all;
        if(filter.equals("croissant")){
            all = annonceService.getByHighPrice();
        }else if (filter.equals("decroissant")){
            all = annonceService.getByLessPrice();
        }else if (filter.equals("anciennes")) {
            all = annonceService.getAllDecroissant();
        }else {
            // récentes
            all = annonceService.getAllRecent();
        }
        return all;
    }

    @PutMapping("/delete")
    public List<AnnonceDto> deleteAnnonce(@RequestBody AnnonceDto annonceDto, @RequestHeader("Authorization") String authorization){
        return this.annonceService.deleteAnnonce(annonceDto, authorization);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filtre")
    @ResponseBody
    public List<AnnonceDto> getAnnonceFiltred(@RequestParam(value = "search") String search ) {
        AnnoncesSpecificationsBuilder builder = new AnnoncesSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        builder = this.annonceService.mapAnnonceObjects(builder);

        for (SearchCriteria searchCriteria : builder.getParams()) {
            System.out.println(searchCriteria.getKey() + " : " + searchCriteria.getValue());
        }

        Specification<AnnonceEntity> spec = builder.build();

        List<AnnonceEntity> all = this.annonceService.getAnnonceFiltered(spec);
        return DtoConverter.mapAsList(all, AnnonceDto.class);
    }
}
