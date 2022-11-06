package cz.mateusz.flashcardy.decks.data;

import cz.mateusz.flashcardy.decks.Deck;

import java.util.List;

public interface AuxiliaryDeckRepository<ID> {
    List<Deck> findAllWithId(ID[] ids);

    List<Deck> findEachDeckIncludingPhrase(String phrase);
}
