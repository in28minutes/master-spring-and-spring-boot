/**
 * Repository interface for managing `Post` entities.
 *
 * This interface extends [JpaRepository] to provide standard CRUD operations
 * for the `Post` entity, which is identified by an `Int` ID.
 *
 * ## Example Usage:
 * ```kotlin
 * @Autowired
 * lateinit var postRepository: PostRepository
 *
 * fun getAllPosts(): List<Post> {
 *     return postRepository.findAll()
 * }
 *
 * fun getPostById(id: Int): Post? {
 *     return postRepository.findById(id).orElse(null)
 * }
 * ```
 * Kotlin's `:` replaces Java's extends/implements
 * @see JpaRepository
 */
package com.in28minutes.rest.webservices.restfulwebservices.jpa

import com.in28minutes.rest.webservices.restfulwebservices.user.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int>
