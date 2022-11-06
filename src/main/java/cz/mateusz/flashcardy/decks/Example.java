package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.helpers.SelfCopy;

import java.util.Objects;

public class Example implements Cloneable, SelfCopy<Example> {

    private String content;

    public Example(String content) {
        this.content = content;
    }

    public void editContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Example copySelf() {
        Example copy = new Example(content);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return Objects.equals(content, example.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
