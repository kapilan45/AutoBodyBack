package org.csid.autobody.repository;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<ModelEntity, String> {

    @Query("select u from ModelEntity u where u.make = ?1")
    List<ModelEntity> findAllModelByMake(String make);

    }

