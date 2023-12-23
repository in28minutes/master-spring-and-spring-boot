# Spring Boot Web Application - V2

## Step01

Step 01 - Creating Spring Boot Web Application with Spring Initializr

On Spring Initializr, choose:
- Group Id: com.in28minutes.springboot
- Artifact Id: myfirstwebapp
	- Dependencies
	- Web
	- DevTools

---
## Step02
Step 02 - Quick overview of Spring Boot Project

## Step03

Step 03 - First Spring MVC Controller, @ResponseBody, @Controller

Checks
- Is there `@Controller` on `SayHelloController`?
- Is there `@ResponseBody` on `sayHello` method?
- Is `SayHelloController.java` under component scan?
	- Is it under sub package of com.in28minutes.springboot.myfirstwebapp?
	- Understand Component Scan in depth - https://www.youtube.com/watch?v=L1Y5pwy09Vw


### /src/main/java/com/in28minutes/springboot/myfirstwebapp/hello/SayHelloController.java

```java
package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	//"say-hello" => "Hello! What are you learning today?"
	
	//say-hello
	// http://localhost:8080/say-hello
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you learning today?";
	}

}
```
---

## Step04

Step 04 - Enhancing Spring MVC Controller to provide HTML response

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/hello/SayHelloController.java Modified


```
	@RequestMapping("say-hello-html")
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My First HTML Page - Changed</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
```
---

## Step05

Step 05 - Redirect to a JSP using Spring Boot - Controller, @ResponseBody and View Resolver

Checks
- Check all code changes below
- Focus on the path of JSP 
	- `/src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp`
- Focus on application.properties changes
- If you still have a problem:
	- Enable debug logging and post the logs in Q&A
		- `logging.level.org.springframework=debug`

### /pom.xml Modified

```
<dependency>
	<groupId>org.apache.tomcat.embed</groupId>
	<artifactId>tomcat-embed-jasper</artifactId>
	<scope>provided</scope>
</dependency>

NOTE: Those are using IntellIj IDE use the scope as `default' i.e.
<scope>default</scope>

```
### /src/main/java/com/in28minutes/springboot/myfirstwebapp/hello/SayHelloController.java Modified

```
	//
	// "say-hello-jsp" => sayHello.jsp 
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
```

---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp

```
<html>
	<head>
		<title> My first HTML Page - JSP</title>
	</head>
	<body>
		My first html page with body - JSP
	</body>
