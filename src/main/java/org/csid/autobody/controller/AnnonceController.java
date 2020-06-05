package org.csid.autobody.controller;

import org.csid.autobody.dto.*;
import org.csid.autobody.entity.CategoryEntity;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.entity.UserEntity;
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

    public AnnonceController(AnnonceService annonceService, MakeService makeService, ModelService modelService,CategoryService categoryService) {
        this.annonceService = annonceService;
        this.makeService = makeService;
        this.modelService = modelService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<AnnonceDto> getAllByPublishedDate(){
        return annonceService.getAllByPublishedDate();
    }

  /* TODO

    @GetMapping("/:user")
    public List<AnnonceDto> getAllByUser(UserDto user) {
        return annonceService.getAllByUser(user);
    }
*/

    @PostMapping("/save")
    public void saveAnnonce(@RequestBody AnnonceDto annonceDto, @RequestHeader(name = "userToken") String userToken){

        this.annonceService.saveAnnonce(annonceDto, userToken);
    }


    @GetMapping("/makes")
    public List<MakeDto> getAllMake(){
        return makeService.getMakes();
    }

    @GetMapping("/models")
    public String getModelsByMake(@RequestParam(name = "make") String marque){
        //List<ModelDto> models = modelService.getAllModelByMake(make);
        return marque;
    }

    @GetMapping("/category")
    public List<CategoryDto> getCategoriesByModel(@RequestBody ModelDto model){
        List<CategoryDto> categories = categoryService.getAllCategoryByModel(model);
        return categories;
    }

}
