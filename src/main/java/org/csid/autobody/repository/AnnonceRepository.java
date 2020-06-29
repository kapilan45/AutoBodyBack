package org.csid.autobody.repository;

import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<AnnonceEntity, Long>,JpaSpecificationExecutor<AnnonceEntity> {

    List<AnnonceEntity> findAll();

    List<AnnonceEntity> findAllByOrderByPublishedDateAsc();

    List<AnnonceEntity> findAllByOrderByPublishedDateDesc();

    List<AnnonceEntity> findAllByOrderByPriceAsc();

    List<AnnonceEntity> findAllByOrderByPriceDesc();

    List<AnnonceEntity> findAllByUser(UserEntity user);

}
