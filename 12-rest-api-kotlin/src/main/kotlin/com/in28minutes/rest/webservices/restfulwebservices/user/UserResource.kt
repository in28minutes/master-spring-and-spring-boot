package com.in28minutes.rest.webservices.restfulwebservices.user

import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class UserResource(private val service: UserDaoService) {

    @GetMapping("/users")
    fun retrieveAllUsers(): List<User?> {
        return service.findAll()
    }

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

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Int) {
        service.deleteById(id)
    }

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
