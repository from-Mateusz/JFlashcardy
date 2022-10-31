package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.exception.InvalidJwtException;
import cz.mateusz.flashcardy.exception.UnsetSecretKeyException;

public interface JWTManager {

    String get(String owner) throws UnsetSecretKeyException;

    void verify(String token, String owner) throws UnsetSecretKeyException, InvalidJwtException;

}
