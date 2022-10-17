package cz.mateusz.flashcardy.data;

public enum CollectionName {

    PLAYERS("player"),
    DECKS("deck"),
    FLASHCARDS("flashcard"),

    LABELS("label"),

    DECK_LABELS("deck_labels");

    private String name;

    CollectionName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
