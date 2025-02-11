package com.in28minutes.rest.webservices.restfulwebservices.helloworld

data class HelloWorldBean(var message: String)

// OR
//class HelloWorldBean(var message: String) {
//    override fun toString(): String {
//        return "HelloWorldBean [message=$message]"
//    }
//}