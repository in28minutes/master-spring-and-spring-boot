package com.in28minutes.rest.webservices.restfulwebservices.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity(name = "user_details")
class User {

    protected constructor()

    @Id
    @GeneratedValue
    var id: Int? = null

    //@JsonProperty("user_name")
    var name: @Size(min = 2, message = "Name should have at least 2 characters") String? = null

    //@JsonProperty("birth_date")
    var birthDate: @Past(message = "Birth Date should be in the past") LocalDate? = null

    @OneToMany(mappedBy = "user", targetEntity = Post::class)
    @JsonIgnore
    var posts: List<Post>? = null

    constructor(
        id: Int?,
        name: String?,
        birthDate: LocalDate?
    ) : super() {
        this.id = id
        this.name = name
        this.birthDate = birthDate
    }

    override fun toString(): String {
        return "User [id=$id, name=$name, birthDate=$birthDate]"
    }
}
