package org.csid.autobody.controller;

import org.csid.autobody.dto.CategoryDto;
import org.csid.autobody.dto.MakeDto;
import org.csid.autobody.dto.ModelDto;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.services.AnnonceService;
import org.csid.autobody.services.CategoryService;
import org.csid.autobody.services.MakeService;
import org.csid.autobody.services.ModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/filter")
public class FilterController {

    private MakeService makeService;
    private ModelService modelService;
    private CategoryService categoryService;

    public FilterController(MakeService makeService, ModelService modelService, CategoryService categoryService, AnnonceService annonceService) {
        this.makeService = makeService;
        this.modelService = modelService;
        this.categoryService = categoryService;
    }

    @GetMapping("/makes")
    public List<MakeDto> getAllMakes(){
        return makeService.getAllMakes();
    }

    @GetMapping("/models")
    public List<ModelDto> getModelWithMake(@RequestParam(name = "make") String make){
        MakeEntity makeEntity = this.makeService.getByName(make);
        return modelService.getModelsWithMake(makeEntity);
    }

    @GetMapping("/category")
    public List<CategoryDto> getCategoriesWithModel(){
        return this.categoryService.getAllCategory();
    }

}
