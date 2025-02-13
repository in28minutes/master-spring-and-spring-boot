/**
 * Repository interface for managing **User** entity persistence.
 *
 * - Extends `JpaRepository<User, Int>` to provide **CRUD** operations.
 * - Uses **Spring Data JPA** to handle database interactions.
 * - No need to implement methods manually—Spring generates them automatically.
 *
 * ## Features:
 * - Supports **find, save, delete** operations out of the box.
 * - Can be extended with **custom query methods**.
 *
 * ## Example Usage:
 * ```kotlin
 * val user: User? = userRepository.findById(1).orElse(null)
 * ```
 * Kotlin's `:` replaces Java's extends/implements
 *
 * @see JpaRepository
 */
package com.in28minutes.rest.webservices.restfulwebservices.jpa

import com.in28minutes.rest.webservices.restfulwebservices.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>
