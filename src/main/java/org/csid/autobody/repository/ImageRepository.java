package org.csid.autobody.repository;

import org.csid.autobody.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAllByOwner_Username(String username);

    List<ImageEntity> findAllByOwner_UsernameAndAnnonceIsNotNull(String username);

    Optional<ImageEntity> findByIdAndOwner_Username(Long id, String username);

    boolean existsByAnnonce_Id(Long annonceId);

    boolean existsByOwner_Id(Long ownerId);
}
