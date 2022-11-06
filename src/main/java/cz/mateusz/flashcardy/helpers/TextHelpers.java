package cz.mateusz.flashcardy.helpers;

public class TextHelpers {
    public static boolean exists(String text) {
        return (text != null) && !text.isBlank();
    }
}
