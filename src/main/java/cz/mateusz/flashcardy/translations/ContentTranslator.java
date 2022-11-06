package cz.mateusz.flashcardy.translations;

import cz.mateusz.flashcardy.translations.model.TranslatedProperty;

import java.util.List;
import java.util.Optional;

public interface ContentTranslator {
    Optional<TranslatedProperty> translate(String propertyName, String languageCode);
    List<TranslatedProperty> translateMultiLanguage(String propertyName);
}
