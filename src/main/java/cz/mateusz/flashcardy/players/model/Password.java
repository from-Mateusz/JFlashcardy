package cz.mateusz.flashcardy.players.model;

import cz.mateusz.flashcardy.security.UnsafePasswordException;

import java.util.regex.Pattern;

public class Password {

    private static final Pattern SAFE_PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[*!@#$^+/])[a-zA-Z\\d*!@#$^+/]{8,}$");

    private String secret;

    private String salt;

    private boolean secured;

    public Password(String secret, boolean secured) {
        this.secret = secret;
        this.secured = secured;
    }

    public static Password unsecured(String secret) throws UnsafePasswordException {
//        if(!isSafe(secret)) throw new UnsafePasswordException(secret);
        return new Password(secret, false);
    }

    public static Password secured(String secret, String salt) {
        Password password = new Password(secret, true);
        password.setSalt(salt);
        return password;
    }

    public static boolean isSafe(String secret) {
        return SAFE_PASSWORD_PATTERN.matcher(secret).matches();
    }

    public String getSecret() {
        return secret;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isSecured() {
        return secured;
    }
}
