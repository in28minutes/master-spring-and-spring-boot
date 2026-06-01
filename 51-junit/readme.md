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

---

## How to Run

**Maven** (needs Java 17+, Maven 3.8+):
```bash
cd 51-junit
mvn test
```

**Eclipse:** *File > Open Projects from File System...* > pick `51-junit` > right-click > *Run As > JUnit Test*.

---

## Notes

### JUnit 4 vs JUnit 5 (Jupiter)

| Concept            | JUnit 4                          | JUnit 5 / 6 (Jupiter)                 |
|--------------------|----------------------------------|---------------------------------------|
| Test annotation    | `@Test` from `org.junit`         | `@Test` from `org.junit.jupiter.api`  |
| Asserts            | `org.junit.Assert.*`             | `org.junit.jupiter.api.Assertions.*`  |
| Before each test   | `@Before`                        | `@BeforeEach`                         |
| After each test    | `@After`                         | `@AfterEach`                          |
| Before all tests   | `@BeforeClass` (static)          | `@BeforeAll` (static)                 |
| After all tests    | `@AfterClass` (static)           | `@AfterAll` (static)                  |
| Expected exception | `@Test(expected = X.class)`      | `assertThrows(X.class, () -> ...)`    |
| Test name override | n/a                              | `@DisplayName("...")`                 |
| Parameterized      | `@RunWith(Parameterized.class)`  | `@ParameterizedTest` + `@ValueSource` |

### Jupiter assertions (cheat sheet)

| Assertion                                  | Use it when                                   |
|--------------------------------------------|-----------------------------------------------|
| `assertEquals(expected, actual)`           | Two values should be equal                    |
| `assertTrue(condition)` / `assertFalse(c)` | A boolean should be true or false             |
| `assertNull(x)` / `assertNotNull(x)`       | A reference should be null or not null        |
| `assertArrayEquals(a, b)`                  | Two arrays should match element by element    |
| `assertThrows(Ex.class, () -> ...)`        | Some code should throw a given exception      |
| `assertAll("group", () -> ..., () -> ...)` | Run all assertions, then report every failure |
| `assertTimeout(Duration, () -> ...)`       | Code should finish within a time budget       |
| `fail("msg")`                              | Force a failure (for unreachable code, etc.)  |

All in `org.junit.jupiter.api.Assertions`.

### Lifecycle annotations

| Annotation     | Runs                                  | Method modifier |
|----------------|---------------------------------------|-----------------|
| `@BeforeAll`   | Once, before all tests in the class   | `static`        |
| `@BeforeEach`  | Before every `@Test` method           | non-static      |
| `@AfterEach`   | After every `@Test` method            | non-static      |
| `@AfterAll`    | Once, after all tests in the class    | `static`        |

Demo: `MyBeforeAfterTest`.

### Style tips
- Test names like sentences: `divide_byZero_throws()`, `calculateSum_emptyArray_returnsZero()`.
- One behaviour per test. For many inputs, use `@ParameterizedTest`.
- Use `assertThrows` instead of `try/catch + fail` for exception tests.

> `ModernJUnit5FeaturesTest.java` shows runnable examples of `@DisplayName`, `@ParameterizedTest`, `assertThrows`, `assertTimeout`, `assumeTrue`, `@RepeatedTest`, `@Disabled`, `@Nested`, `@Tag`.

---

## Complete Code Example


### /src/com/in28minutes/junit/MyMath.java

```java
package com.in28minutes.junit;

import java.util.Objects;

public class MyMath {

	// {1,2,3} => 1+2+3 = 6
	public int calculateSum(int[] numbers) {
		Objects.requireNonNull(numbers, "numbers array must not be null");

		int sum = 0;
		for (int number : numbers) {
			sum += number;
		}
		return sum;
	}

	// Throws ArithmeticException when divisor == 0. Used by the assertThrows demos.
	public int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new ArithmeticException("Cannot divide " + dividend + " by zero");
		}
		return dividend / divisor;
	}

	public boolean isEven(int number) {
		return number % 2 == 0;
	}

}
```
---

### /test/com/in28minutes/junit/MyAssertTest.java

```java
package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

	@Test
	void testAsserts() {
		boolean containsAws = todos.contains("AWS");   // true
		boolean containsGcp = todos.contains("GCP");   // false

		assertTrue(containsAws);
		assertFalse(containsGcp);

		assertNotNull(todos);
		assertNull(null);

		// Array equality is order sensitive.
		assertArrayEquals(new int[] {1, 2}, new int[] {1, 2});

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

	@BeforeAll  static void beforeAll()  { System.out.println("beforeAll"); }
	@BeforeEach void beforeEach()        { System.out.println("BeforeEach"); }

	@Test void test1() { System.out.println("test1"); }
	@Test void test2() { System.out.println("test2"); }
	@Test void test3() { System.out.println("test3"); }

	@AfterEach void afterEach()         { System.out.println("AfterEach"); }
	@AfterAll  static void afterAll()   { System.out.println("afterAll"); }
}
```
---

### /test/com/in28minutes/junit/MyMathTest.java

```java
package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyMathTest {

	private MyMath math = new MyMath();

	@Test
	void calculateSum_ThreeMemberArray() {
		assertEquals(6, math.calculateSum(new int[] {1, 2, 3}));
	}

	@Test
	void calculateSum_ZeroLengthArray() {
		assertEquals(0, math.calculateSum(new int[] {}));
	}

	@Test
	@DisplayName("calculateSum: groups several inputs into one logical test using assertAll")
	void calculateSum_grouped() {
		assertAll("calculateSum",
			() -> assertEquals(0,  math.calculateSum(new int[] {})),
			() -> assertEquals(5,  math.calculateSum(new int[] {5})),
			() -> assertEquals(6,  math.calculateSum(new int[] {1, 2, 3})),
			() -> assertEquals(-3, math.calculateSum(new int[] {-1, -2}))
		);
	}

	@Test
	@DisplayName("divide(_, 0) throws ArithmeticException with a helpful message")
	void divide_byZero_throws() {
		ArithmeticException ex = assertThrows(
			ArithmeticException.class,
			() -> math.divide(10, 0)
		);
		assertEquals("Cannot divide 10 by zero", ex.getMessage());
	}

	@Test
	void divide_happyPath() {
		assertEquals(5, math.divide(10, 2));
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
