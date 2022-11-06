package cz.mateusz.flashcardy.translations.data;

import cz.mateusz.flashcardy.translations.model.TranslatedProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DefaultTranslationsRepository implements TranslationsRepository {

    private MongoTemplate mongoTemplate;

    public DefaultTranslationsRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<TranslatedProperty> findTranslatedProperty(String name, String languageCode) {
        Criteria criteria = new Criteria().where("name")
                                            .is(name)
                                            .and("language.code")
                                            .is(languageCode);
        Query query = new Query(criteria);
        List<TranslatedProperty> queryResults = mongoTemplate.find(query, TranslatedProperty.class);
        return Optional.ofNullable( queryResults.size() == 0 ? null : queryResults.get(0));
    }

    @Override
    public List<TranslatedProperty> findTranslatedProperties(String name) {
        Criteria criteria = new Criteria().where("name")
                                            .is(name);
        Query query = new Query(criteria);
        List<TranslatedProperty> queryResults = mongoTemplate.find(query, TranslatedProperty.class);
        return queryResults;
    }
}
