package com.in28minutes.rest.webservices.restfulwebservices.versioning

data class PersonV1(val name: String)

// OR
//class PersonV1(private val name: String) {
//
//    // Explicit getter for serialization
//    fun getName(): String = name
//
//    override fun toString(): String {
//        return "PersonV1 [name=$name]"
//    }
//}