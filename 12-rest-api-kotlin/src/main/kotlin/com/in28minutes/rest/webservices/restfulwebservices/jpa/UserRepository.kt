package com.in28minutes.rest.webservices.restfulwebservices.jpa

import com.in28minutes.rest.webservices.restfulwebservices.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>
