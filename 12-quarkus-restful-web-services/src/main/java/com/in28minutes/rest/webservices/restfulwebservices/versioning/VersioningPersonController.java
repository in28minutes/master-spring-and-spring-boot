package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for demonstrating different API versioning strategies in Quarkus.
 *
 * <p>Versioning strategies covered:</p>
 * <ul>
 *     <li>URI Versioning</li>
 *     <li>Request Parameter Versioning</li>
 *     <li>Header Versioning</li>
 *     <li>Accept Header (Media Type) Versioning</li>
 * </ul>
 *
 * <p>Converted from Spring Boot to Quarkus:</p>
 * <ul>
 *     <li>Replaced {@code @RestController} with {@code @Path}</li>
 *     <li>Replaced {@code @GetMapping} with {@code @GET} and {@code @Path}</li>
 *     <li>Used {@code @QueryParam} instead of Spring's request parameter mapping</li>
 *     <li>Used {@code @HeaderParam} instead of Spring's header-based versioning</li>
 *     <li>Used {@code @Produces} for Accept Header versioning</li>
 * </ul>
 */
@RestController
public class VersioningPersonController {

    /**
     * URI-based versioning - Version 1.
     *
     * @return {@link PersonV1} object with a full name.
     */
    @GET
    @Path("/v1/person")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    /**
     * URI-based versioning - Version 2.
     *
     * @return {@link PersonV2} object with separate first and last name.
     */
    @GET
    @Path("/v2/person")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    /**
     * Request Parameter-based versioning.
     *
     * <p>Usage: {@code GET /person?version=1} or {@code GET /person?version=2}</p>
     *
     * @param version API version from request parameter.
     * @return {@link PersonV1} for version 1, {@link PersonV2} for version 2.
     * Access from the browser click <a href="http://localhost:8080/person?version=1">here</a>
     */
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByRequestParam(@QueryParam("version") int version) {
        return getResponse(version);
    }

    /**
     * Header-based versioning.
     *
     * <p>Usage: Add header {@code X-API-VERSION: 1} or {@code X-API-VERSION: 2}</p>
     *
     * @param version API version from the request header.
     * @return {@link PersonV1} for version 1, {@link PersonV2} for version 2.
     */
    @GET
    @Path("/person/header")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByHeader(@HeaderParam("X-API-VERSION") int version) {
        return getResponse(version);
    }

    /**
     * Accept Header-based versioning - Version 1.
     *
     * <p>Usage: Add header {@code Accept: application/vnd.company.app-v1+json}</p>
     *
     * @return {@link PersonV1} object.
     */
    @GET
    @Path("/person/accept")
    @Produces("application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    /**
     * Accept Header-based versioning - Version 2.
     *
     * <p>Usage: Add header {@code Accept: application/vnd.company.app-v2+json}</p>
     *
     * @return {@link PersonV2} object.
     */
    @GET
    @Path("/person/accept")
    @Produces("application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    private Response getResponse(@QueryParam("version") int version) {
        return switch (version) {
            case 1 -> Response.ok(new PersonV1("Bob Charlie")).build();
            case 2 -> Response.ok(new PersonV2(new Name("Bob", "Charlie"))).build();
            default -> Response.status(Response.Status.BAD_REQUEST).build();
        };
    }
}