</html>
```
---

### /src/main/resources/application.properties Modified

```
#sayHello.jsp
#/WEB-INF/jsp/sayHello.jsp
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
logging.level.org.springframework=debug
```
## Step06

Step 06 - Exercise - Creating LoginController and login view

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/LoginController.java

```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	///login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp
	
	@RequestMapping("login")
	public String gotoLoginPage() {
		return "login";
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp

```
<html>
	<head>
		<title> Login Page</title>
	</head>
	<body>
		Welcome to the login page!
	</body>
</html>
```
---

## Step07
Step 07 - Quick Overview - How does web work - Request and Response

## Step08
Step 08 - Capturing QueryParams using RequestParam and First Look at Model

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/LoginController.java Modified

```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
	
	///login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp
	
	//http://localhost:8080/login?name=Ranga
	//Model
	@RequestMapping("login")
	public String gotoLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		System.out.println("Request param is " + name); //NOT RECOMMENDED FOR PROD CODE
		return "login";
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp Modified

```
		Welcome to the login page ${name}! 
```
### /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp Modified

```
		<h1>Heading 1</h1>
		<h2>Heading 2</h2>
		
```

## Step09
Step 09 - Quick Overview - Importance of Logging with Spring Boot

## Step10
Step 10 - Understanding DispatcherServlet, Model 1, Model 2 and Front Controller

## Step11
Step 11 - Creating a Login Form


### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/LoginController.java Modified

```
	//login
	public String gotoLoginPage() {
```

```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	//login
	@RequestMapping("login")
	public String gotoLoginPage() {
		return "login";
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp Modified

```
		<title>Login Page</title>
		Welcome to the login page!
		<form method="post">
			Name: <input type="text" name="name">
			Password: <input type="password" name="password">
			<input type="submit">
		</form>
		 
```
### /src/main/resources/application.properties Modified

```
# /WEB-INF/jsp/login.jsp => View Resolver
logging.level.org.springframework=info
logging.level.com.in28minutes.springboot.myfirstwebapp=info
```

## Step12
Step 12 - Displaying Login Credentials in a JSP using Model

## Step13
Step 13 - Add hard-coded validation of userid and password

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/AuthenticationService.java

```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String username, String password) {
		
		boolean isValidUserName = username.equalsIgnoreCase("in28minutes");
		boolean isValidPassword = password.equalsIgnoreCase("dummy");
		
		return isValidUserName && isValidPassword;
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/LoginController.java Modified

Changes
```
@RequestMapping(value="login",method = RequestMethod.GET)
public String gotoLoginPage() {

NEW METHOD: gotoWelcomePage and autowiring AuthenticationService

Details below!		
```

```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private AuthenticationService authenticationService;
	
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value="login",method = RequestMethod.GET)
	public String gotoLoginPage() {
		return "login";
	}

	@RequestMapping(value="login",method = RequestMethod.POST)
	//login?name=Ranga RequestParam
	public String gotoWelcomePage(@RequestParam String name, 
			@RequestParam String password, ModelMap model) {
		
		if(authenticationService.authenticate(name, password)) {
		
			model.put("name", name);
			//Authentication 
			//name - in28minutes
			//password - dummy
			
			return "welcome";
		}
		
		return "login";
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp

```
<html>
	<head>
		<title>Welcome Page</title>
	</head>
	<body>
		<div>Welcome to in28minutes</div>
		<div>Your Name: ${name}</div>
	</body>
</html>
```
---

## Step14
Getting started with Todo Features - Creating Todo and TodoService

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/Todo.java

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

//Database (MySQL) 
//Static List of todos => Database (H2, MySQL)

public class Todo {

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
```

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoService.java

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todos = ArrayList<>();
	
	static {
		todos.add(new Todo(1, "in28minutes","Learn AWS", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(2, "in28minutes","Learn DevOps", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(3, "in28minutes","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
	}
	
}
```
---


## Step15
Step 15 - Creating first version of List Todos Page

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class TodoController {
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	private TodoService todoService;
		
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}

}
```

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoService.java

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	//OLD_CODE

	public List<Todo> findByUsername(String username){
		return todos;
	}
}
```
---


## Step16

Step 16 - Understanding Session vs Model vs Request - @SessionAttributes

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/LoginController.java Modified

```
@Controller
@SessionAttributes("name")
public class LoginController {
```

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java

```java
@Controller
@SessionAttributes("name")
public class TodoController {
```

## Step17
Step 17 - Adding JSTL to Spring Boot Project and Showing Todos in a Table


### /pom.xml Modified

```
<dependency>
	<groupId>jakarta.servlet.jsp.jstl</groupId>
	<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
</dependency>

<dependency>
	<groupId>org.glassfish.web</groupId>
	<artifactId>jakarta.servlet.jsp.jstl</artifactId>
</dependency>
```

### /src/main/resources/META-INF/resources/WEB-INF/jsp/listTodos.jsp
```
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
	<body>
		<div>
			<h1>Your Todos</h1>
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Done?</th>
					</tr>
				</thead>
				<tbody>		
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.id}</td>
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		
	</body>
</html>
```
---


## Step18
Step 18 - Adding Bootstrap CSS framework to Spring Boot Project using webjars

## Step19
Step 19 - Formatting JSP pages with Bootstrap CSS framework

Snippets
```
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
```

### /pom.xml Modified

```
<dependency>
	<groupId>org.webjars</groupId>
	<artifactId>bootstrap</artifactId>
	<version>5.1.3</version>
</dependency>

<dependency>
	<groupId>org.webjars</groupId>
	<artifactId>jquery</artifactId>
	<version>3.6.0</version>
</dependency>
		
```

### /src/main/resources/META-INF/resources/WEB-INF/jsp/listTodos.jsp

```
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<title>List Todos Page</title>		
	</head>
	<body>
		<div class="container">
			<h1>Your Todos</h1>
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Done?</th>
					</tr>
				</thead>
				<tbody>		
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.id}</td>
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
		
	</body>
</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp Modified

```
	
		<div class="container">
			<h1>Login</h1>
			<pre>${errorMessage}</pre>
			<form method="post">
				Name: <input type="text" name="name">
				Password: <input type="password" name="password">
				<input type="submit">
			</form>
		</div>
```
### /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp Modified

```
		<div class="container">
			<h1>Welcome ${name}</h1>
			<a href="list-todos">Manage</a> your todos
		</div>
```
## Step21
- Step 20 - Lets Add a New Todo - Create a new View
- Step 21 - Enhancing TodoService to add the todo

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java Modified

```
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//GET, POST
@RequestMapping(value="add-todo", method = RequestMethod.GET)
public String showNewTodoPage() {
	return "todo";
}

@RequestMapping(value="add-todo", method = RequestMethod.POST)
public String addNewTodo(@RequestParam String description, ModelMap model) {
	String username = (String)model.get("name");
	todoService.addTodo(username, description, 
			LocalDate.now().plusYears(1), false);
	return "redirect:list-todos";
}

```
---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoService.java Modified

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	//OLD_CODE	

	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/listTodos.jsp Modified

```
			<a href="add-todo" class="btn btn-success">Add Todo</a>
```
### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp

```
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<title>Add Todo Page</title>		
	</head>
	<body>
		<div class="container">
			<h1>Enter Todo Details</h1>
			<form method="post">
				Description: <input type="text" name="description"/>
				<input type="submit" class="btn btn-success"/>
			
			</form>
			
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>		
	</body>
</html>
```
---

## Step22

Step 22 - Adding Validations using Spring Boot Starter Validation

### /pom.xml Modified

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java Modified

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	//OLD_CODE	

	//GET, POST
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, Todo todo) {
		String username = (String)model.get("name");
		todoService.addTodo(username, todo.getDescription(), 
				LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}
	
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp Modified

```
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<form:form method="post" modelAttribute="todo">
				Description: <form:input type="text" path="description" 
								required="required"/>
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="done"/>
			</form:form>
```

## Step23

Step 23 - Using Command Beans to implement New Todo Page Validations

## Step24
Step 24 - Implementing Delete Todo Feature - New View

## Step25
Step 25 - Implementing Update Todo - 1 - Show Update Todo Page

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/Todo.java Modified

```
import jakarta.validation.constraints.Size;
	
@Size(min=10, message="Enter atleast 10 characters")
private String description;
```
---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java Modified

```
import jakarta.validation.Valid;

	//GET, POST
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = (String)model.get("name");
		todoService.addTodo(username, todo.getDescription(), 
				LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		//Delete todo
		
		todoService.deleteById(id);
		return "redirect:list-todos";
		
	}

	@RequestMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
```
---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoService.java Modified

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	//OLD_CODE
	
	
	public void deleteById(int id) {
		//todo.getId() == id
		// todo -> todo.getId() == id
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/listTodos.jsp Modified

```
<th></th>
<th></th>
							
<td> <a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>   </td>
<td> <a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a>   </td>
```
### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp Modified

```
Description:<form:input type="text" path="description" 
			<form:errors path="description" cssClass="text-warning"/>
```

## Step26

Step 26 - Implementing Update Todo - 1 - Save changes to Todo

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java Modified
Changes
```
@RequestMapping(value="update-todo", method = RequestMethod.GET)

@RequestMapping(value="update-todo", method = RequestMethod.POST)
public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
	todo.setUsername(username);
	todoService.updateTodo(todo);
```

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	//OLD_CODE

	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = (String)model.get("name");
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}

}
```
---

## Step27
Step 27 - Adding Target Date Field to Todo Page

### /pom.xml Modified

```
<dependency>
	<groupId>org.webjars</groupId>
	<artifactId>bootstrap-datepicker</artifactId>
	<version>1.9.0</version>
</dependency>
```

### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp Modified

```
<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet" >

<fieldset class="mb-3">				
	<form:label path="description">Description</form:label>
	<form:input type="text" path="description" required="required"/>
	<form:errors path="description" cssClass="text-warning"/>
</fieldset>
<fieldset class="mb-3">				
	<form:label path="targetDate">Target Date</form:label>
	<form:input type="text" path="targetDate" required="required"/>
	<form:errors path="targetDate" cssClass="text-warning"/>
</fieldset>
		
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>
						
```
### /src/main/resources/application.properties Modified

```
spring.mvc.format.date=yyyy-MM-dd
```

## Step28
Step 28 - Adding a Navigation Bar and Implementing JSP Fragments

### /src/main/resources/META-INF/resources/WEB-INF/jsp/common/footer.jspf

```
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
		<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
						
	</body>
</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/common/header.jspf

```
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet" >
		
		<title>Manage Your Todos</title>		
	</head>
	<body>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/common/navigation.jspf

```
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
	<a class="navbar-brand m-1" href="https://courses.in28minutes.com">in28minutes</a>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="/list-todos">Todos</a></li>
		</ul>
	</div>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
	</ul>	
</nav>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/listTodos.jsp Modified

```
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
<div class="container">
	<h1>Your Todos</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.description}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.done}</td>
					<td> <a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>   </td>
					<td> <a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a>   </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf" %>
```
### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp Modified

```
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
<div class="container">
	
	<h1>Enter Todo Details</h1>
	
	<form:form method="post" modelAttribute="todo">
		<fieldset class="mb-3">				
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required"/>
			<form:errors path="description" cssClass="text-warning"/>
		</fieldset>
		<fieldset class="mb-3">				
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning"/>
		</fieldset>
		<form:input type="hidden" path="id"/>
		<form:input type="hidden" path="done"/>
		<input type="submit" class="btn btn-success"/>
	
	</form:form>
	
</div>
<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>
```
### /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp Modified

```
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
<div class="container">
	<h1>Welcome ${name}</h1>
	<a href="list-todos">Manage</a> your todos
</div>
<%@ include file="common/footer.jspf" %>
```

## Step29
Step 29 - Preparing for Spring Security

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/AuthenticationService.java Deleted
### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/LoginController.java Deleted
### /src/main/java/com/in28minutes/springboot/myfirstwebapp/login/WelcomeController.java

```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", "in28minutes");
		return "welcome";
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp Deleted

## Step30

Step 30 - Setting up Spring Security with Spring Boot Starter Security
### /pom.xml Modified

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```


## Step31

Step 31 - Configuring Spring Security with Custom User and Password 


### /src/main/java/com/in28minutes/springboot/myfirstwebapp/security/SpringSecurityConfiguration.java

```java
package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {
	//LDAP or Database
	//In Memory 
	
	//InMemoryUserDetailsManager
	//InMemoryUserDetailsManager(UserDetails... users)
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		Function<String, String> passwordEncoder
				= input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username("in28minutes")
									.password("dummy")
									.roles("USER","ADMIN")
									.build();
		
		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
```
---

## Step32
Step 32 - Refactoring and Removing Hardcoding of User Id


```java
package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
```


### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java Modified

```
private String getLoggedInUsername(ModelMap model) {
	Authentication authentication = 
			SecurityContextHolder.getContext().getAuthentication();
	return authentication.getName();

}
```
---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoService.java Modified

```
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
```

---
## Step33
Step 33 - Setting up a New User for Todo Application


### /src/main/java/com/in28minutes/springboot/myfirstwebapp/security/SpringSecurityConfiguration.java Modified

```java
package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {
	//LDAP or Database
	//In Memory 
	
	//InMemoryUserDetailsManager
	//InMemoryUserDetailsManager(UserDetails... users)
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
```
---

## Step34

Step 34 - Adding Spring Boot Starter Data JPA and Getting H2 database ready

### /pom.xml Modified

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

---

### /src/main/resources/application.properties Modified

```
spring.datasource.url=jdbc:h2:mem:testdb
```

## Step35

Step 35 - Configuring Spring Security to Get H2-console Working



### /src/main/java/com/in28minutes/springboot/myfirstwebapp/security/SpringSecurityConfiguration.java Modified

```java
package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disable
	//Frames
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		return http.build();
	}
	
}
```


## Step36
Step 36 - Making Todo an Entity and Population Todo Data into H2

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/Todo.java Modified

```
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//JPA
// Bean -> Database Table
@Entity
public class Todo {

	@Id
	@GeneratedValue
	private int id;


```

---

### /src/main/resources/application.properties Modified

```
spring.jpa.defer-datasource-initialization=true
```
### /src/main/resources/data.sql

```
insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10001,'in28minutes', 'Get AWS Certified', CURRENT_DATE(), false);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10002,'in28minutes', 'Get Azure Certified', CURRENT_DATE(), false);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10003,'in28minutes', 'Get GCP Certified', CURRENT_DATE(), false);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10004,'in28minutes', 'Learn DevOps', CURRENT_DATE(), false);
```
---

## Step37
Step 37 - Creating TodoRepository and Connecting List Todos page from H2 database

## Step38
Step 38 - Connecting All Todo App Features to H2 Database


### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/Todo.java Modified

```
	public Todo() {
	}		
