package cz.mateusz.flashcardy.model;

public interface CredentialsManager {
    Password proposePassword() throws UnsafePasswordException;

    boolean changePassword(Long ownerId, Password newPassword) throws UnknownPlayerException;
}
