package com.in28minutes.rest.webservices.restfulwebservices.versioning

data class PersonV2(val name: Name)

// OR

//class PersonV2(private val name: Name) {
//
//    // Explicit getter for serialization
//    fun getName(): Name = name
//
//    override fun toString(): String {
//        return "PersonV2 [name=$name]"
//    }
//}

