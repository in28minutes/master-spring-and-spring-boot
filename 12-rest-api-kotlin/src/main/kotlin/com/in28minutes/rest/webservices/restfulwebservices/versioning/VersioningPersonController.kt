package com.in28minutes.rest.webservices.restfulwebservices.versioning

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * - `@get:GetMapping` is used as a **property-based mapping**.
 * In Kotlin, instead of defining a function (fun), we can use properties (val).
 */
@RestController
class VersioningPersonController {

    @get:GetMapping("/v1/person")
    val firstVersionOfPerson: PersonV1
        get() = PersonV1("Bob Charlie")

    @get:GetMapping("/v2/person")
    val secondVersionOfPerson: PersonV2
        get() = PersonV2(Name("Bob", "Charlie"))

    @get:GetMapping(path = ["/person"], params = ["version=1"])
    val firstVersionOfPersonRequestParameter: PersonV1
        get() = PersonV1("Bob Charlie")

    @get:GetMapping(path = ["/person"], params = ["version=2"])
    val secondVersionOfPersonRequestParameter: PersonV2
        get() = PersonV2(Name("Bob", "Charlie"))

    @get:GetMapping(path = ["/person/header"], headers = ["X-API-VERSION=1"])
    val firstVersionOfPersonRequestHeader: PersonV1
        get() = PersonV1("Bob Charlie")

    @get:GetMapping(path = ["/person/header"], headers = ["X-API-VERSION=2"])
    val secondVersionOfPersonRequestHeader: PersonV2
        get() = PersonV2(Name("Bob", "Charlie"))

    @get:GetMapping(path = ["/person/accept"], produces = ["application/vnd.company.app-v1+json"])
    val firstVersionOfPersonAcceptHeader: PersonV1
        get() = PersonV1("Bob Charlie")

    @get:GetMapping(path = ["/person/accept"], produces = ["application/vnd.company.app-v2+json"])
    val secondVersionOfPersonAcceptHeader: PersonV2
        get() = PersonV2(Name("Bob", "Charlie"))
}