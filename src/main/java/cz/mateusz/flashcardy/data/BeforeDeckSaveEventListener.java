package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Deck;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class BeforeDeckSaveEventListener extends AbstractMongoEventListener<Deck> {

    private NumericIdSequenceManager idSequenceManager;

    public BeforeDeckSaveEventListener(NumericIdSequenceManager idSequenceManager) {
        this.idSequenceManager = idSequenceManager;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Deck> event) {
        Deck deck = event.getSource();
        if(!deck.hasId()) {
            Optional<IdSequence> possibleIdSequence = idSequenceManager.getIdSequence(CollectionName.DECKS);
            if(possibleIdSequence.isPresent()) deck.setId(possibleIdSequence.get().getCurrentId());
        }
    }
}
