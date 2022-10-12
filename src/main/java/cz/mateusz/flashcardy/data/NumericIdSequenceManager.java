package cz.mateusz.flashcardy.data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NumericIdSequenceManager implements IdSequenceManager {

    private static final int DEFAULT_ID_DIFFERENCE = 10;

    private IdSequenceRepository sequenceRepository;

    public NumericIdSequenceManager(IdSequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public Optional<IdSequence> getNextIdSequence(CollectionName collection) {
        Optional<IdSequence> possibleIdSequence = sequenceRepository.findSequenceByCollection(collection.getName());
        IdSequence sequence = null;
        if(possibleIdSequence.isPresent()) {
            sequence = possibleIdSequence.get();
            rotateSequence(sequence);
        }
        else {
            sequence = new IdSequence(collection.getName(), 1L);
            sequence = sequenceRepository.save(sequence);
        }
        return Optional.of(sequence);
    }

    /**
     * This method is to compute and set a next id for an appropriate collection.
     * Treat the sequence as an arithmetic.
     * @param sequence
     */
    private void rotateSequence(IdSequence sequence) {
        sequence.setNextId(sequence.getNextId() + DEFAULT_ID_DIFFERENCE);
    }

    /**
     * This method is to compute and set a next id for an appropriate collection but as an inverse function of a {@link #rotateSequence(IdSequence)}
     * Treat the sequence as an arithmetic.
     * @param sequence
     */
    private void rotateBackSequence(IdSequence sequence) {
        sequence.setNextId(sequence.getNextId() - DEFAULT_ID_DIFFERENCE);
    }

    @Override
    public Optional<IdSequence> undoNextIdSequence(CollectionName collection) {
        Optional<IdSequence> sequence = sequenceRepository.findSequenceByCollection(collection.getName());
        if(sequence.isPresent()) rotateBackSequence(sequence.get());
        return sequence;
    }
}
