package com.in28minutes.rest.webservices.restfulwebservices.versioning
/**
 * A data class representing a person's name.
 *
 * @property firstName A read-only (`val`) property for the first name.
 * @property lastName A read-only (`val`) property for the last name.
 *
 * - This is a **data class**, which automatically provides:
 *   - `toString()`
 *   - `equals()`
 *   - `hashCode()`
 *   - `copy()`
 * - The constructor parameters (`val firstName`, `val lastName`) are automatically
 *   assigned as properties of the class.
 */
data class Name(
    val firstName: String,
    val lastName: String
)

 /**
  * Alternative approach using a regular class:
  * A standard class representing a person's name.
  *
  * @constructor Primary constructor with private properties (`firstName`, `lastName`).
  * These properties are not accessible outside the class directly.
  */
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
