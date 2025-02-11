package com.in28minutes.rest.webservices.restfulwebservices.user

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository
import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
class UserJpaResource(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {

    @GetMapping("/jpa/users")
    fun retrieveAllUsers(): List<User> {
        return userRepository.findAll()
    }

    //http://localhost:8080/users
    //EntityModel
    //WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    fun retrieveUser(@PathVariable id: Int): EntityModel<User> {
        val user: Optional<User> = userRepository.findById(id)

        if (user.isEmpty) throw UserNotFoundException("id:$id")

        val entityModel = EntityModel.of(user.get())

        val link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.javaClass).retrieveAllUsers())
        entityModel.add(link.withRel("all-users"))

        return entityModel
    }

    @DeleteMapping("/jpa/users/{id}")
    fun deleteUser(@PathVariable id: Int) {
        userRepository.deleteById(id)
    }

    @GetMapping("/jpa/users/{id}/posts")
    fun retrievePostsForUser(@PathVariable id: Int): List<Post>? {
        val user: Optional<User> = userRepository.findById(id)

        if (user.isEmpty) throw UserNotFoundException("id:$id")

        return user.get().posts
    }

    @PostMapping("/jpa/users")
    fun createUser(@RequestBody user: @Valid User): ResponseEntity<User> {
        val savedUser: User = userRepository.save(user)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }

    @PostMapping("/jpa/users/{id}/posts")
    fun createPostForUser(@PathVariable id: Int, @RequestBody post: @Valid Post): ResponseEntity<Any> {
        val user: Optional<User> = userRepository.findById(id)

        if (user.isEmpty) throw UserNotFoundException("id:$id")

        post.user = user.get()

        val savedPost: Post = postRepository.save(post)

        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPost.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}
