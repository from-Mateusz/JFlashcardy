package cz.mateusz.flashcardy.decks;

import cz.mateusz.flashcardy.model.DomainEntity;
import cz.mateusz.flashcardy.helpers.SelfCopy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Explanation extends DomainEntity implements Cloneable, SelfCopy<Explanation> {

    private String content;

    private List<Example> examples;

    public Explanation() {}

    public Explanation(String content) {
        this.content = content;
        this.examples = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public void editContent(String content) {
        this.content = content;
    }

    public boolean addExample(Example example) {
        return this.examples.add(example);
    }

    public boolean addExamples(List<Example> examples) {
        return this.examples.addAll(examples);
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void clearExamples() {
        this.examples.clear();;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Explanation clone = (Explanation) super.clone();
        clone.clearExamples();
        for(Example example : examples) clone.addExample(new Example(example.getContent()));
        return clone;
    }

    @Override
    public Explanation copySelf() {
        Explanation explanation = new Explanation(content);
        for(Example example : examples) explanation.addExample(example.copySelf());
        return explanation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Explanation that = (Explanation) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
