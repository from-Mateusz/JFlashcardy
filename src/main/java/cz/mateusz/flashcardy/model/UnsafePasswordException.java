package cz.mateusz.flashcardy.model;

public class UnsafePasswordException extends Exception {
    public UnsafePasswordException(String message) {
        super(message);
    }
}
