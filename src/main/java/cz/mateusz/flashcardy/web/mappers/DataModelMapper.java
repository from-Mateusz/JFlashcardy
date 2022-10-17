package cz.mateusz.flashcardy.web.mappers;

public interface DataModelMapper<S, D> {
    D from(S source) throws DataModelMapperException;
}
