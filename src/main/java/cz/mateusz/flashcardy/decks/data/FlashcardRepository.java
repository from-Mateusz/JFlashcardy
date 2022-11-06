package cz.mateusz.flashcardy.decks.data;

import cz.mateusz.flashcardy.decks.Flashcard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashcardRepository extends MongoRepository<Flashcard, Long> {

}
