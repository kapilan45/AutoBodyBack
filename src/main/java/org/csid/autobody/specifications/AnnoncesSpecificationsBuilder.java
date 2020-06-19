package org.csid.autobody.specifications;

import org.csid.autobody.entity.AnnonceEntity;
import org.csid.autobody.entity.CategoryEntity;
import org.csid.autobody.entity.MakeEntity;
import org.csid.autobody.entity.ModelEntity;
import org.csid.autobody.repository.CategoryRepository;
import org.csid.autobody.repository.MakeRepository;
import org.csid.autobody.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnnoncesSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public AnnoncesSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public AnnoncesSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<AnnonceEntity> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(AnnonceSpecifications::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 0; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .and(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }

    public List<SearchCriteria> getParams() {
        return params;
    }
}
