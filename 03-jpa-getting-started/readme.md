## Getting Started with JPA and Hibernate

- Step 01 - Getting Started with JPA and Hibernate - Goals
- Step 02 - Setting up New Spring Boot Project for JPA and Hibernate
- Step 03 - Launching up H2 Console and Creating Course Table in H2
- Step 04 - Getting Started with Spring JDBC
- Step 05 - Inserting Hardcoded Data using Spring JDBC
- Step 06 - Inserting and Deleting Data using Spring JDBC
- Step 07 - Querying Data using Spring JDBC
- Step 08 - Getting Started with JPA and EntityManager
- Step 09 - Exploring the Magic of JPA
- Step 10 - Getting Started with Spring Data JPA
- Step 11 - Exploring features of Spring Data JPA
- Step 12 - Understanding difference between Hibernate and JPA


<!---
Current Directory : /Users/ranga/Ranga/git/00.courses/getting-started-for-beginners-v2/jpa-and-hibernate-in-10-steps
-->

## Complete Code Example


### /notes.txt

```

insert into course (id, name, author)
values(1, 'Learn AWS','in28minutes');

select * from course

delete from course where id=1
```
---

### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.springboot</groupId>
	<artifactId>learn-jpa-and-hibernate</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>learn-jpa-and-hibernate</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
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

### /src/main/java/com/in28minutes/springboot/learnjpaandhibernate/LearnJpaAndHibernateApplication.java

```java
package com.in28minutes.springboot.learnjpaandhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnJpaAndHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaAndHibernateApplication.class, args);
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/learnjpaandhibernate/course/Course.java

```java
package com.in28minutes.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
	
	@Id
	private long id;
	
	private String name;
	
	private String author;

	public Course() {

	}

	public Course(long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", author=" + author + "]";
	}

	// constructor
	// getters
	// toString

}
```
---

### /src/main/java/com/in28minutes/springboot/learnjpaandhibernate/course/CourseCommandLineRunner.java

```java
package com.in28minutes.springboot.learnjpaandhibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner{

//	@Autowired
//	private CourseJdbcRepository repository;

//	@Autowired
//	private CourseJpaRepository repository;

	@Autowired
	private CourseSpringDataJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1, "Learn AWS Jpa!", "in28minutes"));
		repository.save(new Course(2, "Learn Azure Jpa!", "in28minutes"));
		repository.save(new Course(3, "Learn DevOps Jpa!", "in28minutes"));
		
		repository.deleteById(1l);
		
		System.out.println(repository.findById(2l));
		System.out.println(repository.findById(3l));
		
		System.out.println(repository.findAll());
		System.out.println(repository.count());
		
		System.out.println(repository.findByAuthor("in28minutes"));
		System.out.println(repository.findByAuthor(""));

		System.out.println(repository.findByName("Learn AWS Jpa!"));
		System.out.println(repository.findByName("Learn DevOps Jpa!"));

		
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/learnjpaandhibernate/course/jdbc/CourseJdbcRepository.java

```java
package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

@Repository
public class CourseJdbcRepository {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private static String INSERT_QUERY = 
			
			"""
				insert into course (id, name, author)
				values(?, ?,?);
	
			""";

	private static String DELETE_QUERY = 
			
			"""
				delete from course
				where id = ?
	
			""";

	private static String SELECT_QUERY = 
			
			"""
				select * from course
				where id = ?
	
			""";
	
	public void insert(Course course) {
		springJdbcTemplate.update(INSERT_QUERY, 
				course.getId(), course.getName(), course.getAuthor());
	}
	
	public void deleteById(long id) {
		springJdbcTemplate.update(DELETE_QUERY,id);
	}
	
	public Course findById(long id) {
		//ResultSet -> Bean => Row Mapper => 
		return springJdbcTemplate.queryForObject(SELECT_QUERY,
				new BeanPropertyRowMapper<>(Course.class), id);
		
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/learnjpaandhibernate/course/jpa/CourseJpaRepository.java

```java
package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJpaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Course course) {
		entityManager.merge(course);
	}
	
	public Course findById(long id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteById(long id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/learnjpaandhibernate/course/springdatajpa/CourseSpringDataJpaRepository.java

```java
package com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{
	
	List<Course> findByAuthor(String author);
	List<Course> findByName(String name);

}
```
---

### /src/main/resources/application.properties

```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.show-sql=true
```
---

### /src/main/resources/schema.sql

```
create table course 
(
	id bigint not null,
	name varchar(255) not null,
	author varchar(255) not null,
	primary key (id)

);
```
---

### /src/test/java/com/in28minutes/springboot/learnjpaandhibernate/LearnJpaAndHibernateApplicationTests.java

```java
package com.in28minutes.springboot.learnjpaandhibernate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnJpaAndHibernateApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---
