package cz.mateusz.flashcardy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Explanation implements Cloneable, SelfCopy<Explanation> {

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

    public void addExample(Example example) {
        this.examples.add(example);
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
