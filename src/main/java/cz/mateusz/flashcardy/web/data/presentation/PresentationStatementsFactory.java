package cz.mateusz.flashcardy.web.data.presentation;

import cz.mateusz.flashcardy.translations.ContentTranslator;
import cz.mateusz.flashcardy.translations.model.TranslatedProperty;
import cz.mateusz.flashcardy.web.data.DataField;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class PresentationStatementsFactory {

    private ContentTranslator contentTranslator;

    public PresentationStatementsFactory(ContentTranslator contentTranslator) {
        this.contentTranslator = contentTranslator;
    }

    public Statement getSuccessStatement(String property, Locale locale) {
        Optional<TranslatedProperty> possibleTranslatedProperty = contentTranslator.translate(property, locale.getLanguage());
        if(possibleTranslatedProperty.isPresent()) {
            return new Statement(DataField.<String>of(property, possibleTranslatedProperty.get().getContent()), true);
        }
        else return new Statement(DataField.<String>of(property, ""), true);
    }

    public Statement getFailStatement(String property, Locale locale) {
        Optional<TranslatedProperty> possibleTranslatedProperty = contentTranslator.translate(property, locale.getLanguage());
        if(possibleTranslatedProperty.isPresent()) {
            return new Statement(DataField.<String>of(property, possibleTranslatedProperty.get().getContent()), false);
        }
        else return new Statement(DataField.<String>of(property, ""), false);
    }
}
