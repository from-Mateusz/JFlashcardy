package cz.mateusz.flashcardy.web.validators;

import cz.mateusz.flashcardy.web.data.Data;

import java.util.List;
import java.util.Locale;

public interface DataValidator<D extends Data> {
    List<DataValidationError> validate(D data, Locale locale);
}
