package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabelRepository extends MongoRepository<Label, Long> {
}
