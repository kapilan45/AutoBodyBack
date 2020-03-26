package org.csid.autobody.repository;

import org.csid.autobody.entity.VehiculeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<VehiculeEntity, Long> {
}
