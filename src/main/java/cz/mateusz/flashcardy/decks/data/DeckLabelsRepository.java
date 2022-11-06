package cz.mateusz.flashcardy.decks.data;

import cz.mateusz.flashcardy.decks.DeckLabels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeckLabelsRepository extends MongoRepository<DeckLabels, Long> {
    @Query("{'deck.id:'?0}")
    List<DeckLabels> findAllByDeckId(Long deckId);
}
