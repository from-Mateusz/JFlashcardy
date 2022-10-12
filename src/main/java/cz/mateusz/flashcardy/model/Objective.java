package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Objective implements Cloneable, SelfCopy<Objective> {

    private String content;

    public Objective(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Objective clone = (Objective) super.clone();
        return clone;
    }

    @Override
    public Objective copySelf() {
        Objective copy = new Objective(content);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(content, objective.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
