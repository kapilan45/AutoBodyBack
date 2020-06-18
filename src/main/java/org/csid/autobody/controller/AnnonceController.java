package org.csid.autobody.controller;

import org.csid.autobody.dto.*;
import org.csid.autobody.services.*;
import org.csid.autobody.services.MakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto, @RequestHeader("Authorization") String authorization){
        this.annonceService.saveAnnonce(annonceDto, authorization);
    }

    @PostMapping("/filtre")
    public void getAnnonceFiltred(@RequestParam("id") String id, @RequestParam("value") String value ){
        this.annonceService.getAnnonceFiltred(id, value);
    }


}
