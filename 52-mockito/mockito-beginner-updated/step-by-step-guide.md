# Step by step guide

# Existing Steps

- Step 00 - Introduction to Section - Mockito in 5 Steps
- Step 01 - Setting up a Spring Boot Project
- Step 02 - Understanding problems with Stubs
- Step 03 - Writing your first Mockito test with Mocks
- Step 04 - Simplifying Tests with Mockito Annotations - @Mock, @InjectMocks
- Step 05 - Exploring Mocks further by Mocking List interface

---

## Step 01: Setting up a Spring Boot Project

`/pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.mockito</groupId>
	<artifactId>mockito-demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mockito-demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>25</java.version>
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


</project>
```

`/src/main/java/com/in28minutes/mockito/mockitodemo/MockitoDemoApplication.java`

```java
package com.in28minutes.mockito.mockitodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockitoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockitoDemoApplication.class, args);
	}

}
```

`/src/main/resources/application.properties`

```properties

```

`/src/test/java/com/in28minutes/mockito/mockitodemo/MockitoDemoApplicationTests.java`

```java
package com.in28minutes.mockito.mockitodemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockitoDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

## Step 02: Understanding problems with Stubs

`/src/main/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImpl.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

public class SomeBusinessImpl {
	
	private final DataService dataService;
	
	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}
	
	public int findTheGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();
		int greatestValue = Integer.MIN_VALUE;
		for(int value:data) {
			if(value > greatestValue)
				greatestValue = value;
		}
		return greatestValue;
	}

}

interface DataService {
	int[] retrieveAllData();
	
	
}
```

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplStubTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImplStubTest {

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		DataService dataServiceStub = new DataServiceStub1();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, result);
	}

	@Test
	void findTheGreatestFromAllData_withOneValue() {
		DataService dataServiceStub = new DataServiceStub2();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(35, result);
	}

}

class DataServiceStub1 implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[]{25, 15, 5};
	}
	
}


class DataServiceStub2 implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[]{35};
	}
	
}
```

## Step 03: Writing your first Mockito test with Mocks

Same test as Step 02, with `mock(DataService.class)` and `when().thenReturn()` in place of the two stub classes. The module keeps only the finished annotated version of this file, shown at Step 04.

