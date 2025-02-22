package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public record PersonV1(String name) {

    @Override
    public String toString() {
        return "PersonV1 [name=" + name + "]";
    }

}
