- [Section 01](#section-01)
  * [Getting Started with Java Spring Framework](#getting-started-with-java-spring-framework)
    + [Step 03](#step-03)
    + [Step 04](#step-04)
    + [Step 05](#step-05)
    + [Step 06](#step-06)
    + [Step 08](#step-08)
    + [Step 09](#step-09)
    + [Step 10](#step-10)
    + [Step 14](#step-14)
    + [Step 15](#step-15)
- [Section 02](#section-02)
  * [Using Spring Framework to Create and Manage Your Java Objects](#using-spring-framework-to-create-and-manage-your-java-objects)
    + [Step 01](#step-01)
    + [Step 02](#step-02)
    + [Step 04](#step-04-1)
    + [Step 08](#step-08-1)
- [Section 03](#section-03)
  * [Exploring Spring Framework Advanced Features](#exploring-spring-framework-advanced-features)
    + [Step 03](#step-03-1)
    + [Step 07](#step-07)
    + [Step 10](#step-10-1)


# Section 01

## Getting Started with Java Spring Framework

### Step 03

- Step 01 - Understanding the Need for Java Spring Framework
- Step 02 - Getting Started with Java Spring Framework
- Step 03 - Creating a New Spring Framework Project with Maven and Java


#### /learn-spring-framework/pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes</groupId>
	<artifactId>learn-spring-framework</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>learn-spring-framework</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
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

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/LearnSpringFrameworkApplication.java

```java
package com.in28minutes.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringFrameworkApplication.class, args);
	}

}
```
---

#### /learn-spring-framework/src/main/resources/application.properties

```properties

```
---

#### /learn-spring-framework/src/test/java/com/in28minutes/learnspringframework/LearnSpringFrameworkApplicationTests.java

```java
package com.in28minutes.learnspringframework;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringFrameworkApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---
### Step 04

- Step 04 - Getting Started with Java Gaming Application

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/AppGamingBasicJava.java New

```java
package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.MarioGame;

public class AppGamingBasicJava {

	public static void main(String[] args) {
		
		var marioGame = new MarioGame();
		var gameRunner = new GameRunner(marioGame);
		gameRunner.run();

	}

}
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/GameRunner.java New

```java
package com.in28minutes.learnspringframework.game;

public class GameRunner {
	MarioGame game;
	
	public GameRunner(MarioGame game) {
		this.game = game;
	}

	public void run() {
		
		System.out.println("Running game: " + game);
		game.up();
		game.down();
		game.left();
		game.right();
		
	}

}
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/MarioGame.java New

```java
package com.in28minutes.learnspringframework.game;

public class MarioGame {
	
	public void up() {
		System.out.println("Jump");
	}

	public void down() {
		System.out.println("Go into a hole");
	}
	
	public void left() {
		System.out.println("Go back");
	}

	public void right() {
		System.out.println("Accelerate");
	}


}
```
---

### Step 05

- Step 05 - Understanding Loose Coupling and Tight Coupling


#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/SuperContraGame.java New

```java
package com.in28minutes.learnspringframework.game;

public class SuperContraGame {

	public void up() {
		System.out.println("up");
	}

	public void down() {
		System.out.println("Sit down");
	}
	
	public void left() {
		System.out.println("Go back");
	}

	public void right() {
		System.out.println("Shoot a bullet");
	}

}
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/AppGamingBasicJava.java Modified
```
import com.in28minutes.learnspringframework.game.SuperContraGame;
//var marioGame = new MarioGame();
var superContraGame = new SuperContraGame();
var gameRunner = new GameRunner(superContraGame);
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/GameRunner.java Modified

```
private SuperContraGame game;
public GameRunner(SuperContraGame game) {
```


### Step 06

- Step 06 - Introducting Java Interface to Make App Loosely Coupled


#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/GamingConsole.java New

```java
package com.in28minutes.learnspringframework.game;

public interface GamingConsole {
	void up();
	void down();
	void left();
	void right();
}
```
---
#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/MarioGame.java Modified

```
public class MarioGame implements GamingConsole{
```

---
#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/SuperContraGame.java Modified

```
public class SuperContraGame implements GamingConsole{
```

---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/AppGamingBasicJava.java Modified

```
//var game = new MarioGame();
//var game = new SuperContraGame();
var game = new PacmanGame();
var gameRunner = new GameRunner(game);
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/GameRunner.java Modified

```
//PacmanGame
private GamingConsole game;
public GameRunner(GamingConsole game) {
```

---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/game/PacmanGame.java New

```java
package com.in28minutes.learnspringframework.game;

public class PacmanGame implements GamingConsole{
	
	public void up() {
		System.out.println("up");
	}

	public void down() {
		System.out.println("down");
	}
	
	public void left() {
		System.out.println("left");
	}

	public void right() {
		System.out.println("right");
	}


}
```
---

### Step 08

- Step 07 - Bringing in Spring Framework to Make Java App Loosely Coupled
- Step 08 - Your First Java Spring Bean and Launching Java Spring Configuration


#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/LearnSpringFrameworkApplication.java Deleted

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/AppGamingBasicJava.java RENAMED TO
#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App01GamingBasicJava.java

```java
package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.PacmanGame;

public class App01GamingBasicJava {

	public static void main(String[] args) {
		
		//var game = new MarioGame();
		//var game = new SuperContraGame();
		
		var game = new PacmanGame(); //1: Object Creation
		
		var gameRunner = new GameRunner(game); 
			//2: Object Creation + Wiring of Dependencies
			// Game is a Dependency of GameRunner
		
		gameRunner.run();

	}

}
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App02HelloWorldSpring.java New

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {

	public static void main(String[] args) {

		//1: Launch a Spring Context
		var context = 
			new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		
		//2: Configure the things that we want Spring to manage - 
		//HelloWorldConfiguration - @Configuration
		//name - @Bean
		
		//3: Retrieving Beans managed by Spring
		System.out.println(context.getBean("name"));
		
	}

}
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/HelloWorldConfiguration.java New

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
	
	@Bean
	public String name() {
		return "Ranga";
	}
	
	

}
```
---

### Step 09

- Step 09 - Creating More Java Spring Beans in Spring Java Configuration File


#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App02HelloWorldSpring.java Modified

```
System.out.println(context.getBean("age"));
System.out.println(context.getBean("person"));
System.out.println(context.getBean("address"));
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/HelloWorldConfiguration.java Modified

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Eliminate verbosity in creating Java Beans
//Public accessor methods, constructor, 
//equals, hashcode and toString are automatically created. 
//Released in JDK 16.

record Person (String name, int age) { };

//Address - firstLine & city
record Address(String firstLine, String city){ };

@Configuration
public class HelloWorldConfiguration {
	
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20);		
	}
	
	@Bean
	public Address address() {
		return new Address("Baker Street", "London");		
	}
	

}
```
---

### Step 10

- Step 10 - Implementing Auto Wiring in Spring Framework Java Configuration File

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App02HelloWorldSpring.java Modified

```
System.out.println(context.getBean("person2MethodCall"));
System.out.println(context.getBean("person3Parameters"));
System.out.println(context.getBean("address2"));
//System.out.println(context.getBean(Address.class));
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/HelloWorldConfiguration.java Modified

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Eliminate verbosity in creating Java Beans
//Public accessor methods, constructor, 
//equals, hashcode and toString are automatically created. 
//Released in JDK 16.

record Person (String name, int age, Address address) { };

//Address - firstLine & city
record Address(String firstLine, String city){ };

@Configuration
public class HelloWorldConfiguration {
	
	@Bean
	public String name() {
		return "Ranga";
	}
	
	@Bean
	public int age() {
		return 15;
	}
	
	@Bean
	public Person person() {
		return new Person("Ravi", 20, new Address("Main Street", "Utrecht"));		
	}

	@Bean
	public Person person2MethodCall() {
		return new Person(name(), age(), address()); //name, age		
	}

	@Bean
	public Person person3Parameters(String name, int age, Address address3) {
		//name,age,address2
		return new Person(name, age, address3); //name, age		
	}

	@Bean(name = "address2")
	public Address address() {
		return new Address("Baker Street", "London");		
	}

	@Bean(name = "address3")
	public Address address3() {
		return new Address("Motinagar", "Hyderabad");		
	}

}
```
---

### Step 14

- Step 11 - Questions about Spring Framework - What will we learn?
- Step 12 - Understanding Spring IOC Container - Application Context and Bean Factory
- Step 13 - Exploring Java Bean vs POJO vs Spring Bean
- Step 14 - Exploring Spring Framework Bean Auto Wiring - Primary and Qualifier Annotations

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App02HelloWorldSpring.java Modified

```
import java.util.Arrays;
		
System.out.println(context.getBean(Person.class));
System.out.println(context.getBean(Address.class));
System.out.println(context.getBean("person5Qualifier"));
//System.out.println
//		Arrays.stream(context.getBeanDefinitionNames())
//			.forEach(System.out::println);
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/HelloWorldConfiguration.java Modified

```java
@Bean
@Primary
//No qualifying bean of type 'com.in28minutes.learnspringframework.Address' 
//available: expected single matching bean but found 2: address2,address3
public Person person4Parameters(String name, int age, Address address) {
	//name,age,address2
	return new Person(name, age, address); //name, age		
}

@Bean
public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address) {
	//name,age,address2
	return new Person(name, age, address); //name, age		
}


@Bean(name = "address2")
@Primary
public Address address() {
	return new Address("Baker Street", "London");		
}

@Bean(name = "address3")
@Qualifier("address3qualifier")
public Address address3() {
	return new Address("Motinagar", "Hyderabad");		
}

```

### Step 15

- Step 15 - Using Spring Framework to Manage Beans for Java Gaming App

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/HelloWorldConfiguration.java Moved to
#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/helloworld/HelloWorldConfiguration.java New

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App02HelloWorldSpring.java Moved to
#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/helloworld/App02HelloWorldSpring.java

---
#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/App03GamingSpringBeans.java New

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;

public class App03GamingSpringBeans {

	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(GamingConfiguration.class)) {

			context.getBean(GamingConsole.class).up();
			
			context.getBean(GameRunner.class).run();

		}
	}
}
```
---

#### /learn-spring-framework/src/main/java/com/in28minutes/learnspringframework/GamingConfiguration.java New

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.PacmanGame;

@Configuration
public class GamingConfiguration {

	@Bean
	public GamingConsole game() {
		var game = new PacmanGame();
		return game;
	}

	@Bean
	public GameRunner gameRunner(GamingConsole game) {
		var gameRunner = new GameRunner(game);
		return gameRunner;
	}

}
```
---


# Section 02

## Using Spring Framework to Create and Manage Your Java Objects

- Step 01 - Getting Spring Framework to Create and Manage Your Java Objects

### Step 01

Getting Started
- Rename `learn-spring-framework` to `learn-spring-framework-01`
- Copy `learn-spring-framework-01` to `learn-spring-framework-02`
- Close project `learn-spring-framework-01`
- Start Work on `learn-spring-framework-02`

---

#### /learn-spring-framework-02/pom.xml New

No change
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/GamingAppLauncherApplication.java Copied

@ComponentScan("com.in28minutes.learnspringframework.game")

```java
package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;

@Configuration
@ComponentScan("com.in28minutes.learnspringframework.game")
public class GamingAppLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(GamingAppLauncherApplication.class)) {

			context.getBean(GamingConsole.class).up();
			
			context.getBean(GameRunner.class).run();

		}
	}
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/GameRunner.java Copied

Added @Component

```java
package com.in28minutes.learnspringframework.game;

import org.springframework.stereotype.Component;

@Component
public class GameRunner {
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/PacmanGame.java Copied

Added @Component

```java
package com.in28minutes.learnspringframework.game;

import org.springframework.stereotype.Component;

@Component
public class PacmanGame implements GamingConsole{

```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/SuperContraGame.java Copied
---

### Step 02

- Step 02 - Exploring Primary and Qualifier Annotations for Spring Components

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/GameRunner.java Modified

```
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class GameRunner {
	
	private GamingConsole game;
	
	public GameRunner(@Qualifier("SuperContraGameQualifier") GamingConsole game) { //NEW

```

---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/MarioGame.java Modified

```
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary
public class MarioGame implements GamingConsole{
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/SuperContraGame.java Modified

```
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
@Qualifier("SuperContraGameQualifier")
public class SuperContraGame implements GamingConsole{
```
---

### Step 04

- Step 03 - Primary and Qualifier - Which Spring Annotation Should You Use?
- Step 04 - Exploring Spring Framework - Different Types of Dependency Injection

---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/GamingAppLauncherApplication.java MOVED TO
#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/game/GamingAppLauncherApplication.java
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/a0/SimpleSpringContextLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.a0;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SimpleSpringContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(SimpleSpringContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);

		}
	}
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/a1/DepInjectionLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.a1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class YourBusinessClass {

	Dependency1 dependency1;

	Dependency2 dependency2;

	//@Autowired
	public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
		super();
		System.out.println("Constructor Injection - YourBusinessClass ");
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}

//	@Autowired
//	public void setDependency1(Dependency1 dependency1) {
//		System.out.println("Setter Injection - setDependency1 ");
//		this.dependency1 = dependency1;
//	}
//
//	@Autowired
//	public void setDependency2(Dependency2 dependency2) {
//		System.out.println("Setter Injection - setDependency2 ");
//		this.dependency2 = dependency2;
//	}

	public String toString() {
		return "Using " + dependency1 + " and " + dependency2;
	}

}

@Component
class Dependency1 {

}

@Component
class Dependency2 {

}

@Configuration
@ComponentScan
public class DepInjectionLauncherApplication {

	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)) {

			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

			System.out.println(context.getBean(YourBusinessClass.class));

		}
	}
}
```

---

### Step 08

- Step 05 - Java Spring Framework - Understanding Important Terminology
- Step 06 - Java Spring Framework - Comparing @Component vs @Bean
- Step 07 - Why do we have dependencies in Java Spring Applications?
- Step 08 - Exercise/ Solution for Real World Java Spring Framework Example


#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/BusinessCalculationService.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class BusinessCalculationService {
	
	private DataService dataService;
	
	public BusinessCalculationService(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findMax() {
		return Arrays.stream(dataService.retrieveData()).max().orElse(0);
	}

}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/DataService.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

public interface DataService {
	int[] retrieveData();
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MongoDbDataService.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbDataService implements DataService {

	@Override
	public int[] retrieveData() {
		return new int[] { 11, 22, 33, 44, 55 };
	}

}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MySqlDataService.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.stereotype.Component;

@Component
public class MySqlDataService implements DataService {

	@Override
	public int[] retrieveData() {
		return new int[] { 1, 2, 3, 4, 5 };
	}

}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/RealWorldSpringContextLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RealWorldSpringContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(RealWorldSpringContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(
					context.getBean(BusinessCalculationService.class).findMax());
			
		}
	}
}
```

# Section 03

## Exploring Spring Framework Advanced Features

### Step 03

- Step 01 - Exploring Lazy and Eager Initialization of Spring Framework Beans
- Step 02 - Comparing Lazy Initialization vs Eager Initialization
- Step 03 - Exploring Java Spring Framework Bean Scopes - Prototype and Singleton

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/d1/LazyInitializationLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.d1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA {
	
}

@Component
@Lazy
class ClassB {
	
	private ClassA classA;
	
	public ClassB(ClassA classA) {
		//Logic
		System.out.println("Some Initialization logic");
		this.classA = classA;
	}
	
	public void doSomething() {
		System.out.println("Do Something");
	}
	
}


@Configuration
@ComponentScan
public class LazyInitializationLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(LazyInitializationLauncherApplication.class)) {
			
			System.out.println("Initialization of context is completed");
			
			context.getBean(ClassB.class).doSomething();
			

		}
	}
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/e1/BeanScopesLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.e1;

import java.util.Arrays;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass {
	
}


@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {
	
}


@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(BeanScopesLauncherApplication.class)) {
			
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			

		}
	}
}
```
---

### Step 07

- Step 04 - Comparing Prototype vs Singleton - Spring Framework Bean Scopes
- Step 05 - Exploring Spring Beans - PostConstruct and PreDestroy
- Step 06 - Evolution of Jakarta EE - Comparing with J2EE and Java EE
- Step 07 - Exploring Jakarta CDI with Spring Framework and Java

#### /learn-spring-framework-02/pom.xml Modified

```
<dependency>
	<groupId>jakarta.inject</groupId>
	<artifactId>jakarta.inject-api</artifactId>
	<version>2.0.1</version>
</dependency>
```

---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/f1/PrePostAnnotationsContextLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.f1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class SomeClass {
	
	private SomeDependency someDependency;
	
	public SomeClass(SomeDependency someDependency) {
		super();
		this.someDependency = someDependency;
		System.out.println("All dependencies are ready!");
	}
	
	@PostConstruct
	public void initialize() {
		someDependency.getReady();
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("Cleanup");
	}
	
}

@Component 
class SomeDependency {

	public void getReady() {
		System.out.println("Some logic using SomeDependency");
		
	}
	
}

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(PrePostAnnotationsContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);

		}
	}
}
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/g1/CdiContextLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.g1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jakarta.inject.Inject;
import jakarta.inject.Named;


//@Component
@Named
class BusinessService {
	private DataService dataService;

	//@Autowired
	@Inject
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}


	public DataService getDataService() {
		return dataService;
	}

	
	
}

//@Component
@Named
class DataService {
	
}

@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new AnnotationConfigApplicationContext
					(CdiContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class)
					.getDataService());

		}
	}
}
```
---

### Step 10

- Step 08 - Exploring Java Spring XML Configuration
- Step 09 - Exploring Java Annotations vs XML Configuration for Java Spring Framework
- Step 10 - Exploring Spring Framework Stereotype Annotations - Component and more

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/BusinessCalculationService.java Modified

```
import org.springframework.stereotype.Service;
//@Component
@Service
```

---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MongoDbDataService.java Modified

```
import org.springframework.stereotype.Repository;
//@Component
@Repository
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/c1/MySqlDataService.java Modified

```
import org.springframework.stereotype.Repository;
//@Component
@Repository
```

---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/d1/LazyInitializationLauncherApplication.java Modified

```
@SuppressWarnings("unused")
private ClassA classA;
```
---

#### /learn-spring-framework-02/src/main/java/com/in28minutes/learnspringframework/examples/h1/XmlConfigurationContextLauncherApplication.java New

```java
package com.in28minutes.learnspringframework.examples.h1;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunner;

public class XmlConfigurationContextLauncherApplication {
	
	public static void main(String[] args) {

		try (var context = 
				new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
			
			Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
			
			System.out.println(context.getBean("name"));
			
			System.out.println(context.getBean("age"));
			
			context.getBean(GameRunner.class).run();

		}
	}
}
```
---

#### /learn-spring-framework-02/src/main/resources/contextConfiguration.xml New

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> <!-- bean definitions here -->
	
	<bean id="name" class="java.lang.String">
		<constructor-arg value="Ranga" />
	</bean>

	<bean id="age" class="java.lang.Integer">
		<constructor-arg value="35" />
	</bean>

<!-- 	<context:component-scan 
		base-package="com.in28minutes.learnspringframework.game"/>
 -->
 	<bean id="game" class="com.in28minutes.learnspringframework.game.PacmanGame"/>
 	
 	<bean id="gameRunner" 
 		class="com.in28minutes.learnspringframework.game.GameRunner">
 		<constructor-arg ref="game" />
 	</bean>
 	
</beans>
```
---
