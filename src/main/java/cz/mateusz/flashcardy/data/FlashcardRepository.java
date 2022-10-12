package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Flashcard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashcardRepository extends MongoRepository<Flashcard, Long> {

}
