# Spring AOP - Step By Step Changes

## Step 04

- Step 01 - Getting Started with Spring AOP - An overview
- Step 02 - What is Aspect Oriented Programming?
- Step 03 - Creating a Spring Boot Project for Spring AOP
- Step 04 - Setting up Spring Components for Spring AOP


##### /src/main/java/com/in28minutes/learnspringaop/LearnSpringAopApplication.java

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

##### /src/main/java/com/in28minutes/learnspringaop/aopexample/business/BusinessService1.java

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

##### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService1.java

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

##### /src/main/resources/application.properties

```properties
spring.main.banner-mode=off
logging.pattern.console= %d{MM-dd HH:mm:ss} - %logger{36} - %msg%n
```
---

##### /src/test/java/com/in28minutes/learnspringaop/LearnSpringAopApplicationTests.java

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
## Step 06

- Step 05 - Creating AOP Logging Aspect and Pointcut
- Step 06 - Understanding AOP Terminology

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/LoggingAspect.java New

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

## Step 08

- Step 07 - Exploring @After, @AfterReturning, @AfterThrowing AOP Annotations
- Step 08 - Exploring Around AOP annotations with a Timer class

### /src/main/java/com/in28minutes/learnspringaop/LearnSpringAopApplication.java Modified

```java
package com.in28minutes.learnspringaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.learnspringaop.aopexample.business.BusinessService1;
import com.in28minutes.learnspringaop.aopexample.business.BusinessService2;

@SpringBootApplication
public class LearnSpringAopApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BusinessService1 businessService1;
	
	private BusinessService2 businessService2;
	
	public LearnSpringAopApplication(BusinessService1 businessService1,
			BusinessService2 businessService2) {
		this.businessService1 = businessService1;
		this.businessService2 = businessService2;
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("businessService1 Value returned is {}", businessService1.calculateMax());
		logger.info("businessService2 Value returned is {}", businessService2.calculateMin());
	}
}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/LoggingAspect.java Modified

```java
package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
		logger.info("Before Aspect - {} is called with arguments: {}"
				,  joinPoint, joinPoint.getArgs());//WHAT
	}

	@After("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {
		logger.info("After Aspect - {} has executed",  joinPoint);
	}

	@AfterThrowing(
	pointcut = "execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))",
	throwing = "exception"
	)
	public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
		logger.info("AfterThrowing Aspect - {} has thrown an exception {}"
				,  joinPoint, exception);
	}

	@AfterReturning(
	pointcut = "execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))",
	returning = "resultValue"
	)
	public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, 
			Object resultValue) {
		logger.info("AfterReturning Aspect - {} has returned {}"
				,  joinPoint, resultValue);
	}


}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/PerformanceTrackingAspect.java New

```java
package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Around("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		//Start a timer
		long startTimeMillis = System.currentTimeMillis();
		
		//Execute the method
		Object returnValue = proceedingJoinPoint.proceed();
		
		//Stop the timer
		long stopTimeMillis = System.currentTimeMillis();
		
		long executionDuration = stopTimeMillis - startTimeMillis;
		
		logger.info("Around Aspect - {} Method executed in {} ms"
				,proceedingJoinPoint, executionDuration);
		
		return returnValue;
	}

}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/business/BusinessService1.java Modified
```
//throw new RuntimeException("Something Went Wrong!");
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/business/BusinessService2.java New

```java
package com.in28minutes.learnspringaop.aopexample.business;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.in28minutes.learnspringaop.aopexample.data.DataService2;

@Service
public class BusinessService2 {
	
	private DataService2 dataService2;
	
	public BusinessService2(DataService2 dataService2) {
		this.dataService2 = dataService2;
	}
	
	public int calculateMin() {
		int[] data = dataService2.retrieveData();
		
		//throw new RuntimeException("Something Went Wrong!");
		return Arrays.stream(data).min().orElse(0);
	}
}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService1.java Modified
New Lines
```
		
try {
	Thread.sleep(30);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService2.java New

```java
package com.in28minutes.learnspringaop.aopexample.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService2 {
	
	public int[] retrieveData() {
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new int[] {111, 222, 333, 444, 555};
	}

}
```
---

## Step 09

- Step 09 - AOP Best Practice - Creating Common Pointcut Definitions

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/CommonPointcutConfig.java New

```java
package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
	
	@Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
	public void businessAndDataPackageConfig() {}

	@Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.business.*.*(..))")
	public void businessPackageConfig() {}

	@Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.data.*.*(..))")
	public void dataPackageConfig() {}
	
	@Pointcut("bean(*Service*)")
	public void allPackageConfigUsingBean() {}

}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/LoggingAspect.java Modified
New Lines
```
	@Before("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()")//WHEN
	@After("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessPackageConfig()")
	pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()",
	pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()",
```

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/PerformanceTrackingAspect.java Modified
New Lines
```
	@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
```


## Step 11

- Step 10 - Creating Track Time Annotation
- Step 11 - Getting Started with Spring AOP - Thank You

### /src/main/java/com/in28minutes/learnspringaop/aopexample/annotations/TrackTime.java New

```java
package com.in28minutes.learnspringaop.aopexample.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {

}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/CommonPointcutConfig.java Modified
New Lines
```
@Pointcut("@annotation(com.in28minutes.learnspringaop.aopexample.annotations.TrackTime)")
public void trackTimeAnnotation() {}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/PerformanceTrackingAspect.java Modified
New Lines
```
//@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.trackTimeAnnotation()")
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/business/BusinessService1.java Modified
New Lines
```
import com.in28minutes.learnspringaop.aopexample.annotations.TrackTime;

@TrackTime
public int calculateMax() {
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService2.java Modified
New Lines
```
import com.in28minutes.learnspringaop.aopexample.annotations.TrackTime;
	
	@TrackTime
	public int[] retrieveData() {
```
