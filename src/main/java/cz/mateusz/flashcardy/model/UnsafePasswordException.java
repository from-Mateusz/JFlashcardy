package cz.mateusz.flashcardy.model;

public class UnsafePasswordException extends DomainException {
    public UnsafePasswordException(String message) {
        super(message);
    }
}
