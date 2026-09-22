## First 5 Steps in JUnit

### Step 1 : What is JUnit and Unit Testing?
 - What is JUnit?
 - What is Unit Testing?
 - Advantages of Unit Testing

### Step 2 : First JUnit Project and Green Bar
 - What is JUnit?
 - First Project with JUnit
 - First JUnit Class
 - No Failure is Success
 - MyMath class with sum method

### Step 3 : First Code and First Unit Test
 - Unit test for the sum method

### Step 4 : Other assert methods
 - assertTrue and assertFalse methods

### Step 5 : Important annotations
  - @Before @After annotations
  - @BeforeClass @AfterClass annotations


<!---
Current Directory : /Users/rangakaranam/Ranga/git/00.courses/spring-boot-master-class/03.JUnit-Introduction-In-5-Steps-V2
-->

## Complete Code Example


### /src/com/in28minutes/junit/MyMath.java

```java
package com.in28minutes.junit;

public class MyMath {

	//{1,2,3} => 1+2+3 = 6
	public int calculateSum(int[] numbers) {
		
		int sum = 0;
		
		for(int number:numbers) {
			sum += number;
		}
		
		return sum;
	}
	

}
```
---

### /test/com/in28minutes/junit/MyAssertTest.java

```java
package com.in28minutes.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");
	
	@Test
	void testAsserts() {
		boolean test = todos.contains("AWS");//Result
		boolean test2 = todos.contains("GCP");//Result
		
		//assertEquals(true, test);
		assertTrue(test);
		assertFalse(test2);
		//assertNull,assertNotNull
		assertArrayEquals(new int[] {1,2}, new int[] {2, 1});
		
		assertEquals(3, todos.size());		
	}

}
```
---

### /test/com/in28minutes/junit/MyBeforeAfterTest.java

```java
package com.in28minutes.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyBeforeAfterTest {

	
	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
	}

	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}

	@Test
	void test3() {
		System.out.println("test3");
	}

	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}

}
```
---

### /test/com/in28minutes/junit/MyMathTest.java

```java
package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {

	private MyMath math = new MyMath();
	
	@Test
	void calculateSum_ThreeMemberArray() {		
		assertEquals(6, math.calculateSum(new int[] {1,2,3}));
	}

	@Test
	void calculateSum_ZeroLengthArray() {		
		assertEquals(0, math.calculateSum(new int[] {}));
	}

}
```
---

## Useful References

### JUnit (official)
- JUnit User Guide : https://docs.junit.org/current/user-guide/
- JUnit Javadoc : https://docs.junit.org/current/api/
- `Assertions` API : https://docs.junit.org/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html
- `Assumptions` API : https://docs.junit.org/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assumptions.html
- Parameterized tests : https://docs.junit.org/current/user-guide/#writing-tests-parameterized-tests
- Parameterized classes : https://docs.junit.org/current/user-guide/#writing-tests-parameterized-classes
- Release notes : https://docs.junit.org/current/release-notes/
- Maven starter pom : https://github.com/junit-team/junit-examples/tree/r6.0.3/junit-jupiter-starter-maven
- Source on GitHub : https://github.com/junit-team/junit

### Build tools
- Maven Surefire : https://maven.apache.org/surefire/maven-surefire-plugin/
- Surefire + JUnit 5 : https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html
- Gradle + JUnit 5 : https://docs.gradle.org/current/userguide/java_testing.html#using_junit5

### Spring Boot testing
- Testing in Spring Boot : https://docs.spring.io/spring-boot/reference/testing/index.html
- `@SpringBootTest` : https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/context/SpringBootTest.html
- Slice tests : https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.autoconfigured-tests
- Testcontainers + Spring Boot : https://java.testcontainers.org/modules/spring_boot/


---
