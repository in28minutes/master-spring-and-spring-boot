<!---
Current Directory : /Users/ranga/Ranga/git/00.courses/master-spring-and-spring-boot/11-spring-aop
-->

## Complete Code Example


### /src/main/java/com/in28minutes/learnspringaop/LearnSpringAopApplication.java

```java
package com.in28minutes.learnspringaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.learnspringaop.aopexample.business.BusinessService1;

@SpringBootApplication
public class LearnSpringAopApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BusinessService1 businessService1;
	
	public LearnSpringAopApplication(BusinessService1 businessService1) {
		this.businessService1 = businessService1;
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Value returned is {}", businessService1.calculateMax());
	}
}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/LoggingAspect.java

```java
package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//Pointcut - When?
	// execution(* PACKAGE.*.*(..))
	@Before("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")//WHEN
	public void logMethodCall(JoinPoint joinPoint) {
		logger.info("Before Aspect - {} is called with arguments: {}"
				,  joinPoint, joinPoint.getArgs());//WHAT
	}

}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/business/BusinessService1.java

```java
package com.in28minutes.learnspringaop.aopexample.business;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.in28minutes.learnspringaop.aopexample.data.DataService1;

@Service
public class BusinessService1 {
	
	private DataService1 dataService1;
	
	public BusinessService1(DataService1 dataService1) {
		this.dataService1 = dataService1;
	}
	
	public int calculateMax() {
		int[] data = dataService1.retrieveData();
		return Arrays.stream(data).max().orElse(0);
	}
}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService1.java

```java
package com.in28minutes.learnspringaop.aopexample.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService1 {
	
	public int[] retrieveData() {
		return new int[] {11, 22, 33, 44, 55};
	}

}
```
---

### /src/main/resources/application.properties

```properties
spring.main.banner-mode=off
logging.pattern.console= %d{MM-dd HH:mm:ss} - %logger{36} - %msg%n
```
---

### /src/test/java/com/in28minutes/learnspringaop/LearnSpringAopApplicationTests.java

```java
package com.in28minutes.learnspringaop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringAopApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---
