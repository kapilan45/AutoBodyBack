package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.CategoryDto;
import org.csid.autobody.dto.MakeDto;
import org.csid.autobody.dto.ModelDto;
import org.csid.autobody.entity.CategoryEntity;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.repository.CategoryRepository;
import org.csid.autobody.repository.MakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto saveCategory(CategoryDto category) {
        CategoryEntity categoryEntity = DtoConverter.map(category, CategoryEntity.class);
        return DtoConverter.map(this.categoryRepository.save(categoryEntity), CategoryDto.class);
    }

    public List<CategoryDto> getAllCategory() {
        List<CategoryEntity> categories = this.categoryRepository.findAll();
        return DtoConverter.mapAsList(categories, CategoryDto.class);
    }

    /*public List<CategoryDto> getAllCategoryByModel(ModelEntity model) {
        List<CategoryEntity> all = categoryRepository.findAllByModel(model);
        return DtoConverter.mapAsList(all, CategoryDto.class);
    }*/

}
