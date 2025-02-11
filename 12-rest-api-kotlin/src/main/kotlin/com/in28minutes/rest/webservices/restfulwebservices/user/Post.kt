package com.in28minutes.rest.webservices.restfulwebservices.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
class Post {
    @Id
    @GeneratedValue
    var id: Int? = null

    var description: @Size(min = 10) String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    var user: User? = null

    override fun toString(): String {
        return "Post [id=$id, description=$description]"
    }
}
