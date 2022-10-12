package cz.mateusz.flashcardy.data;

import java.util.List;
import java.util.Optional;

public interface IdSequenceManager {

    /**
     * If sequence for a given collection doesn't exist, new IdSequence is created, saved and passed back on output.
     * Otherwise, the existing sequence is passed back on output.
     * @param collection
     * @return
     */
    Optional<IdSequence> getNextIdSequence(CollectionName collection);

    Optional<IdSequence> undoNextIdSequence(CollectionName collection);
}
