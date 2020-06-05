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
    public List<AnnonceDto> getAllByPublishedDate(){
        return annonceService.getAllByPublishedDate();
    }


    @GetMapping("/:user")
    public List<AnnonceDto> getAllByUser() {
        return annonceService.getAllByUser(userService.getCurrentUser());
    }


    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto){
        this.annonceService.saveAnnonce(annonceDto);
    }


    @GetMapping("/makes")
    public List<MakeDto> getAllMake(){
        return makeService.getMakes();
    }

    @GetMapping("/models")
    public List<ModelDto> getModelsByMake(@RequestParam(name = "make") String makeName){
        MakeDto make = makeService.getByMake(makeName);
        List<ModelDto> models = modelService.getAllModelByMake(make);
        return models;
    }

    @GetMapping("/category")
    public List<CategoryDto> getCategoriesByModel(@RequestBody ModelDto model){
        List<CategoryDto> categories = categoryService.getAllCategoryByModel(model);
        return categories;
    }

}
