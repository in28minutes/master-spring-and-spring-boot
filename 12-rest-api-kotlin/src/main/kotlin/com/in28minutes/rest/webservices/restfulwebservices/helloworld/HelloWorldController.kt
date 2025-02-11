package com.in28minutes.rest.webservices.restfulwebservices.helloworld

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(private val messageSource: MessageSource) {

    @GetMapping(path = ["/hello-world"])
    fun helloWorld(): String {
        return "Hello World"
    }

    @GetMapping(path = ["/hello-world-bean"])
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello World")
    }

    // Path Parameters
    // /users/{id}/todos/{id}  => /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/Ranga
    @GetMapping(path = ["/hello-world/path-variable/{name}"])
    fun helloWorldPathVariable(@PathVariable name: String?): HelloWorldBean {
        return HelloWorldBean(String.format("Hello World, %s", name))
    }

    @GetMapping(path = ["/hello-world-internationalized"])
    fun helloWorldInternationalized(): String? {
        val locale = LocaleContextHolder.getLocale()
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale)

        //return "Hello World V2";

        //1:
        //2:
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)
    }
}