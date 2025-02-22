package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * @param field3 @JsonIgnore
 */ //@JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeBeanFilter")
public record SomeBean(String field1,
                       String field2,
                       String field3) {

    @Override
    public String toString() {
        return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }

}
