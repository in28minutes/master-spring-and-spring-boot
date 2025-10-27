package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    @JsonView(Views.Public.class)
    public String filtering() throws JsonProcessingException {
        var someBean = new SomeBean("value1", "value2", "value3");

        var filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        var filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        var objectMapper = new ObjectMapper();
        objectMapper.setFilterProvider(filters);

        return objectMapper.writeValueAsString(someBean);
    }

    @GetMapping("/filtering-list")
    @JsonView(Views.Internal.class)
    public String filteringList() throws JsonProcessingException {
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        var filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        var filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        var objectMapper = new ObjectMapper();
        objectMapper.setFilterProvider(filters);

        return objectMapper.writeValueAsString(list);

    }

}
