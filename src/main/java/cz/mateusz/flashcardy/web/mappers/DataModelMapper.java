package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.DomainException;

public interface DataModelMapper<S, D> {
    D from(S source) throws DataModelMapperException, DomainException;
}
