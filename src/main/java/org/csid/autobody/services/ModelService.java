package org.csid.autobody.services;

import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.ModelDto;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.repository.ModelRepository;
import org.csid.autobody.dto.MakeDto;
import org.csid.autobody.entity.MakeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public ModelDto saveModel(ModelDto model) {
        ModelEntity modelEntity = DtoConverter.map(model, ModelEntity.class);
        return DtoConverter.map(this.modelRepository.save(modelEntity), ModelDto.class);
    }

    public List<ModelDto> getModelsWithMake(MakeEntity make) {
        List<ModelEntity> models = modelRepository.findAllByMake(make);
        System.out.println(models.size());
        return DtoConverter.mapAsList(models, ModelDto.class);
    }

    public ModelEntity getOneModel(String model) {
        return this.modelRepository.findByModel(model);
    }
}
