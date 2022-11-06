package cz.mateusz.flashcardy.translations.data;

import cz.mateusz.flashcardy.translations.model.TranslatedProperty;

import java.util.List;
import java.util.Optional;

public interface TranslationsRepository {
    Optional<TranslatedProperty> findTranslatedProperty(String name, String languageCode);

    List<TranslatedProperty> findTranslatedProperties(String name);
}
