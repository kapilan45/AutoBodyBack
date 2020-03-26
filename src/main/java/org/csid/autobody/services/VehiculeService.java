package org.csid.autobody.services;

import org.csid.autobody.entity.Vehicule;
import org.csid.autobody.mapper.VehiculeMapper;
import org.csid.autobody.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository){
        this.vehiculeRepository = vehiculeRepository;
    }

}
