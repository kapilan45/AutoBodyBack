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

    public List<CategoryDto> getAllCategoryByModel(ModelDto modelDto) {
        ModelEntity model = DtoConverter.map(modelDto, ModelEntity.class);
        List<CategoryEntity> all = categoryRepository.findAllCategoryByModel(model.getModel());
        return DtoConverter.mapAsList(all, CategoryDto.class);
    }

}
