package com.in28minutes.rest.webservices.restfulwebservices.user

import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

/**
 * @property service Declares a read-only (`val`) property for `UserDaoService`.
 * The constructor parameter is automatically assigned as a property.
 */
@RestController
class UserResource(private val service: UserDaoService) {

    /**
     * Retrieves all users.
     *
     * @return A list of nullable `User` objects (`List<User?>`).
     * The `?` indicates that the list can contain `null` values.
     */
    @GetMapping("/users")
    fun retrieveAllUsers(): List<User?> {
        return service.findAll()
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user as an `Int`.
     * @return An `EntityModel<User>` containing the user and a HATEOAS link.
     * Uses Kotlin’s null safety feature (`?:`) to throw an exception if `findOne(id)` returns `null`.
     */
    //http://localhost:8080/users
    //EntityModel
    //WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    fun retrieveUser(@PathVariable id: Int): EntityModel<User> {
        val user = service.findOne(id) ?: throw UserNotFoundException("id:$id")

        val entityModel = EntityModel.of(user)

        val link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.javaClass).retrieveAllUsers())
        entityModel.add(link.withRel("all-users"))

        return entityModel
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user as an `Int`.
     * Calls `service.deleteById(id)`, which does not return anything (`Unit` return type is implicit).
     */
    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Int) {
        service.deleteById(id)
    }

    /**
     * Creates a new user.
     *
     * @param user The request body containing a `User` object.
     * The `@Valid` annotation ensures validation.
     * @return `ResponseEntity<User>` with the created status and URI location.
     * Uses `val` for `savedUser` since it's immutable.
     */
    @PostMapping("/users")
    fun createUser(@RequestBody user: @Valid User): ResponseEntity<User> {
        val savedUser = service.save(user)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}
