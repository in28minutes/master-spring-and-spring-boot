package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * `MappingJacksonValue` is a Spring-specific class, and Quarkus does not provide a direct equivalent.
 * However, you can achieve the same functionality using Jackson’s `SimpleBeanPropertyFilter` with Quarkus.
 */
@RestController
public class FilteringController {

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/filtering") //field2
    public String filtering() throws JsonProcessingException {

        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        // Define the filter to exclude "field2"
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        // Convert SomeBean to JSON with applied filters
        return objectMapper.writer(filters).writeValueAsString(someBean);

    }

    @GetMapping("/filtering-list") //field2, field3
    public String filteringList() throws JsonProcessingException {
        List<SomeBean> list = Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6")
        );

        // Define the filter to include only "field2" and "field3"
        var filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        var filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        // Convert the list to JSON with applied filters
        return objectMapper.writer(filters).writeValueAsString(list);
    }

}
