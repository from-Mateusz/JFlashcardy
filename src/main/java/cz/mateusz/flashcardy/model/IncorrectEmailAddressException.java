package cz.mateusz.flashcardy.model;

public class IncorrectEmailAddressException extends Exception {
    public IncorrectEmailAddressException(String message) {
        super(message);
    }
}
