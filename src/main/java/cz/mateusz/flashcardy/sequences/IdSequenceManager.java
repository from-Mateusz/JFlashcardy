package cz.mateusz.flashcardy.sequences;

import java.util.Optional;

public interface IdSequenceManager {

    /**
     * If sequence for a given collection doesn't exist, new IdSequence is created, saved and passed back on output.
     * Otherwise, the existing sequence is passed back on output.
     * @param collection
     * @return
     */
    Optional<IdSequence> getIdSequence(SequenceName collection);

    Optional<IdSequence> undoNextIdSequence(SequenceName collection);
}
