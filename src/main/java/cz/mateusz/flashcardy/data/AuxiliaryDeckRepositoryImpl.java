package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Deck;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuxiliaryDeckRepositoryImpl implements AuxiliaryDeckRepository<Long> {

    private MongoTemplate mongoTemplate;

    public AuxiliaryDeckRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Deck> findAllWithId(Long[] ids) {
        Query query = new Query();
        List<Deck> selectedDecks = mongoTemplate.find(query, Deck.class);
        return selectedDecks;
    }

    @Override
    public List<Deck> findEachDeckIncludingPhrase(String phrase) {
        if(null == phrase || phrase.isBlank()) return Collections.emptyList();
        Query query = new Query();
        Criteria criteria = new Criteria("name");
        criteria.regex("^.*[" + phrase + "]{1}.*$");
        query.addCriteria(criteria);
        List<Deck> decks = mongoTemplate.find(query, Deck.class);
        return decks;
    }
}
