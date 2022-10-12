package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Deck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
public interface DeckRepository extends MongoRepository<Deck, Long> {

    @Query("{id:'?0'}")
    List<Deck> findByOwnerId(Long ownerId);

    @Query("{ownerId:'?0', published:'?1'}")
    List<Deck> findPublishedByOwnerId(Long ownerId, Boolean published);

    @Query("{published:'?0'}")
    List<Deck> findPublished(Boolean published);
}
