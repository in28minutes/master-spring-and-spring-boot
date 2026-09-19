# First Steps in Mockito

Mockito is the most famous mocking framework in Java.

## Installing Tools

### Our Recommendations

- Use **latest version** of Java
- Use **latest version** of "Eclipse IDE for Enterprise Java Developers"
- Remember: Spring Boot 3+ works only with Java 17+

### Installing Java

- Windows - https://www.youtube.com/watch?v=I0SBRWVS0ok
- Linux - https://www.youtube.com/watch?v=mHvFpyHK97A
- Mac - https://www.youtube.com/watch?v=U3kTdMPlgsY

#### Troubleshooting

- Troubleshooting Java Installation - https://www.youtube.com/watch?v=UI_PabQ1YB0

### Installing Eclipse

- Windows - https://www.youtube.com/watch?v=toY06tsME-M
- Others - https://www.youtube.com/watch?v=XveQ9Gq41UM

#### Troubleshooting
- Configuring Java in Eclipse - https://www.youtube.com/watch?v=8i0r_fcE3L0

## Easier Static Imports
- Window > Preferences > Java > Editor > Content Assist > Favorites
- org.junit.jupiter.api.Assertions
- org.mockito.Mockito
- org.mockito.BDDMockito
- org.mockito.ArgumentMatchers
- org.assertj.core.api.Assertions
- More information
- Visit Mockito Official Documentation - [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)

## Step by Step Details

- Step 00 - Introduction to Section - Mockito in 12 Steps
- Step 01 - Setting up a Spring Boot Project
- Step 02 - Understanding problems with Stubs
- Step 03 - Writing your first Mockito test with Mocks
- Step 04 - Simplifying Tests with Mockito Annotations - @Mock, @InjectMocks
- Step 05 - Exploring Mocks further by Mocking List interface
- Step 06 - Verifying calls on Mocks - verify, times, never, verifyNoMoreInteractions
- Step 07 - BDD Style - given, willReturn, then, should
- Step 08 - Capturing arguments with ArgumentCaptor
- Step 09 - Introduction to Spy - spy vs mock
- Step 10 - Strict Stubs and UnnecessaryStubbingException
- Step 11 - Mocking static methods, without PowerMock
- Step 12 - Mocking a Spring Bean with @MockitoBean

Notes for Steps 06 to 12: [99-hafeez-module-additions.md](99-hafeez-module-additions.md)
<!---
Current Directory : /Users/rangakaranam/Ranga/git/00.courses/spring-boot-master-class/04.Mockito-Introduction-In-5-Steps-V2
-->

## Complete Code Example


### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.1</version>
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

### /src/main/java/com/in28minutes/mockito/mockitodemo/MockitoDemoApplication.java

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
---

### /src/main/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImpl.java

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
---

### /src/main/resources/application.properties

```properties

```
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/MockitoDemoApplicationTests.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplMockTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplStubTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/list/ListTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplVerifyTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplBddTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplCaptorTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/list/SpyTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplStrictStubsTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/staticmock/MockStaticTest.java

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
---

### /src/test/java/com/in28minutes/mockito/mockitodemo/business/SomeBusinessImplMockitoBeanTest.java

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

Notes for each step: [99-hafeez-module-additions.md](99-hafeez-module-additions.md).

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
