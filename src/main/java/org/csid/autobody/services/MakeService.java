package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.MakeDto;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.repository.MakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeService {

    private MakeRepository makeRepository;

    public MakeService(MakeRepository makeRepository) {
        this.makeRepository = makeRepository;
    }

    public MakeDto saveModel(MakeDto make) {
        MakeEntity makeEntity = DtoConverter.map(make, MakeEntity.class);
        return DtoConverter.map(this.makeRepository.save(makeEntity), MakeDto.class);
    }

    public MakeEntity getByName(String makeName){
        MakeEntity make = this.makeRepository.findByMake(makeName);
        return make;
    }

    public List<MakeDto> getAllMakes() {
        List<MakeEntity> makes = this.makeRepository.findAll();
        return DtoConverter.mapAsList(makes, MakeDto.class);
    }
}

