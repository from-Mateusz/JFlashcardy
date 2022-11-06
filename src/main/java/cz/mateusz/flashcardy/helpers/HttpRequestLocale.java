package cz.mateusz.flashcardy.helpers;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class HttpRequestLocale {
    public static Locale getLocale(HttpServletRequest req) {
        final String localeHeader = req.getHeader("X-app-language");
        if(localeHeader == null) return Locale.ENGLISH;
        final String[] localeData = localeHeader.split(",");
        return new Locale(localeData[0].trim(), localeData[1].trim());
    }
}
