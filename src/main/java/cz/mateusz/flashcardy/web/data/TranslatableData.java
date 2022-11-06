package cz.mateusz.flashcardy.web.data;

import java.util.Objects;

public class TranslatableData implements Data {

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslatableData that = (TranslatableData) o;
        return Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language);
    }
}
