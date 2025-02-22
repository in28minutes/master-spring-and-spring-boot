package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;

import java.util.Locale;

/**
 * A service class responsible for retrieving localized messages
 * from resource bundles based on the given locale.
 *
 * <p>
 * This class is marked as {@code @RequestScoped}, meaning a new instance
 * will be created for each HTTP request.
 * </p>
 * @RequestScoped is equivalent to {@code @Scope(WebApplicationContext.SCOPE_REQUEST)} in Spring
 * <a href="https://quarkus.io/guides/cdi#bean-scope-available">RequestScoped</a>
 */
@RequestScoped
public class LocaleService {

    /**
     * Injected instance of {@link HttpHeaders} to access HTTP request headers.
     * This allows the service to retrieve language preferences sent by the client.
     */
    @Inject
    HttpHeaders headers;

    /**
     * Retrieves the preferred locale from the HTTP request headers.
     * If no preferred language is specified in the request,
     * it falls back to the system's default locale.
     *
     * @return the locale preferred by the client, or the system default if none is provided.
     */
    public Locale getCurrentLocale() {
        return headers.getAcceptableLanguages().isEmpty()
                ? Locale.getDefault()
                : headers.getAcceptableLanguages().getFirst();
    }
}
