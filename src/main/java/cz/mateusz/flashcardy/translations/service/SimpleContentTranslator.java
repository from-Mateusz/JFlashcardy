package cz.mateusz.flashcardy.translations.service;

import cz.mateusz.flashcardy.translations.ContentTranslator;
import cz.mateusz.flashcardy.translations.data.TranslationsRepository;
import cz.mateusz.flashcardy.translations.model.TranslatedProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleContentTranslator implements ContentTranslator {

    private TranslationsRepository translationsRepository;

    public SimpleContentTranslator(TranslationsRepository translationsRepository) {
        this.translationsRepository = translationsRepository;
    }

    @Override
    public Optional<TranslatedProperty> translate(String propertyName, String languageCode) {
        return translationsRepository.findTranslatedProperty(propertyName, languageCode);
    }

    @Override
    public List<TranslatedProperty> translateMultiLanguage(String propertyName) {
        return translationsRepository.findTranslatedProperties(propertyName);
    }
}
