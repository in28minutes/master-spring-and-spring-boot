package com.in28minutes.rest.webservices.restfulwebservices.helloworld

/**
 * A data class representing a simple message container.
 *
 * ## Features:
 * 1. Defines a `data class` named `HelloWorldBean`.
 * 2. Contains a single mutable property: `message: String` (`var`).
 * 3. Automatically provides:
 *    - `toString()`: Returns a string representation of the object.
 *    - `equals()`: Compares instances for equality.
 *    - `hashCode()`: Generates a hash code.
 *    - `copy()`: Creates a copy of the object with modified properties.
 *
 * @constructor Creates a `HelloWorldBean` instance with a given message.
 * @property message The message string, which can be modified after instantiation.
 * @return A `HelloWorldBean` object.
 *
 * Ideal for simple data-holding objects.
 */

data class HelloWorldBean(var message: String)

/**
 * An alternative implementation of `HelloWorldBean` using a regular class.
 *
 * ## Features:
 * 1. Defines a regular class with a mutable property: `message: String` (`var`).
 * 2. Manually overrides `toString()` to provide a custom string representation.
 * 3. Lacks built-in features of a `data class`, such as `equals()`, `hashCode()`, and `copy()`.
 *
 * @constructor Creates a `HelloWorldBean` instance with a given message.
 * @property message The message string, which can be modified after instantiation.
 * @return A `HelloWorldBean` object.
 *
 * Useful when full control over method implementations is needed.
 */

//class HelloWorldBean(var message: String) {
//    override fun toString(): String {
//        return "HelloWorldBean [message=$message]"
//    }
//}