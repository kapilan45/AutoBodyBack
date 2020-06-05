package org.csid.autobody.repository;

import org.csid.autobody.entity.CategoryEntity;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    @Query("select u from CategoryEntity where model = ?1")
    List<ModelEntity> findAllCategoryByModel(ModelEntity model);


}
