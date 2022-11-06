package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.model.DomainException;

public class UnsafePasswordException extends DomainException {
    public UnsafePasswordException(String message) {
        super(message);
    }
}
