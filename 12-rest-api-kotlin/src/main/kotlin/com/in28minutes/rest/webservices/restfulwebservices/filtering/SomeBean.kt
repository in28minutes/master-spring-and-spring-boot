/**
 * A Kotlin data class representing a simple bean with JSON filtering capabilities.
 *
 * ## Features:
 * - **Jackson JSON Filtering**: Uses `@JsonFilter` to dynamically filter fields at runtime.
 * - **Kotlin Data Class**: Automatically generates:
 *   - `toString()`
 *   - `equals()`
 *   - `hashCode()`
 *   - `copy()`
 * - **Immutable Properties**: Uses `val` for read-only properties.
 * - **Kotlin Concepts and Syntax Used**:
 *   - **Data Class Declaration**:
 *     ```kotlin
 *     data class SomeBean(val field1: String, val field2: String, val field3: String)
 *     ```
 *   - **Jackson JSON Filtering**:
 *     ```kotlin
 *     @JsonFilter("SomeBeanFilter")
 *     ```
 *
 * ## Usage Example:
 * ```kotlin
 * val bean = SomeBean("value1", "value2", "value3")
 * ```
 *
 * When using Jackson's `FilterProvider`, specific fields can be included or excluded dynamically.
 *
 * @param field1 The first field of the bean.
 * @param field2 The second field of the bean.
 * @param field3 The third field of the bean.
 */
package com.in28minutes.rest.webservices.restfulwebservices.filtering

import com.fasterxml.jackson.annotation.JsonFilter

//@JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeBeanFilter")
data class SomeBean(
    val field1: String,
    val field2: String,
    val field3: String
)

/**
 * A Kotlin class representing a simple bean with JSON filtering and explicit getters.
 *
 * ## Features:
 * - **Jackson Annotations for JSON Filtering**:
 *   - `@JsonIgnoreProperties({"field1","field2"})`: Ignores specific fields during serialization.
 *   - `@JsonFilter("SomeBeanFilter")`: Allows dynamic field filtering.
 * - **Encapsulation**: Fields are `private`, and explicit getters control access.
 * - **Custom `toString()` Implementation**: Overrides `toString()` for better debugging.
 * - **Kotlin Concepts and Syntax Used**:
 *   - **Primary Constructor with Private Fields**:
 *     ```kotlin
 *     class SomeBean(private val field1: String, private val field2: String, private val field3: String)
 *     ```
 *   - **Explicit Getters**:
 *     ```kotlin
 *     fun getField1(): String = field1
 *     fun getField2(): String = field2
 *     fun getField3(): String = field3
 *     ```
 *   - **Overriding `toString()`**:
 *     ```kotlin
 *     override fun toString(): String {
 *         return "SomeBean [field1=$field1, field2=$field2, field3=$field3]"
 *     }
 *     ```
 *
 * ## Usage Example:
 * ```kotlin
 * val bean = SomeBean("value1", "value2", "value3")
 * println(bean) // Calls overridden toString()
 * ```
 *
 * When using Jackson's `FilterProvider`, selected fields can be included or excluded dynamically.
 *
 * @param field1 The first field (private, accessed via getter).
 * @param field2 The second field (private, accessed via getter).
 * @param field3 The third field (private, accessed via getter).
 */
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