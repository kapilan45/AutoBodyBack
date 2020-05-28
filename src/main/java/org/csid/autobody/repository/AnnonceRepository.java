package org.csid.autobody.repository;

import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<AnnonceEntity, Long> {

    // search by last published date
    //List<AnnonceEntity> findByPublishedDate ();

    //List<AnnonceEntity> findAllByOrderByPublishedDateAsc();

    // search all annonces of specified user
    //List<AnnonceEntity> findByUser(UserEntity user);

    //@Query("SELECT a from AnnonceEntity a where a.prix > ?1 and a.prix < ?2")
   // List<AnnonceEntity> findByPrixBetween(int minPrix, int maxPrix);
}
