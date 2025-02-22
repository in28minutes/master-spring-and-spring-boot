package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Quarkus does not provide `org.springframework.context.i18n.LocaleContextHolder`, as that is specific to Spring.
 * Hence, we have manually created `LocaleService` and `MessageService`
 * 1. Using `Locale` in CDI Beans
 * 2. Manually Load Properties for i18n from MessageService
 */
@RestController
public class HelloWorldController {

    /**
     * Service for retrieving localized messages based on the current locale.
     */
    private final MessageService messageService;

    /**
     * Service for determining the locale of the current request.
     */
    private final LocaleService localeService;

    /**
     * Constructs a {@code HelloWorldController} with dependencies on
     * {@code MessageService} and {@code LocaleService}.
     *
     * @param messageService the service responsible for fetching localized messages.
     * @param localeService  the service responsible for determining the request's locale.
     */
    public HelloWorldController(MessageService messageService,
                                LocaleService localeService) {
        this.messageService = messageService;
        this.localeService = localeService;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // Path Parameters
    // /users/{id}/todos/{id}  => /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/Ranga

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = localeService.getCurrentLocale();
        return messageService.getMessage("good.morning.message", locale);

        //return "Hello World V2";

        //1:
        //2:
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)

    }


}
