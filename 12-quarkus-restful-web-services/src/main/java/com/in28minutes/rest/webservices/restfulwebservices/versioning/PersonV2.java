package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public record PersonV2(Name name) {

    @Override
    public String toString() {
        return "PersonV2 [name=" + name + "]";
    }

}
