package com.in28minutes.rest.webservices.restfulwebservices.user

import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.function.Predicate

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


    companion object {
        // JPA/Hibernate > Database
        // UserDaoService > Static List
        private val users: MutableList<User?> = ArrayList()

        private var usersCount = 0

        init {
            users.add(User(++usersCount, "Adam", LocalDate.now().minusYears(30)))
            users.add(User(++usersCount, "Eve", LocalDate.now().minusYears(25)))
            users.add(User(++usersCount, "Jim", LocalDate.now().minusYears(20)))
        }
    }
}
