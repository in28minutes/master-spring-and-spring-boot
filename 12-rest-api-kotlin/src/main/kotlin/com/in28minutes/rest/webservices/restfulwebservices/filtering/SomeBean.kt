package com.in28minutes.rest.webservices.restfulwebservices.filtering

import com.fasterxml.jackson.annotation.JsonFilter

//@JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeBeanFilter")
data class SomeBean(
    val field1: String,
    val field2: String,
    val field3: String
)

// OR
//@JsonIgnoreProperties({"field1","field2"})
//@JsonFilter("SomeBeanFilter")
//class SomeBean(
//    private val field1: String,
//    private val field2: String,
//    private val field3: String
//) {
//    // Explicit getters for serialization
//    fun getField1(): String = field1
//    fun getField2(): String = field2
//    fun getField3(): String = field3
//
//    override fun toString(): String {
//        return "SomeBean [field1=$field1, field2=$field2, field3=$field3]"
//    }
//}