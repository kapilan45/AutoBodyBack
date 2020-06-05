package org.csid.autobody.repository;

import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<AnnonceEntity, Long> {

    List<AnnonceEntity> findAllByOrderByPublishedDateAsc();

    List<AnnonceEntity> findAllByOrderByPublishedDateDesc();

    @Query("select u from AnnonceEntity u where u.user = ?1")
    List<AnnonceEntity> findByUser(Long userId);

    @Query("SELECT u from AnnonceEntity u where u.price > ?1 and u.price < ?2")
    List<AnnonceEntity> findByPriceBetween(int minPrix, int maxPrix);
}