## Step 04: Simplifying Tests with Mockito Annotations - @Mock, @InjectMocks

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplMockTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {
	
	@Mock
	private DataService dataServiceMock;
	
	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});
		assertEquals(25, businessImpl.findTheGreatestFromAllData());
	}
	
	@Test
	void findTheGreatestFromAllData_OneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{35});
		assertEquals(35, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	void findTheGreatestFromAllData_EmptyArray() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
	}

}
```

## Step 05: Exploring Mocks further by Mocking List interface

`/src/test/java/com/in28minutes/mockito/mockitodemo/list/ListTest.java`

```java
package com.in28minutes.mockito.mockitodemo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ListTest {
	
	@Test
	void simpleTest() {
		List listMock = mock(List.class);
		//listMock.size() => 3
		when(listMock.size()).thenReturn(3);	
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	void multipleReturns() {
		List listMock = mock(List.class);
		//listMock.size() => 3
		when(listMock.size()).thenReturn(1).thenReturn(2);	
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}
	
	@Test
	void specificParameters() {
		List listMock = mock(List.class);
		//listMock.size() => 3
		when(listMock.get(0)).thenReturn("SomeString");	
		assertEquals("SomeString", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	void genericParameters() {
		List listMock = mock(List.class);
		//listMock.size() => 3
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");	
		assertEquals("SomeOtherString", listMock.get(0));
		assertEquals("SomeOtherString", listMock.get(1));
	}

}
```

---

# Mockito - Additions (Steps 06 to 12)

`mvn test` from `52-mockito` -> 31 tests, 0 failures, no warnings on the console.

## Existing vs new

**Existing - Steps 00 to 05**

- Handwritten stubs, and why they do not scale
- Creating mocks with `mock()`, `@Mock`, `@InjectMocks`, `@ExtendWith(MockitoExtension.class)`
- Stubbing with `when().thenReturn()`, and chained returns
- Argument matchers - exact values and `anyInt()` - and default return values

**New - Steps 06 to 12**

- Step 06 - Verification with `verify`, `times`, `never`, `atLeastOnce`, `verifyNoMoreInteractions`
- Step 07 - BDD style with `given().willReturn()`, `then().should()`, and AssertJ `assertThat`
- Step 08 - Argument capture with `ArgumentCaptor`, `@Captor`, `getValue()`, `getAllValues()`
- Step 09 - Spies - `spy()` vs `mock()`, stubbing a spy, `doReturn().when()`
- Step 10 - Strict stubs, `UnnecessaryStubbingException`, `lenient()`
- Step 11 - Static mocking with `mockStatic()` and `MockedStatic`, no PowerMock
- Step 12 - Spring bean mocking with `@MockitoBean`

No existing step was removed or reordered, and everything new sits at the end. Three existing files were touched, all in small ways - see below.

**Why these steps.** The module stopped at "make a mock return a value". Three gaps:

1. **No verification.** A `void` method could not be tested at all.
2. **Defaults have moved on.** `MockitoExtension` now defaults to strict stubs, and Mockito 5 makes the inline mock maker standard.
3. **Spring Boot 4 removed `@MockBean`.** Anyone following the old material gets a compile error.

Note for the recording: verification, captors, spies and BDD are not new Mockito - `ArgumentCaptor` and `BDDMockito` are `@since 1.8.0`. They were simply never covered here. Only strict stubs, `mockStatic()` and `@MockitoBean` reflect genuine changes in the tools.

---

## Changes to existing files

- **`DataService` gained `storeGreatest(int)`** so Steps 06 and 08 have a `void` call to verify and an argument to capture.
- **Both handwritten stubs had to implement it.** This is a gift to **Step 02**: that step claims stubs are a maintenance burden, and now it can show it. On screen: *"I added one method to the interface and every stub class went red. A mock needs no such change."*
- **`ListTest` uses `mock()` type inference** instead of raw `List`, so no more yellow warnings on screen, and is now package-private like every other test.

`/src/main/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImpl.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

public class SomeBusinessImpl {
	
	private final DataService dataService;
	
	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}
	
	public int findTheGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();
		int greatestValue = Integer.MIN_VALUE;
		for(int value:data) {
			if(value > greatestValue)
				greatestValue = value;
		}
		return greatestValue;
	}

	//Step 06 - a void call to verify, and an argument to capture
	public void storeTheGreatestFromAllData() {
		dataService.storeGreatest(findTheGreatestFromAllData());
	}

}

interface DataService {
	int[] retrieveAllData();

	void storeGreatest(int greatestValue);

}
```

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplStubTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImplStubTest {

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		DataService dataServiceStub = new DataServiceStub1();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, result);
	}

	@Test
	void findTheGreatestFromAllData_withOneValue() {
		DataService dataServiceStub = new DataServiceStub2();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(35, result);
	}

}

class DataServiceStub1 implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[]{25, 15, 5};
	}

	//DataService grew one method, so every stub has to implement it.
	@Override
	public void storeGreatest(int greatestValue) {
	}

}


class DataServiceStub2 implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[]{35};
	}

	@Override
	public void storeGreatest(int greatestValue) {
	}

}
```

`/src/test/java/com/in28minutes/mockito/mockitodemo/list/ListTest.java`

```java
package com.in28minutes.mockito.mockitodemo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	void simpleTest() {
		List<String> listMock = mock();
		//listMock.size() => 3
		when(listMock.size()).thenReturn(3);
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	void multipleReturns() {
		List<String> listMock = mock();
		//listMock.size() => 1, then 2 for every call after that
		when(listMock.size()).thenReturn(1).thenReturn(2);
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}
	
	@Test
	void specificParameters() {
		List<String> listMock = mock();
		when(listMock.get(0)).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	void genericParameters() {
		List<String> listMock = mock();
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");
		assertEquals("SomeOtherString", listMock.get(0));
		assertEquals("SomeOtherString", listMock.get(1));
	}

}
```

## Dependencies

**None added.** `spring-boot-starter-test` already brings Mockito, AssertJ and Hamcrest. `mockStatic()` needs no `mockito-inline` on Mockito 5.

**Two `pom.xml` plugins**, so the console is clean. Mockito used to self-attach its Java agent at runtime, which printed a notice plus five JVM warnings on every run. [JEP 451](https://openjdk.org/jeps/451) (JDK 21) asks libraries to load the agent at startup instead, so surefire passes `-javaagent`. The path comes from `maven-dependency-plugin` and is quoted, so it survives a local repository under a folder with a space in it.

`/pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.mockito</groupId>
	<artifactId>mockito-demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mockito-demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>25</java.version>
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

			<!-- Sets a property holding the path of every dependency jar.
			     Surefire below needs the path of mockito-core, and this resolves it
			     rather than guessing at the layout of the local repository. -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-dependency-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>properties</goal>
						</goals>
					</execution>
				</executions>
			</plugin>

			<!-- Keeps the test console clean on Java 21+.
			     Without this, every test run prints "Mockito is currently self-attaching..."
			     plus five JVM warning lines about dynamically loaded Java agents.
			     Loading Mockito as a -javaagent at startup is what JEP 451 asks libraries to do.
			     The path comes from the plugin above, so the agent is always the same
			     mockito-core that Spring Boot put on the test classpath.
			     -Xshare:off silences the CDS warning that loading the Java agent causes.
			     https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<configuration>
					<argLine>-javaagent:"${org.mockito:mockito-core:jar}" -Xshare:off</argLine>
				</configuration>
			</plugin>
		</plugins>
	</build>


</project>
```

---

# Step by step

## Step 06 - Verifying calls on Mocks

- Until now every test asked "what came back". This step asks "was it even called".
- Needed whenever the method returns `void` - there is no return value to assert on.
- Live demo: change `storeGreatest(25)` to `storeGreatest(15)` and read the failure. Mockito prints the argument it actually saw.

[Mockito javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplVerifyTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Stubbing controls what the mock returns.
//Verifying checks whether the mock was called, with what, and how often.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplVerifyTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_verifyTheCall() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.findTheGreatestFromAllData();

		verify(dataServiceMock).retrieveAllData();  //no count => exactly once
		verify(dataServiceMock, times(1)).retrieveAllData();
		verify(dataServiceMock, atLeastOnce()).retrieveAllData();

		//nothing was stored, so never() passes
		verify(dataServiceMock, never()).storeGreatest(anyInt());
	}

	@Test
	void storeTheGreatestFromAllData_verifyTheArgument() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock).storeGreatest(25);  //25 is the greatest of the three
		verify(dataServiceMock, never()).storeGreatest(15);
	}

	@Test
	void storeTheGreatestFromAllData_verifyNothingElseHappened() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock).retrieveAllData();
		verify(dataServiceMock).storeGreatest(25);
		verifyNoMoreInteractions(dataServiceMock);  //fails if we missed a call
	}

}
```

## Step 07 - BDD Style - given, willReturn, then, should

- Nothing new is tested. Only the vocabulary changes, and `//given //when //then` marks the three parts.
- `when` is overloaded in Mockito: it means "stub this" but reads as "the action". BDD removes the clash.
- AssertJ's `assertThat(result).isEqualTo(25)` comes in here. Already in the starter.
- The readme already asks you to add `org.mockito.BDDMockito` to Eclipse Favorites. This is the step that uses it.

[BDDMockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/BDDMockito.html) | [AssertJ](https://assertj.github.io/doc/)

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplBddTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.never;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//The same tests in given/when/then form.
//when-thenReturn becomes given-willReturn, and verify becomes then-should.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplBddTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		//given
		given(dataServiceMock.retrieveAllData()).willReturn(new int[]{25, 15, 5});

		//when
		int result = businessImpl.findTheGreatestFromAllData();

		//then
		assertThat(result).isEqualTo(25);
		then(dataServiceMock).should().retrieveAllData();
	}

	@Test
	void storeTheGreatestFromAllData_basicScenario() {
		//given
		given(dataServiceMock.retrieveAllData()).willReturn(new int[]{25, 15, 5});

		//when
		businessImpl.storeTheGreatestFromAllData();

		//then
		then(dataServiceMock).should().storeGreatest(25);
		then(dataServiceMock).should(never()).storeGreatest(15);
	}

}
```

## Step 08 - Capturing arguments with ArgumentCaptor

- `verify(mock).storeGreatest(25)` only works when you already know the value. A captor records what the code actually passed.
- Order matters: `capture()` goes **inside** `verify()`, and you read the value **after**.
- `getValue()` is the last call, `getAllValues()` is every call in order - that is the second test.
- `@Captor` saves writing `ArgumentCaptor.forClass(Integer.class)`.

[ArgumentCaptor](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/ArgumentCaptor.html)

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplCaptorTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//verify(mock).storeGreatest(25) only works when we already know the value.
//A captor records what the code actually passed, for arguments built inside the method.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplCaptorTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Captor
	private ArgumentCaptor<Integer> greatestValueCaptor;

	@Test
	void storeTheGreatestFromAllData_captureTheArgument() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock).storeGreatest(greatestValueCaptor.capture());
		assertThat(greatestValueCaptor.getValue()).isEqualTo(25);
	}

	@Test
	void storeTheGreatestFromAllData_captureMultipleCalls() {
		when(dataServiceMock.retrieveAllData())
				.thenReturn(new int[]{25, 15, 5})
				.thenReturn(new int[]{35});

		businessImpl.storeTheGreatestFromAllData();
		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock, times(2)).storeGreatest(greatestValueCaptor.capture());
		assertThat(greatestValueCaptor.getAllValues()).containsExactly(25, 35);
	}

}
```

