package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.VehiculeDto;
import org.csid.autobody.entity.VehiculeEntity;
import org.csid.autobody.repository.VehiculeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository){
        this.vehiculeRepository = vehiculeRepository;
    }

    public List<VehiculeDto> getAll() {
        List<VehiculeEntity> all = vehiculeRepository.findAll();
        return DtoConverter.mapAsList(all, VehiculeDto.class);
    }

   /* TODO public Vehicule get(Long id){
       try{
           return VehiculeMapper.toVehicule(vehiculeRepository.getOne(id));
       } catch(PersistenceException ex){
           throw new ObjectNotFoundException("Vehicule with id " + id + " not found");
       }
    }

    public Vehicule create(Vehicule vehicule) {
        return VehiculeMapper.toVehicule(vehiculeRepository.save(VehiculeMapper.toVehicule(vehicule)));
    }



    public Vehicule update(Vehicule vehicule) {
        if(!vehiculeRepository.existsById(vehicule.getId())) {
            throw new ObjectNotFoundException("Vehicule with id " + vehicule.getId() + " does not exist");
        }
        VehiculeEntity savedVehicule = vehiculeRepository.save(VehiculeMapper.toVehicule((vehicule)));
        return VehiculeMapper.toVehicule(savedVehicule);
    }

    public Vehicule partialUpdate(Vehicule vehicule) {
        if(!vehiculeRepository.existsById(vehicule.getId())) {
            throw new ObjectNotFoundException("Vehicule with id " + vehicule.getId() + " does not exist");
        }

        Vehicule currentVehicule = VehiculeMapper.toVehicule(vehiculeRepository.getOne(vehicule.getId()));
        Vehicule mergedVehicule = currentVehicule.mergeWith(vehicule);

        VehiculeEntity newEmployee = vehiculeRepository.save(VehiculeMapper.toVehicule(mergedVehicule));

        return VehiculeMapper.toVehicule(newEmployee);
    }

    public void delete(Long id) {
        if(vehiculeRepository.existsById(id)) {
            vehiculeRepository.deleteById(id);
        }
    }*/

}
