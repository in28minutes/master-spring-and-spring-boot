package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public record Name(String firstName,
                   String lastName) {

    @Override
    public String toString() {
        return "Name [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}
