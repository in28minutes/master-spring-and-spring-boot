## Slides

- Large applications can 1000s of Code files and millions of lines of code
- Testing is done to check if the behavior of the application is as expected
	- Option 1: Deploy the complete application and test
		- This is called System Testing or Integration Testing
	- Option 2: Test specific units of application code
		- Examples: A specific method or group of methods
	- This is called Unit Testing
- Advantages of Unit Testing
	- Finds bug early
		- Can be run under Continuous Integration
	- Easy to fix bugs
	- Reduces costs in the long run

## Notes

```
Absence of failure is success

Create a Java Project in Eclipse without using Modules
Add sum method
Create a new Test source folder
Use Eclipse Menu to create New JUnit 5 Test Case, it would automatically ask to add JUnit 5 to class path.

assertEquals(1,2)//Lot of types
assertTrue
assertFalse
assertNotNull
assertNull

@BeforeAll
@BeforeEach
@AfterAll
@AfterEach
```