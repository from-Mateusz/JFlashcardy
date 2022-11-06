package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.web.data.Data;
import cz.mateusz.flashcardy.web.validators.DataValidationError;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectApiResponse<D extends Data>  {

    private HttpStatus status;

    private List<DataValidationError> errors = new ArrayList<>();

    private D content;

    public List<DataValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public ObjectApiResponse() {}

    public static <D extends Data> ObjectApiResponse<D> create(HttpStatus status, D content, List<DataValidationError> errors) {
        ObjectApiResponse<D> response = create(status, content);
        response.setErrors(errors);
        return response;
    }

    public static <D extends Data> ObjectApiResponse<D> create(HttpStatus status, D content) {
        ObjectApiResponse<D> response = new ObjectApiResponse<>();
        response.setStatus(status);
        response.setContent(content);
        return response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setErrors(List<DataValidationError> errors) {
        this.errors = errors;
    }

    int countErrors() {
        return errors.size();
    }

    public D getContent() {
        return content;
    }

    public void setContent(D content) {
        this.content = content;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
