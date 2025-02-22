package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

/**
 * Replaced Spring annotations (@RestController, @GetMapping, etc.) with Jakarta RESTful Web Services (JAX-RS) annotations (@Path, @GET, etc.).<br/><br/>
 * Replaced ResponseEntity with Quarkus-native Response.<br/>
 * Swagger URL: <a href="http://localhost:8080/q/swagger-ui/">Quarkus Swagger</a><br/>
 * Dev UI: <a href="http://localhost:8080/q/dev-ui/endpoints">Quarkus Dev UI</a>
 */
@Path("/users")  // Base path for this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDaoService service;

    /**
     * Inject UriInfo to dynamically generate URLs
     * Use UriInfo in Quarkus, which is part of JAX-RS.
     */
    @Context  // Inject UriInfo for generating dynamic URLs
    UriInfo uriInfo;

    @Inject
    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GET
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // http://localhost:8080/users

    /**
     * Java Code
     * <code>
     * @param id The ID of the user to retrieve.
     * @return A Map containing user details and If the user is found, returns the user details with HATEOAS-style links.
     * @throws UserNotFoundException if the user is not found.
     * Unlike Spring HATEOAS, Quarkus does not have automatic link generation.
     * EntityModel, WebMvcLinkBuilder implementation does not exist. So we have to manually create _links.
     */
    @GET
    @Path("/{id}")
    public Response retrieveUser(@PathParam("id") int id) {
        User user = service.findOne(id);

        if (user == null)
            throw new NotFoundException("id: " + id);

        // Prepare HAL-style response
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);

        // Create HATEOAS links
        Map<String, Object> links = new HashMap<>();
        links.put("self", uriInfo.getBaseUriBuilder().path(UserResource.class).path(String.valueOf(id)).build().toString());
        links.put("all-users", uriInfo.getBaseUriBuilder().path(UserResource.class).build().toString());

        response.put("_links", links);
        return Response.ok(response).build();
    }

    /**
     * Use @DELETE instead of @DeleteMapping
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        service.deleteById(id);
        return Response.noContent().build();
    }

    /**
     * Java Code
     * <code>
     * URI location = ServletUriComponentsBuilder.fromCurrentRequest()
     * .path("/{id}")
     * .buildAndExpand(savedUser.getId())
     * .toUri();
     * </code>
     * Unlike Spring HATEOAS, Quarkus does not have automatic link generation.
     * EntityModel, WebMvcLinkBuilder implementation does not exist. So we have to manually create _links.
     **/
    @POST
    @Transactional
    public Response createUser(@Valid User user) {
        service.save(user);

        // QUARKUS WAY
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(user.getId()))
                .build();
        return Response.created(location).build();
    }
}