## Step 09 - Introduction to Spy

- Same setup against a mock and a spy, back to back. `size()` is `0` on the mock and `1` on the spy. That contrast is the whole step.
- Third test is the surprise: you can stub a spy, and the stub wins over the real method. Everything unstubbed stays real.
- **Fourth test is the trap.** `when(spy.size())` has to call the real `size()` to reach the stubbing. Harmless on an `ArrayList`; not harmless on a method that throws or hits a database. `doReturn().when()` skips the real call.
- Caveat worth saying: a spy means testing real code you probably meant to isolate. Prefer a mock. `@Spy` is the annotation form.

[Mockito javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)

`/src/test/java/com/in28minutes/mockito/mockitodemo/list/SpyTest.java`

```java
package com.in28minutes.mockito.mockitodemo.list;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

//A mock does nothing unless you stub it.
//A spy wraps a real object: the real behavior runs, and you can still stub and verify it.
//Prefer a mock. A spy is for code you cannot redesign.
class SpyTest {

	@Test
	void mock_ignoresTheRealBehavior() {
		//mock() infers the type from the variable, so List.class is not needed
		List<String> listMock = mock();

		listMock.add("SomeString");

		assertThat(listMock.size()).isZero();     //the add() went nowhere
		assertThat(listMock.get(0)).isNull();
	}

	@Test
	void spy_keepsTheRealBehavior() {
		List<String> listSpy = spy(new ArrayList<String>());

		listSpy.add("SomeString");

		assertThat(listSpy.size()).isEqualTo(1);  //a real ArrayList did the work
		assertThat(listSpy.get(0)).isEqualTo("SomeString");
		verify(listSpy).add("SomeString");        //and it is still a Mockito mock
	}

	@Test
	void spy_canStillBeStubbed() {
		List<String> listSpy = spy(new ArrayList<String>());
		listSpy.add("SomeString");

		when(listSpy.size()).thenReturn(10);      //stubbing wins over real behavior

		//everything the stub does not cover stays real
		assertThat(listSpy.size()).isEqualTo(10);
		assertThat(listSpy.get(0)).isEqualTo("SomeString");
	}

	//Watch the line above: when(listSpy.size()) runs the real size() before it stubs anything.
	//On an ArrayList that is harmless. On a method that throws, or writes to a database, it is not.
	//doReturn().when() never calls the real method, so it is the safe form for a spy.
	@Test
	void spy_stubbedWithoutCallingTheRealMethod() {
		List<String> listSpy = spy(new ArrayList<String>());

		doReturn(10).when(listSpy).size();

		assertThat(listSpy.size()).isEqualTo(10);
	}

}
```

## Step 10 - Strict Stubs and UnnecessaryStubbingException

- **The live demo is the step.** Delete `lenient()`, run, get the red bar, put it back, green again.
- Then say why: an unused stub is setup that no longer matches the code, and strictness reports it instead of leaving it there.
- Point at the clickable line number in the failure - that is the part people miss.
- Mention the sibling error `PotentialStubbingProblem`: you stubbed `get(0)` but the code called `get(1)`. Lenient returns `null` quietly; strict fails.
- Worth two minutes, because it surprises anyone following older Mockito material.

