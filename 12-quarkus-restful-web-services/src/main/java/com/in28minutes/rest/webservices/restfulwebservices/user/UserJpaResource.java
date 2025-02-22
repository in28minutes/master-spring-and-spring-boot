package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.*;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;

/**
 * Replaced Spring annotations (@RestController, @GetMapping, etc.) with Jakarta RESTful Web Services (JAX-RS) annotations (@Path, @GET, etc.).<br/><br/>
 * Replaced ResponseEntity with Quarkus-native Response.<br/>
 * Replaced findAll() with listAll() for Panache repository.<br/>
 * Used persist(user) instead of save(user) for Panache.<br/>
 * Swagger URL: <a href="http://localhost:8080/q/swagger-ui/">Quarkus Swagger</a><br/>
 * Dev UI: <a href="http://localhost:8080/q/dev-ui/endpoints">Quarkus Dev UI</a>
 */
@Path("/jpa/users")  // Use @Path for Routing instead of @RestController and @RequestMapping.
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserJpaResource {

    @Inject
    UserRepository userRepository;

    @Inject
    PostRepository postRepository;

    @Context  // Injects UriInfo for generating dynamic URLs
    UriInfo uriInfo;

    /**
     * Use @GET instead of @GetMapping <br/>
     * Use userRepository.listAll() (Quarkus Panache) instead of findAll()
     */
    @GET
    public List<User> retrieveAllUsers() {
        return userRepository.listAll();  // Quarkus way (Panache)
    }

    /**
     * Use @GET instead of @GetMapping <br/>
     * Use `Response` instead of `ResponseEntity` for returning responses.
     */
    @GET
    @Path("/{id}")
    public Response retrieveUser(@PathParam("id") int id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));

        if (user.isEmpty()) {
            throw new NotFoundException("User not found with id: " + id);
        }

        // HAL-style response with HATEOAS links
        Map<String, Object> response = new HashMap<>();
        response.put("user", user.get());

        // Create HATEOAS links
        Map<String, Object> links = new HashMap<>();
        links.put("self", uriInfo.getAbsolutePath().toString());
        links.put("all-users", uriInfo.getBaseUriBuilder().path(UserJpaResource.class).build().toString());

        response.put("_links", links);

        return Response.ok(response).build();
    }

    /**
     * Use @DELETE instead of @DeleteMapping
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        boolean deleted = userRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("User not found with id: " + id);
        }
        return Response.noContent().build(); // Use Response.noContent().build() for DELETE operations
    }

    /**
     * Use @GET instead of @GetMapping
     */
    @GET
    @Path("/{id}/posts")
    public Response retrievePostsForUser(@PathParam("id") int id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));

        if (user.isEmpty()) {
            throw new NotFoundException("User not found with id: " + id);
        }

        return Response.ok(user.get().getPosts()).build();
    }

    /**
     * Use @POST instead of @PostMapping <br/>
     * Use userRepository.persist(user) instead of save(user) (Quarkus Panache).
     */
    @POST
    @Transactional
    public Response createUser(@Valid User user) {
        userRepository.persist(user);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(user.getId()))
                .build();
        return Response.created(location).build();
    }

    /**
     * Use @POST instead of @PostMapping <br/>
     * Use userRepository.persist(user) instead of save(user) (Quarkus Panache).
     */
    @POST
    @Path("/{id}/posts")
    @Transactional
    public Response createPostForUser(@PathParam("id") int id, @Valid Post post) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));

        if (user.isEmpty()) {
            throw new NotFoundException("User not found with id: " + id);
        }

        post.setUser(user.get());
        postRepository.persist(post);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(post.getId()))
                .build();

        return Response.created(location).build();
    }
}
