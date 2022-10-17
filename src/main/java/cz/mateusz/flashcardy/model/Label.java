package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Label extends DomainEntity implements SelfCopy<Label> {

    @Indexed(unique = true)
    private String content;

    public Label(String content) {
        this.content = content;
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
