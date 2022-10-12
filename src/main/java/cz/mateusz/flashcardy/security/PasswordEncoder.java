package cz.mateusz.flashcardy.security;

public interface PasswordEncoder {
    String encode(String password);

    boolean compare(String plain, String encoded);
}