[UnnecessaryStubbingException](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/exceptions/misusing/UnnecessaryStubbingException.html) | [Strictness](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/quality/Strictness.html)

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplStrictStubsTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//MockitoExtension defaults to strict stubs. Two failures it reports:
// - UnnecessaryStubbingException : you stubbed something the code never called
// - PotentialStubbingProblem     : the code called your stub with a different argument
//lenient() opts a single stub out.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplStrictStubsTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void stubThatIsUsed_isHappy() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		assertThat(businessImpl.findTheGreatestFromAllData()).isEqualTo(25);
	}

	//DEMO: delete lenient() below and run again -> UnnecessaryStubbingException
	@Test
	void stubThatIsNeverUsed_needsLenient() {
		lenient().when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		assertThat(businessImpl).isNotNull();  //we never call retrieveAllData()
	}

}
```

## Step 11 - Mocking static methods, without PowerMock

- Freezing the clock is the clearest real-world case. Date-dependent code was awkward to test before this.
- `try`-with-resources is not decoration. The static mock is live on the thread until closed, so leaking it breaks unrelated tests.
- The last line, outside the block, proves the scoping. Worth pointing out on screen.
- The headline: **`mockito-inline` and PowerMock are no longer needed.** Anyone who learned static mocking before Mockito 5 reaches for PowerMock out of habit.
- Same mechanism handles final classes and final methods.

[MockedStatic](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/MockedStatic.html)

`/src/test/java/com/in28minutes/mockito/mockitodemo/staticmock/MockStaticTest.java`

```java
package com.in28minutes.mockito.mockitodemo.staticmock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

//Static methods once required PowerMock. Mockito 5 makes the inline mock maker the default,
//so mockStatic() works with no extra dependency, as does mocking final classes.
//The mock is scoped to this thread and this try block, so it has to be closed.
class MockStaticTest {

	@Test
	void mockStatic_freezeTheClock() {
		//a real LocalDate, built before the static mock exists
		LocalDate fixedDate = LocalDate.of(2000, 1, 1);

		try (MockedStatic<LocalDate> mockedLocalDate = mockStatic(LocalDate.class)) {
			mockedLocalDate.when(LocalDate::now).thenReturn(fixedDate);

			assertThat(LocalDate.now()).isEqualTo(fixedDate);
			mockedLocalDate.verify(LocalDate::now);   //statics can be verified as well
		}

		//outside the try block the mock is closed, so the real behavior is back
		assertThat(LocalDate.now()).isNotEqualTo(fixedDate);
	}

}
```

## Step 12 - Mocking a Spring Bean with @MockitoBean

- Every step so far ran without Spring. This one starts a context, so it bridges to the Spring Boot testing material.
- `@Mock` vs `@MockitoBean` in one line: same Mockito mock, but `@MockitoBean` registers it in the Spring container so `@Autowired` beans receive it.
- **Flag the breaking change clearly.** `@MockBean` and `@SpyBean` are gone in Spring Boot 4, not just deprecated - deprecated in 3.4, removed in 4.
- Watch the import: `org.springframework.test.context.bean.override.mockito.MockitoBean`, it moved to `spring-test`.
- Not a 1-to-1 swap: `@MockitoBean` is replace-or-create, `@MockitoSpyBean` wraps an existing bean.
- The `@TestConfiguration` is only there because `SomeBusinessImpl` is a plain class here, not a `@Service`. Say so, so nobody copies it into a real project.

[@MockitoBean and @MockitoSpyBean](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-mockitobean.html) | [Boot 4 migration guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide)

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplMockitoBeanTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//@Mock creates a mock for a plain JUnit test. @MockitoBean puts one in the Spring context,
//so injected beans receive it. Spring Boot 4 removed @MockBean and @SpyBean - use
//@MockitoBean and @MockitoSpyBean, imported from spring-test.
@SpringBootTest
class SomeBusinessImplMockitoBeanTest {

	@MockitoBean
	private DataService dataServiceMock;

	@Autowired
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_withTheMockInsideTheSpringContext() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		assertThat(businessImpl.findTheGreatestFromAllData()).isEqualTo(25);
	}

	//SomeBusinessImpl is not a @Service in this module, so register it just for this test
	@TestConfiguration
	static class TestConfig {

		@Bean
		SomeBusinessImpl someBusinessImpl(DataService dataService) {
			return new SomeBusinessImpl(dataService);
		}

	}

}
```

---

## Not included, and why

- **Hamcrest matchers.** AssertJ ships in the same starter and reads better.
- **PowerMock.** No longer needed for static or final mocking.
- **`mockConstruction()`.** Same mechanism as Step 11, narrow use. Mention in passing.
- **`@MockitoSpyBean` demo.** It wraps an existing bean, and this module has no real bean worth wrapping - `DataService` has no implementation outside the tests. Named in Step 12 without a demo.

