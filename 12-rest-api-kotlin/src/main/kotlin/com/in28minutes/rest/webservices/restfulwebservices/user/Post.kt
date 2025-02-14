package com.in28minutes.rest.webservices.restfulwebservices.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Size
/**
 * Declares a Kotlin class using `class` keyword.
 * The `@Entity` annotation marks it as a JPA entity.
 *
 */
@Entity
class Post {
    /**
     * Declares a mutable (`var`) nullable property `id` of type `Int?`.
     * - `var` means the value can be changed.
     * - `Int?` makes it nullable (it can hold `null`).
     */
    @Id
    @GeneratedValue
    var id: Int? = null

    /**
     * Declares a mutable (`var`) nullable property `description` of type `String?`.
     * - `var` allows modification.
     * - `String?` makes it nullable.
     */
    var description: @Size(min = 10) String? = null

    /**
     * Declares a mutable (`var`) nullable property `user` of type `User?`.
     * - `var` means it can be reassigned.
     * - `User?` allows it to hold `null`.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    var user: User? = null

    /**
     * - `override` is used to modify a superclass function.
     * - The function returns a `String`.
     * - Uses **string interpolation** (`$variable`) to insert values into the string.
     * - `$id` is replaced with the actual value of `id`.
     * - `$description` is replaced with the actual value of `description`.
     * - The result is a formatted string: `"Post [id=1, description=Sample post]"`
     */
    override fun toString(): String {
        return "Post [id=$id, description=$description]"
    }
}
