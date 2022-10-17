package cz.mateusz.flashcardy.data;

import java.util.Optional;

public interface AuxiliaryIdSequenceRepository {
    Optional<IdSequence> save(IdSequence sequence);
}
