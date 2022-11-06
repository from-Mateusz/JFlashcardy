package cz.mateusz.flashcardy.sequences;

public enum SequenceName {

    PLAYERS("player"),
    DECKS("deck"),
    FLASHCARDS("flashcard"),

    LABELS("label"),

    DECK_LABELS("deck_labels"),

    TRANSLATED_PROPERTY("translated_property");

    private String name;

    SequenceName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
