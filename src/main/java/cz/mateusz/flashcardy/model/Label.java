package cz.mateusz.flashcardy.model;

import javax.persistence.Id;
import java.util.Objects;

public class Label implements SelfCopy<Label>, DomainModel {

    @Id
    private Long id;

    private String content;

    public Label(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public Label copySelf() {
        Label copy = new Label(content);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(content, label.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
