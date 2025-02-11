package com.in28minutes.rest.webservices.restfulwebservices.versioning

data class Name(
    val firstName: String,
    val lastName: String
)


// OR
//class Name(
//    private val firstName: String,
//    private val lastName: String
//) {
//
//    // Explicit getters for serialization
//    fun getFirstName(): String = firstName
//    fun getLastName(): String = lastName
//
//    override fun toString(): String {
//        return "Name [firstName=$firstName, lastName=$lastName]"
//    }
//}
