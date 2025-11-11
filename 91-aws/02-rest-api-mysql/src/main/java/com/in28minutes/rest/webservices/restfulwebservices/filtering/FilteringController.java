package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
// import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.ser.FilterProvider;
import tools.jackson.databind.ser.std.SimpleBeanPropertyFilter;
import tools.jackson.databind.ser.std.SimpleFilterProvider;

// BEFORE SPRING BOOT 4
// import com.fasterxml.jackson.databind.ser.FilterProvider;
// import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
// import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering") //field2
    @JsonView(Views.Public.class)
    public String filtering() {

        // SomeBean someBean = new SomeBean("value1","value2", "value3");
		var someBean = new SomeBean("value1","value2", "value3");

		// MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		// 	SimpleBeanPropertyFilter filter =
        //				SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		var filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");

        // FilterProvider filters =
        //				new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		var filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        var jsonMapper = JsonMapper.builder().build();

		// mappingJacksonValue.setFilters(filters );
        // return mappingJacksonValue
        return jsonMapper.writer(filters).writeValueAsString(someBean);
	}

	@GetMapping("/filtering-list") //field2, field3
	public String filteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2", "value3"),
				new SomeBean("value4","value5", "value6"));
		// MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		// SimpleBeanPropertyFilter filter =
		//		SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        var filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		// FilterProvider filters =
		//		new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
        var filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		// mappingJacksonValue.setFilters(filters );
        var jsonMapper = JsonMapper.builder().build();

		// return mappingJacksonValue;
        return jsonMapper.writer(filters).writeValueAsString(list);
	}

}
