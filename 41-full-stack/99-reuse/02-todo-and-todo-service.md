## Todo and TodoService


```java
package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;

public class Todo {

    public Todo() {
        
    }
    
    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        super();
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    private int id;

    private String username;
    
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
                + targetDate + ", done=" + done + "]";
    }

}


package com.in28minutes.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    
    private static List<Todo> todos = new ArrayList<>();
    
    private static int todosCount = 0;
    
    static {
        todos.add(new Todo(++todosCount, "in28minutes","Get AWS Certified", 
                            LocalDate.now().plusYears(5), false ));
        todos.add(new Todo(++todosCount, "in28minutes","Learn DevOps", 
                LocalDate.now().plusYears(6), false ));
        todos.add(new Todo(++todosCount, "in28minutes","Learn Full Stack Development", 
                LocalDate.now().plusYears(7), false ));
    }
    
    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = 
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
    
    public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount,username,description,targetDate,done);
        todos.add(todo);
        return todo;
    }
    
    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
```
