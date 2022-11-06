package cz.mateusz.flashcardy.sequences;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface IdSequenceRepository extends MongoRepository<IdSequence, Long> {
    @Query("{collection:'?0'}")
    Optional<IdSequence> findSequenceByCollection(String collection);
}
