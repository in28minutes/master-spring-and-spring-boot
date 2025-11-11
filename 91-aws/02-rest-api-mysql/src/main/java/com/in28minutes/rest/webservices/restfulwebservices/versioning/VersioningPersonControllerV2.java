//package com.in28minutes.rest.webservices.restfulwebservices.versioning;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Starting Spring Framework 7 `version` attribute has been introduced.
// */
//@RestController
//public class VersioningPersonControllerV2 {
//
//    /** Path-Segment Versioning **/
//    // @GetMapping("/v1/person")
//    @GetMapping(value = "/{version}/person", version = "1.0")
//	public PersonV1 getFirstVersionOfPerson() {
//		return new PersonV1("Bob Charlie");
//	}
//
//    // @GetMapping("/v2/person")
//    @GetMapping(value = "/{version}/person", version = "2.0")
//	public PersonV2 getSecondVersionOfPerson() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}
//
//    /** Query Parameter Versioning **/
//    // http://localhost:5001/person?version=1
//	@GetMapping(path = "/person", params = "version=1")
//	public PersonV1 getFirstVersionOfPersonRequestParameter() {
//		return new PersonV1("Bob Charlie");
//	}
//
//    // http://localhost:5001/person?version=2
//	@GetMapping(path = "/person", params = "version=2")
//	public PersonV2 getSecondVersionOfPersonRequestParameter() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}
//
//    /** Request Header Versioning **/
//    // USAGE: GET /person/header
//    // X-API-Version: 1.0
//	@GetMapping(path = "/person/header", version = "1.0")
//	public PersonV1 getFirstVersionOfPersonRequestHeader() {
//		return new PersonV1("Bob Charlie");
//	}
//
//    // USAGE: GET /person/header
//    // X-API-Version: 2.0
//	@GetMapping(path = "/person/header", version = "2.0")
//	public PersonV2 getSecondVersionOfPersonRequestHeader() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}
//
//	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
//	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
//		return new PersonV1("Bob Charlie");
//	}
//
//	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
//	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}
//
//}
