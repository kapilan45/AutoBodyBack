package org.csid.autobody.repository;

import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<AnnonceEntity, Long> {

    List<AnnonceEntity> findAllByOrderByPublishedDateAsc();

    List<AnnonceEntity> findAllByOrderByPublishedDateDesc();

    List<AnnonceEntity> findAllByOrderByPriceAsc();

    List<AnnonceEntity> findAllByOrderByPriceDesc();

    List<AnnonceEntity> findAllByPriceBetween(double min, double max);

    List<AnnonceEntity> findByUser(UserEntity user);

}
