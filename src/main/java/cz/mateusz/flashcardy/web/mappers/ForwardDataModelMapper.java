package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.DomainEntity;
import cz.mateusz.flashcardy.model.DomainException;
import cz.mateusz.flashcardy.web.data.Data;

/**
 * Mappers like those map the domain models (business) to the external data (name it DTO).
 * Hence, a direction of the mapping is forward.
 * We can envision this as a walk with a load from the lower application layers to the upper ones.
 */
public abstract class ForwardDataModelMapper<S, D extends Data> implements DataModelMapper<S, D> {
    public abstract D from(S source) throws DataModelMapperException, DomainException;
}
