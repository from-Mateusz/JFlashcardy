package cz.mateusz.flashcardy.data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IdSequence {

    private String collection;

    private Long nextId;

    public IdSequence(String collection, Long nextId) {
        this.collection = collection;
        this.nextId = nextId;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }
}
