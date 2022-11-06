package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.model.UnknownPlayerException;
import cz.mateusz.flashcardy.players.model.Password;

public interface CredentialsManager {
    Password proposePassword() throws UnsafePasswordException;

    void comparePasswords(Password plainPw, Password encodedPw) throws AsymmetricalPasswordException;

    boolean changePassword(Long ownerId, Password newPassword) throws UnknownPlayerException;
}
