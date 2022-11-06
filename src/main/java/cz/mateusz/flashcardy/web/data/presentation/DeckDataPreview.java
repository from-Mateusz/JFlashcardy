package cz.mateusz.flashcardy.web.data.presentation;

import cz.mateusz.flashcardy.web.data.Data;

import java.util.List;

public class DeckDataPreview implements Data {

    private Long id;

    private String name;

    private List<FlashcardDataPreview> flashcards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FlashcardDataPreview> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<FlashcardDataPreview> flashcards) {
        this.flashcards = flashcards;
    }
}
