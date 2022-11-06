package cz.mateusz.flashcardy.translations.model;

import cz.mateusz.flashcardy.ModelEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("languages")
public class Language extends ModelEntity {

    private String code;

    private String name;

    public Language() {}

    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
