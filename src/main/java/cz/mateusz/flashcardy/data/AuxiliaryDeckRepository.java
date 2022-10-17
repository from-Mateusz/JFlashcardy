package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.Deck;

import java.util.List;
import java.util.Set;

public interface AuxiliaryDeckRepository<ID> {
    List<Deck> findAllWithId(ID[] ids);

    List<Deck> findEachDeckIncludingPhrase(String phrase);
}
