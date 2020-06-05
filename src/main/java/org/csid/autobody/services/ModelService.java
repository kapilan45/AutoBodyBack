package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.ModelDto;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.repository.ModelRepository;
import org.csid.autobody.dto.MakeDto;
import org.csid.autobody.entity.MakeEntity;

import java.util.List;

public class ModelService {

    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public ModelDto saveModel(ModelDto model) {

        ModelEntity modelEntity = DtoConverter.map(model, ModelEntity.class);
        return DtoConverter.map(this.modelRepository.save(modelEntity), ModelDto.class);
    }

    public List<ModelDto> getAllModelByMake(MakeDto makeDto) {
        MakeEntity make = DtoConverter.map(makeDto, MakeEntity.class);
        List<ModelEntity> all = modelRepository.findAllModelByMake(make);
        return DtoConverter.mapAsList(all, ModelDto.class);
    }

}
