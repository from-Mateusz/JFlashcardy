package cz.mateusz.flashcardy.web.validators;

import cz.mateusz.flashcardy.translations.ContentTranslator;
import cz.mateusz.flashcardy.translations.model.TranslatedProperty;
import cz.mateusz.flashcardy.web.data.Data;

import java.util.Locale;
import java.util.Optional;

public abstract class AbstractDataValidator<D extends Data> implements DataValidator<D> {

    private ContentTranslator contentTranslator;

    public AbstractDataValidator(ContentTranslator contentTranslator) {
        this.contentTranslator = contentTranslator;
    }

    protected DataValidationError getFormattedValidationError(String property, Locale locale) {
        Optional<TranslatedProperty> content = contentTranslator.translate(property, locale.getLanguage());
        if(!content.isPresent()) return new DataValidationError(property, "error");
        return new DataValidationError(property, content.get().getContent());
    }
}
