<!---
Current Directory : /Ranga/001.Notes/00.CoursePreparations/2022-06-Spring-Boot-Upgrade-Code/trial
-->

## Complete Code Example

### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.0-M3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.springboot.web</groupId>
	<artifactId>spring-boot-first-web-application</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-first-web-application</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>18</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>jakarta.servlet.jsp.jstl</groupId>
			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		</dependency>

		<dependency>
			<groupId>org.eclipse.jetty</groupId>
			<artifactId>glassfish-jstl</artifactId>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>5.1.3</version>
		</dependency>

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap-datepicker</artifactId>
			<version>1.9.0</version> <!-- Changed from 1.9.0 -->
		</dependency>

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.6.0</version> <!-- Changed from 3.6.0 -->
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>

</project>
```
---

### /src/main/java/com/in28minutes/springboot/web/SpringBootFirstWebApplication.java

```java
package com.in28minutes.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/login/AuthenticationService.java

```java
package com.in28minutes.springboot.web.login;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
	public boolean authenticateUser(String user, String password) {
		boolean isValidUser = user.equalsIgnoreCase("in28minutes");
		boolean isValidPassword = password.equalsIgnoreCase("dummy");
		return isValidUser && isValidPassword;
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/web/login/LoginController.java

```java
package com.in28minutes.springboot.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

	public LoginController(AuthenticationService service) {
		this.service = service;
	}

	private final AuthenticationService service;

	// say-hello-jsp -> login.jsp
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name
			, @RequestParam String password) {
		boolean isValidUser = service.authenticateUser(name, password);
		
		if(!isValidUser) {
			model.addAttribute("errorMessage", "Invalid Credentials. Please try again!");
			return "login";
		}
		
		model.addAttribute("name", name);
		return "welcome";
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/sayhello/SayHelloController.java

```java
package com.in28minutes.springboot.web.sayhello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	// localhost:8080/say-hello - "Hello World"
	@RequestMapping("/say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello World v2";
	}
	
	//THIS IS NOT COOL
	//THIS IS NOT RECOMMENDED!!
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String sayHelloWithHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Hello World From HTML</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Hello World From Body");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	//say-hello-jsp -> login.jsp
	@RequestMapping("/say-hello-jsp")
	public String sayHelloWithJSP() {
		return "sayHello";
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/todo/Todo.java

```java
package com.in28minutes.springboot.web.todo;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.Size;

public class Todo {
	
	public Todo(int id, String user, String desc, LocalDate targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}
	
	private int id;
	private String user;
	
	@Size(min = 10, message = "Enter atleast 10 Characters.")
	private String desc;
	private LocalDate targetDate;
	private boolean isDone;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public LocalDate getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", targetDate=" + targetDate + ", isDone="
				+ isDone + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(desc, id, isDone, targetDate, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return Objects.equals(desc, other.desc) && id == other.id && isDone == other.isDone
				&& Objects.equals(targetDate, other.targetDate) && Objects.equals(user, other.user);
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/todo/TodoController.java

```java
package com.in28minutes.springboot.web.todo;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	private TodoService todoService;
	
	@RequestMapping("/list-todos")
	public String showAllTodos(ModelMap model) {
		String name = (String) model.getAttribute("name");
		
		logger.debug("Name from session is {}", name);
		model.put("todos", todoService.findByUser(name));
		
		return "listTodos";
	}

	@RequestMapping("/add-todo")
	public String showTodoPage(ModelMap model) {
		return "todo";
	}
	
	//NOT USED
	public String addTodo(ModelMap model, @RequestParam String desc){
		todoService.addTodo((String) model.get("name"), desc, LocalDate.now().plusYears(2), false);
		return "redirect:/list-todos";
	}

	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodoCommandBean(ModelMap model, Todo todo){
		todoService.addTodo((String) model.get("name"), todo.getDesc(), LocalDate.now().plusYears(2), false);
		return "redirect:/list-todos";
	}

	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id){
		todoService.deleteById(id);
		return "redirect:/list-todos";
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/web/todo/TodoService.java

```java
package com.in28minutes.springboot.web.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<Todo>();

	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "in28minutes", "Learn Spring MVC", LocalDate.now(), false));
		todos.add(new Todo(2, "in28minutes", "Learn Struts", LocalDate.now(), false));
		todos.add(new Todo(3, "in28minutes", "Learn Hibernate", LocalDate.now(), false));
	}

	public List<Todo> findByUser(String user) {
		return todos.stream().filter(todo -> todo.getUser().equalsIgnoreCase(user)).toList();
	}

	public Todo findById(int id) {
		return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
	}

	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}

	public void addTodo(String name, String desc, LocalDate targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteById(int id) {
		todos.removeIf(todo -> todo.getId() == id);
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/listTodos.jsp

```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	    		rel="stylesheet">
<title>All Your Todos!!</title>
</head>
<body>

<div class="container">

<H1>${name} - Your Todos</H1>

<table class="table table-striped">
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-warning" 
		href="/delete-todo?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a type="button" class="btn btn-success" href="/add-todo" >Add a Todo</a>

</div>



</body>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>

</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp

```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>

<pre>${errorMessage}</pre>

<form method="post">
	Name : <input name="name" type="text" />
	Password : <input type="password" name="password" /> 
	<input type="submit" />
</form>

</body>
</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp

```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
My First JSP!!!
</body>
</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp

```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	    		rel="stylesheet">
<title>All Your Todos!!</title>
</head>
<body>

<div class="container">

	<H1>${name} - Enter new todo details</H1>
	
	
	<form method="post">
	
		<fieldset class="form-group mb-3">
			<label>Description</label>
			<input name="desc" type="text" class="form-control" required="required"/>
		</fieldset>
		
		<input type="hidden" name="id" value="-1"/>
		<input type="hidden" name="isDone" value="false"/>
		
		<button type="submit" class="btn btn-success">Add</button>
		 
	</form>

</div>


</body>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>

</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp

```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>

<div class="container">

<H1>Welcome ${name}!</H1>

I'm sure you will have a wonderful learning experience!
<a href="list-todos">Here</a> are your todos!

</div>
</body>
</html>
```
---

### /src/main/resources/application.properties

```properties
logging.level.org.springframework=DEBUG
spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp
```
---

### /src/test/java/com/in28minutes/springboot/web/SpringBootFirstWebApplicationTests.java

```java
package com.in28minutes.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootFirstWebApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---
