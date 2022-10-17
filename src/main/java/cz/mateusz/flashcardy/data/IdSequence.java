package cz.mateusz.flashcardy.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IdSequence {

    @Id
    private String collection;

    private Long currentId;

    public IdSequence(String collection, Long currentId) {
        this.collection = collection;
        this.currentId = currentId;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
    }
}
