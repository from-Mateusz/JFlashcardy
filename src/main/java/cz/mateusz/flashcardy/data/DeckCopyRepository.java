package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.DeckCopy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeckCopyRepository extends MongoRepository<DeckCopy, Long> {}
