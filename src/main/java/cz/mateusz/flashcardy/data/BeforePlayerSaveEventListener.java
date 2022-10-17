package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.model.Player;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.Optional;

public class BeforePlayerSaveEventListener extends AbstractMongoEventListener<Player> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforePlayerSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Player> event) {
        Player player = event.getSource();
        if(!player.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(CollectionName.PLAYERS);
            if(possibleIdSequence.isPresent()) player.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
