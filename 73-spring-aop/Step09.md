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

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/CommonPointcutConfig.java

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

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/LoggingAspect.java

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
	@Before("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()")//WHEN
	public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
		logger.info("Before Aspect - {} is called with arguments: {}"
				,  joinPoint, joinPoint.getArgs());//WHAT
	}

	@After("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessPackageConfig()")
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {
		logger.info("After Aspect - {} has executed",  joinPoint);
	}

	@AfterThrowing(
	pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()",
	throwing = "exception"
	)
	public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
		logger.info("AfterThrowing Aspect - {} has thrown an exception {}"
				,  joinPoint, exception);
	}

	@AfterReturning(
	pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()",
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

### /src/main/java/com/in28minutes/learnspringaop/aopexample/aspects/PerformanceTrackingAspect.java

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
	
	@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
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
		
		//throw new RuntimeException("Something Went Wrong!");
		return Arrays.stream(data).max().orElse(0);
	}
}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/business/BusinessService2.java

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

### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService1.java

```java
package com.in28minutes.learnspringaop.aopexample.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService1 {
	
	public int[] retrieveData() {
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new int[] {11, 22, 33, 44, 55};
	}

}
```
---

### /src/main/java/com/in28minutes/learnspringaop/aopexample/data/DataService2.java

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
