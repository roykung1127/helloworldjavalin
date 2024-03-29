package app.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Localizer {
    public static final ConcurrentMap<Locale, Localizer> byLocale = new ConcurrentHashMap<>();

    public static Localizer getInstance(@NotNull String languageTag) {
        Locale locale = new Locale(languageTag);
        return getInstance(locale);
    }

    public static Localizer getInstance(@NotNull Locale locale) {
        return byLocale.computeIfAbsent(locale, k -> {
            ResourceBundle bundle = ResourceBundle.getBundle("localization/messages", k);
            return new Localizer(bundle);
        });
    }

    public final ResourceBundle bundle;

    public Localizer(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String localize(String key) {
        return bundle.getString(key);
    }
}