```

---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoController.java Modified

```
//@Controller
//Disabled the TodoController
//Copied to TodoControllerJpa.java

```

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoControllerJpa.java

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	private TodoRepository todoRepository;
			
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
				
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}

	//GET, POST
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
//		todoService.addTodo(username, todo.getDescription(), 
//				todo.getTargetDate(), todo.isDone());
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		//Delete todo
		todoRepository.deleteById(id);
		return "redirect:list-todos";
		
	}

	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
```

---

### /src/main/java/com/in28minutes/springboot/myfirstwebapp/todo/TodoRepository.java

```java
package com.in28minutes.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findByUsername(String username);
}
```
---

## Step39
Step 39 - OPTIONAL - Overview of Connecting Todo App to MySQL database

## Step40
Step 40 - OPTIONAL - Installing Docker

### Install Docker

- [Docker](https://docs.docker.com/engine/install/)

## Step41
- Step 41 - OPTIONAL - Connecting Todo App to MySQL database


### Launch MySQL using Docker

```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle
```



### Mysqlsh

- [Docker](https://docs.docker.com/engine/install/)
- [Mysqlsh](https://dev.mysql.com/doc/mysql-shell/8.0/en/mysql-shell-install.html)


### /pom.xml Modified

```
<!-- Use this for Spring Boot 3.1 and higher -->
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
</dependency> 

<!-- Use this if you are using Spring Boot 3.0 or lower
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency> 
-->

```

### /src/main/resources/application.properties Modified

```
#spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.url=jdbc:mysql://localhost:3306/todos
spring.datasource.username=todos-user
spring.datasource.password=dummytodos
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
#/connect todos-user@localhost:3306
#docker run --detach 
#--env MYSQL_ROOT_PASSWORD=dummypassword 
#--env MYSQL_USER=todos-user 
#--env MYSQL_PASSWORD=dummytodos 
#--env MYSQL_DATABASE=todos 
#--name mysql 
#--publish 3306:3306 
#mysql:8-oracle
```
