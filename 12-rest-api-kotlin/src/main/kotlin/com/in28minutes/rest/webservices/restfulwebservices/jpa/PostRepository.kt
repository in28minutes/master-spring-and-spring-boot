package com.in28minutes.rest.webservices.restfulwebservices.jpa

import com.in28minutes.rest.webservices.restfulwebservices.user.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int>
