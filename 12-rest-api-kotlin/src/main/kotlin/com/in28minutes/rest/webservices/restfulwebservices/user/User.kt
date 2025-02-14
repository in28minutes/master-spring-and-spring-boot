package com.in28minutes.rest.webservices.restfulwebservices.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import java.time.LocalDate

/**
 * Declares a Kotlin class using the `class` keyword.
 */
@Entity(name = "user_details")
class User {

    /**
     * Declares a `protected` no-argument constructor.
     * - `protected` means it's only accessible within this class or subclasses.
     * - Used for JPA entity initialization.
     */
    protected constructor()

    /**
     * Declares a mutable (`var`) nullable property `id` of type `Int?`.
     * - `var` allows reassignment.
     * - `Int?` means it can hold `null`.
     */
    @Id
    @GeneratedValue
    var id: Int? = null

    /**
     * Declares a mutable (`var`) nullable property `name` of type `String?`.
     * - `var` allows modification.
     * - `String?` makes it nullable.
     */
    //@JsonProperty("user_name")
    var name: @Size(min = 2, message = "Name should have at least 2 characters") String? = null

    /**
     * Declares a mutable (`var`) nullable property `birthDate` of type `LocalDate?`.
     * - `var` allows reassignment.
     * - `LocalDate?` means it can be `null`.
     */
    //@JsonProperty("birth_date")
    var birthDate: @Past(message = "Birth Date should be in the past") LocalDate? = null

    /**
     * Declares a mutable (`var`) nullable property `posts` of type `List<Post>?`.
     * - `var` allows modification.
     * - `List<Post>?` makes it a nullable list of `Post` objects.
     * - `@OneToMany(mappedBy = "user", targetEntity = Post::class)` establishes a one-to-many relationship.
     */
    @OneToMany(mappedBy = "user", targetEntity = Post::class)
    @JsonIgnore
    var posts: List<Post>? = null

    /**
     * Declares a secondary constructor that initializes `id`, `name`, and `birthDate`.
     * - `constructor(...) : super()` calls the superclass constructor.
     * - Assigns parameter values to properties.
     */
    constructor(
        id: Int?,
        name: String?,
        birthDate: LocalDate?
    ) : super() {
        this.id = id
        this.name = name
        this.birthDate = birthDate
    }

    /**
     * Overrides the `toString()` function to provide a string representation of the class.
     * - Uses **string interpolation** (`$variable`) to insert values dynamically.
     */
    override fun toString(): String {
        return "User [id=$id, name=$name, birthDate=$birthDate]"
    }
}
