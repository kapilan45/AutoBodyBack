package org.csid.autobody.repository;

import org.csid.autobody.entity.CategoryEntity;
import org.csid.autobody.entity.LocalisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalisationRepository extends JpaRepository<LocalisationEntity, String> {
}
