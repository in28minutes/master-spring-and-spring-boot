package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A service class responsible for retrieving localized messages
 * from resource bundles based on the given locale.
 *
 * <p>
 * This class is marked as {@code @ApplicationScoped}, meaning a single
 * instance of it will be shared across the entire application.
 * </p>
 */
@ApplicationScoped
public class MessageService {

    /**
     * Retrieves a localized message for the given key using a resource bundle.
     *
     * @param key    the key for the desired message.
     * @param locale the locale for which the message should be retrieved.
     * @return the localized message corresponding to the given key.
     * @throws java.util.MissingResourceException if the key is not found in the resource bundle.
     */
    public String getMessage(String key, Locale locale) {
        var bundle = ResourceBundle.getBundle("messages", locale);
        return bundle.getString(key);
    }
}
