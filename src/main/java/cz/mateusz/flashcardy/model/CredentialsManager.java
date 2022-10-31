package cz.mateusz.flashcardy.model;

public interface CredentialsManager {
    Password proposePassword() throws UnsafePasswordException;

    void comparePasswords(Password plainPw, Password encodedPw) throws AsymmetricalPasswordException;

    boolean changePassword(Long ownerId, Password newPassword) throws UnknownPlayerException;
}
