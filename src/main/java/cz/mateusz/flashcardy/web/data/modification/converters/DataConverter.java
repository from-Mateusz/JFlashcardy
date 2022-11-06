package cz.mateusz.flashcardy.web.data.modification.converters;

import cz.mateusz.flashcardy.ModelEntity;
import cz.mateusz.flashcardy.web.data.Data;

public interface DataConverter<FROM extends Data, TO extends ModelEntity> {
    TO convert(FROM data);
}
