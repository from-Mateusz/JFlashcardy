package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.players.model.Password;

public interface PasswordEncoder {
    Password encode(String password);

    Password encode(String password, String salt);

    boolean compare(Password plain, Password encoded);
}
