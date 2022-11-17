# First 5 Steps in Mockito

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
- org.junit.Assert
- org.mockito.BDDMockito
- org.mockito.Mockito
- org.hamcrest.Matchers
- org.hamcrest.CoreMatchers
- More information 
- Visit Mockito Official Documentation - [Mockito Documentation] (http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html)

## Step by Step Details

- Step 00 - Introduction to Section - Mockito in 5 Steps
- Step 01 - Setting up a Spring Boot Project
- Step 02 - Understanding problems with Stubs
- Step 03 - Writing your first Mockito test with Mocks
- Step 04 - Simplifying Tests with Mockito Annotations - @Mock, @InjectMocks
- Step 05 - Exploring Mocks further by Mocking List interface
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
		<version>3.0.0-M3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.mockito</groupId>
	<artifactId>mockito-demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mockito-demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
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
	
	private DataService dataService;
	
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
	
}


class DataServiceStub2 implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[]{35};
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
