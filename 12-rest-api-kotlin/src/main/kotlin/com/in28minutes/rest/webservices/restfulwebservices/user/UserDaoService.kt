package com.in28minutes.rest.webservices.restfulwebservices.user

import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.function.Predicate
/**
 * Declares a class using the `class` keyword.
 * - `fun` declares a function.
 */
@Component
class UserDaoService {

    fun findAll(): List<User?> {
        return users
    }

    fun save(user: User): User {
        user.id = ++usersCount
        users.add(user)
        return user
    }

    fun findOne(id: Int): User? {
        val predicate: Predicate<in User?> =
            Predicate { user: User? -> user!!.id == id }
        return users.stream()
            .filter(predicate)
            .findFirst()
            .orElse(null)
    }

    fun deleteById(id: Int) {
        val predicate: Predicate<in User?> =
            Predicate { user: User? -> user!!.id == id }
        users.removeIf(predicate)
    }

    /**
     * Companion object serves as a static context.
     * - Allows static properties and functions.
     */
    companion object {
        // JPA/Hibernate > Database
        // UserDaoService > Static List
        /**
         * Declares a mutable (`var`) list of nullable `User` objects.
         * - `private` restricts access to this class.
         * - `ArrayList()` creates an empty list.
         */
        private val users: MutableList<User?> = ArrayList()

        /**
         * Declares a mutable (`var`) integer to keep track of user count.
         * - `private` restricts access.
         */
        private var usersCount = 0

        /**
         * `init` block executes when the class is first loaded.
         * - Populates the `users` list with initial data.
         * - `LocalDate.now().minusYears(30)` subtracts years from the current date.
         */
        init {
            users.add(User(++usersCount, "Adam", LocalDate.now().minusYears(30)))
            users.add(User(++usersCount, "Eve", LocalDate.now().minusYears(25)))
            users.add(User(++usersCount, "Jim", LocalDate.now().minusYears(20)))
        }
    }
}
