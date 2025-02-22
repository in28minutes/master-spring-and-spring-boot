package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Service class responsible for managing user data. Marks a class as a CDI-managed bean
 *
 * <p>
 * This class is marked as {@code @ApplicationScoped}, meaning it is a CDI-managed
 * bean with a single instance shared across the application. Singleton within the application scope
 * </p>
 */
@ApplicationScoped
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List

    private static final List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
