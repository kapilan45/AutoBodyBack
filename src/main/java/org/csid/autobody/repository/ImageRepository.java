package org.csid.autobody.repository;

import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.ImageEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
