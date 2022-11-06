package cz.mateusz.flashcardy.decks.data;

import cz.mateusz.flashcardy.decks.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabelRepository extends MongoRepository<Label, Long> {
}
