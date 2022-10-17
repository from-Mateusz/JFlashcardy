package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.DomainEntity;
import cz.mateusz.flashcardy.web.data.Data;

/**
 * Mappers like those map the external data (name it DTO) to the internal domain models (business).
 * Hence, a direction of the mapping is backward.
 * We can envision this as a walk with a load from the upper application layers to the lower ones.
 */
public abstract class BackwardDataModelMapper<S, D extends DomainEntity> implements DataModelMapper<S, D> {
    public abstract D from(S source) throws DataModelMapperException;
}
