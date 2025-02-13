/**
 * A Spring Boot REST Controller that demonstrates dynamic JSON filtering using Jackson.
 *
 * ## Features:
 * - **Dynamic JSON Filtering with Jackson**:
 *   - Uses `SimpleBeanPropertyFilter.filterOutAllExcept(...)` to filter fields dynamically.
 *   - Applies `@JsonFilter("SomeBeanFilter")` on `SomeBean` for selective serialization.
 * - **Spring Boot REST Endpoints**:
 *   - `/filtering`: Returns a `SomeBean` instance with only `field1` and `field3`.
 *   - `/filtering-list`: Returns a list of `SomeBean` objects with only `field2` and `field3`.
 * - **Kotlin Features**:
 *   - **Data Class for JSON Representation** (`SomeBean`):
 *     ```kotlin
 *     @JsonFilter("SomeBeanFilter")
 *     data class SomeBean(val field1: String, val field2: String, val field3: String)
 *     ```
 *   - **List Creation using `listOf(...)`**:
 *     ```kotlin
 *     val list = listOf(
 *         SomeBean("value1", "value2", "value3"),
 *         SomeBean("value4", "value5", "value6")
 *     )
 *     ```
 *   - **Spring Boot Controller & Mapping**:
 *     ```kotlin
 *     @RestController
 *     class FilteringController
 *     ```
 *
 * ## Usage:
 * 1. **Call `/filtering`** → Returns JSON with `field1` and `field3` only.
 * 2. **Call `/filtering-list`** → Returns a list of objects with `field2` and `field3` only.
 * 3. **Modify the filters dynamically** by adjusting `filterOutAllExcept(...)`.
 *
 * @see SomeBean
 * @see MappingJacksonValue
 */
package com.in28minutes.rest.webservices.restfulwebservices.filtering

import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FilteringController {

    @GetMapping("/filtering") //field2
    fun filtering(): MappingJacksonValue {
        val someBean = SomeBean("value1", "value2", "value3")
        val mappingJacksonValue = MappingJacksonValue(someBean)
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3")
        val filters: FilterProvider = SimpleFilterProvider().addFilter("SomeBeanFilter", filter)
        mappingJacksonValue.filters = filters

        return mappingJacksonValue
    }

    @GetMapping("/filtering-list") //field2, field3
    fun filteringList(): MappingJacksonValue {
        val list = listOf(
            SomeBean("value1", "value2", "value3"),
            SomeBean("value4", "value5", "value6")
        )
        val mappingJacksonValue = MappingJacksonValue(list)
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3")
        val filters: FilterProvider = SimpleFilterProvider().addFilter("SomeBeanFilter", filter)
        mappingJacksonValue.filters = filters

        return mappingJacksonValue
    }
}