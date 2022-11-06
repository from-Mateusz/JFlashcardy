package cz.mateusz.flashcardy.players.model;

import cz.mateusz.flashcardy.model.IncorrectEmailAddressException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private static final Pattern CORRECT_EMAIL_PATTERN = Pattern.compile("\\b[a-z0-9]+[-.]?[a-z0-9]+@{1}[a-z0-9]+\\.{1}[a-z]{2,3}\\b");

    private String address;

    private Email(String address) {
        this.address = address;
    }

    public static Email createUnchecked(String address) {
        return new Email(address);
    }

    public static Email create(String address) throws IncorrectEmailAddressException {
        if(!isAddressCorrect(address)) throw new IncorrectEmailAddressException(address);
        return new Email(address);
    }

    public static boolean isAddressCorrect(String address) {
        return CORRECT_EMAIL_PATTERN.matcher(address).matches();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
