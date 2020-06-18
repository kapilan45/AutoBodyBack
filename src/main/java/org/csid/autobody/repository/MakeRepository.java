package org.csid.autobody.repository;

import org.csid.autobody.entity.MakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MakeRepository extends JpaRepository<MakeEntity, Long> {

    MakeEntity findByMake(String makeName);
}
