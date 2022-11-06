package cz.mateusz.flashcardy.players.data.events;

import cz.mateusz.flashcardy.players.model.Player;
import cz.mateusz.flashcardy.sequences.SequenceName;
import cz.mateusz.flashcardy.sequences.IdSequence;
import cz.mateusz.flashcardy.sequences.NumericIdSequenceManager;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeforePlayerSaveEventListener extends AbstractMongoEventListener<Player> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforePlayerSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Player> event) {
        Player player = event.getSource();
        if(!player.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(SequenceName.PLAYERS);
            if(possibleIdSequence.isPresent()) player.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
