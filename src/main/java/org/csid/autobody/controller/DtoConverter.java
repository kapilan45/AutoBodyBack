package org.csid.autobody.controller;

import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.csid.autobody.dto.AnnonceDto;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoConverter {

    private static MapperFacade mapper = createMapper();

    private static MapperFacade createMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(AnnonceEntity.class, AnnonceDto.class)
                .field("category.category","category")
                .field("model.model","model")
                .field("make.make","make")
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }

    public static <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapper.map(sourceObject, destinationClass);
    }

    public static <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapper.mapAsList(source, destinationClass);
    }

}