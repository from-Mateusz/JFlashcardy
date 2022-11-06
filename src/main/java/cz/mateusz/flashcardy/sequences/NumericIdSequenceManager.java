package cz.mateusz.flashcardy.sequences;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NumericIdSequenceManager implements IdSequenceManager {

    private static final Long DEFAULT_ID_DIFFERENCE = 10L;

    private IdSequenceRepository sequenceRepository;

    public NumericIdSequenceManager(IdSequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public Optional<IdSequence> getIdSequence(SequenceName collection) {
        Optional<IdSequence> possibleIdSequence = sequenceRepository.findSequenceByCollection(collection.getName());
        IdSequence sequence = null;
        if(possibleIdSequence.isPresent()) {
            sequence = possibleIdSequence.get();

        }
        else sequence = new IdSequence(collection.getName(), 0L);
        rotateSequence(sequence);
        sequenceRepository.save(sequence);
        return Optional.of(sequence);
    }

    /**
     * This method is to compute and set a next id for an appropriate collection.
     * Treat the sequence as an arithmetic.
     * @param sequence
     */
    private void rotateSequence(IdSequence sequence) {
        Long nextId =  Double.valueOf(Math.floor(Math.random() * DEFAULT_ID_DIFFERENCE)).longValue() + sequence.getCurrentId();
        sequence.setCurrentId(nextId);
    }

    /**
     * This method is to compute and set a next id for an appropriate collection but as an inverse function of a {@link #rotateSequence(IdSequence)}
     * Treat the sequence as an arithmetic.
     * @param sequence
     */
    private void rotateBackSequence(IdSequence sequence) {
        sequence.setCurrentId(sequence.getCurrentId() - DEFAULT_ID_DIFFERENCE);
    }

    @Override
    public Optional<IdSequence> undoNextIdSequence(SequenceName collection) {
        Optional<IdSequence> sequence = sequenceRepository.findSequenceByCollection(collection.getName());
        if(sequence.isPresent()) rotateBackSequence(sequence.get());
        return sequence;
    }
}
