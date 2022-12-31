package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Eliminate verbosity in creating Java Beans
//Public accessor methods, constructor,
//equals, hashcode and toString are automatically created.
//Released in JDK 16.

record Person(String name, int age, Address address) { };

record Address(String firstLine, String city) { };

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String name() {
        return "Omer";
    }

    @Bean
    public int age() {
        return 25;
    }

    @Bean
    public Person person() {
        return new Person("Omer", 35, new Address("Abc", "Istanbul"));
    }

    @Bean
    public Person person2MethodCall() {
        return new Person(name(), age(), address());
    }

    @Bean(name = "address2")
    public Address address() {
        return new Address("Abc", "Istanbul");
    }

}