---

# New Addition - Advanced Mockito Features

1. `thenAnswer()`: Mockito also supports dynamic answers where the return value depends on the invocation.
2. `doAnswer()`: For void methods
3. `thenThrow()`: Returning values but not throwing exceptions.
4. `doThrow()`: For void methods
5. `InOrder`: You verify that something happened. Mockito can also verify the order.
6. `verifyNoInteractions()`: The mock must-have never been called.
7. `verifyNoMoreInteractions()`: All interactions must already have been verified; nothing extra is allowed.

Reference: https://site.mockito.org

`/src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplNewMockitoTest.java`

```java
package com.in28minutes.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplNewMockitoTest {

    @Mock
    private DataService dataService;

    @InjectMocks
    private SomeBusinessImpl business;

    // thenAnswer() feature
    @Test
    void findTheGreatest_dynamicAnswer() {
        var counter = new AtomicInteger();
        when(dataService.retrieveAllData())
                .thenAnswer(_ -> {
                    if(counter.getAndIncrement() == 0)
                        return new int[] {10, 20};
                    return new int[] {100, 200};
                });

        assertEquals(20, business.findTheGreatestFromAllData());
        assertEquals(200, business.findTheGreatestFromAllData());
    }

    // doAnswer feature
    @Test
    void storeTheGreatest_doAnswer() {
        when(dataService.retrieveAllData()).thenReturn(new int[] {15, 40, 25});
        doAnswer(invocation -> {
            Integer value = invocation.getArgument(0);
            assertEquals(40, value);
            return null;
        }).when(dataService).storeGreatest(anyInt());

        business.storeTheGreatestFromAllData();
        verify(dataService).storeGreatest(40);
    }

    // thenThrow() → methods with return values
    @Test
    void findTheGreatestFromAllData_whenDataServiceFails() {

        when(dataService.retrieveAllData())
                .thenThrow(new RuntimeException("Database unavailable"));

        assertThrows(RuntimeException.class,
                () -> business.findTheGreatestFromAllData());
    }

    // doThrow() → void methods
    @Test
    void storeTheGreatestFromAllData_whenStoreFails() {

        when(dataService.retrieveAllData())
                .thenReturn(new int[] {10, 20, 30});

        doThrow(new RuntimeException("Unable to store"))
                .when(dataService)
                .storeGreatest(anyInt());

        assertThrows(RuntimeException.class,
                () -> business.storeTheGreatestFromAllData());
    }

    // Verifying Order with <code>InOrder</code>
    @Test
    void verifyMethodOrder() {

        when(dataService.retrieveAllData())
                .thenReturn(new int[] {10, 20, 30});

        business.storeTheGreatestFromAllData();

        InOrder inOrder = inOrder(dataService);

        inOrder.verify(dataService).retrieveAllData();
        inOrder.verify(dataService).storeGreatest(30);
    }

    // verifyNoInteractions()
    @Test
    void onlyFindMethodShouldNotStoreAnything() {

        when(dataService.retrieveAllData())
                .thenReturn(new int[] {10, 20, 30});

        business.findTheGreatestFromAllData();

        verify(dataService).retrieveAllData();
        verify(dataService, never()).storeGreatest(anyInt());
    }
}
```

---

## References

Verified against Spring Boot 4.1.0, Mockito 5.23.0 and JUnit Jupiter 6.0.3, compiled for Java 25 and run on JDK 25.

- [Mockito javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html) - the main Mockito documentation
- [BDDMockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/BDDMockito.html) - Step 07
- [ArgumentCaptor](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/ArgumentCaptor.html) - Step 08
- [UnnecessaryStubbingException](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/exceptions/misusing/UnnecessaryStubbingException.html) - Step 10
- [MockedStatic](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/MockedStatic.html) - Step 11
- [@MockitoBean and @MockitoSpyBean](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-mockitobean.html) - Step 12
- [Strictness](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/quality/Strictness.html) - Step 10
- [Spring Boot 4.0 migration guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide) - why `@MockBean` and `@SpyBean` are gone
- [Spring Boot testing reference](https://docs.spring.io/spring-boot/reference/testing/index.html)
- [JUnit 5 user guide](https://junit.org/junit5/docs/current/user-guide/)
- [AssertJ documentation](https://assertj.github.io/doc/)
- [JEP 451](https://openjdk.org/jeps/451) - why the pom passes Mockito to the JVM as a `-javaagent`
