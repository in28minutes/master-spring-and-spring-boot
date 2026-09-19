# 52-mockito - Notes for Steps 06 to 12

Notes only. All code is in [readme.md](readme.md) under **Complete Code Example**.

`mvn test` from `52-mockito` -> 25 tests, 0 failures, no warnings on the console.

---

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

## Dependencies

**None added.** `spring-boot-starter-test` already brings Mockito, AssertJ and Hamcrest. `mockStatic()` needs no `mockito-inline` on Mockito 5.

**Two `pom.xml` plugins**, so the console is clean. Mockito used to self-attach its Java agent at runtime, which printed a notice plus five JVM warnings on every run. [JEP 451](https://openjdk.org/jeps/451) (JDK 21) asks libraries to load the agent at startup instead, so surefire passes `-javaagent`. The path comes from `maven-dependency-plugin` and is quoted, so it survives a local repository under a folder with a space in it.

---

# Step by step

## Step 06 - Verifying calls on Mocks

- Until now every test asked "what came back". This step asks "was it even called".
- Needed whenever the method returns `void` - there is no return value to assert on.
- Live demo: change `storeGreatest(25)` to `storeGreatest(15)` and read the failure. Mockito prints the argument it actually saw.

[Mockito javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)

## Step 07 - BDD Style - given, willReturn, then, should

- Nothing new is tested. Only the vocabulary changes, and `//given //when //then` marks the three parts.
- `when` is overloaded in Mockito: it means "stub this" but reads as "the action". BDD removes the clash.
- AssertJ's `assertThat(result).isEqualTo(25)` comes in here. Already in the starter.
- The readme already asks you to add `org.mockito.BDDMockito` to Eclipse Favorites. This is the step that uses it.

[BDDMockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/BDDMockito.html) | [AssertJ](https://assertj.github.io/doc/)

## Step 08 - Capturing arguments with ArgumentCaptor

- `verify(mock).storeGreatest(25)` only works when you already know the value. A captor records what the code actually passed.
- Order matters: `capture()` goes **inside** `verify()`, and you read the value **after**.
- `getValue()` is the last call, `getAllValues()` is every call in order - that is the second test.
- `@Captor` saves writing `ArgumentCaptor.forClass(Integer.class)`.

[ArgumentCaptor](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/ArgumentCaptor.html)

## Step 09 - Introduction to Spy

- Same setup against a mock and a spy, back to back. `size()` is `0` on the mock and `1` on the spy. That contrast is the whole step.
- Third test is the surprise: you can stub a spy, and the stub wins over the real method. Everything unstubbed stays real.
- **Fourth test is the trap.** `when(spy.size())` has to call the real `size()` to reach the stubbing. Harmless on an `ArrayList`; not harmless on a method that throws or hits a database. `doReturn().when()` skips the real call.
- Caveat worth saying: a spy means testing real code you probably meant to isolate. Prefer a mock. `@Spy` is the annotation form.

[Mockito javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)

## Step 10 - Strict Stubs and UnnecessaryStubbingException

- **The live demo is the step.** Delete `lenient()`, run, get the red bar, put it back, green again.
- Then say why: an unused stub is setup that no longer matches the code, and strictness reports it instead of leaving it there.
- Point at the clickable line number in the failure - that is the part people miss.
- Mention the sibling error `PotentialStubbingProblem`: you stubbed `get(0)` but the code called `get(1)`. Lenient returns `null` quietly; strict fails.
- Worth two minutes, because it surprises anyone following older Mockito material.

[UnnecessaryStubbingException](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/exceptions/misusing/UnnecessaryStubbingException.html) | [Strictness](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/quality/Strictness.html)

## Step 11 - Mocking static methods, without PowerMock

- Freezing the clock is the clearest real-world case. Date-dependent code was awkward to test before this.
- `try`-with-resources is not decoration. The static mock is live on the thread until closed, so leaking it breaks unrelated tests.
- The last line, outside the block, proves the scoping. Worth pointing out on screen.
- The headline: **`mockito-inline` and PowerMock are no longer needed.** Anyone who learned static mocking before Mockito 5 reaches for PowerMock out of habit.
- Same mechanism handles final classes and final methods.

[MockedStatic](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/MockedStatic.html)

## Step 12 - Mocking a Spring Bean with @MockitoBean

- Every step so far ran without Spring. This one starts a context, so it bridges to the Spring Boot testing material.
- `@Mock` vs `@MockitoBean` in one line: same Mockito mock, but `@MockitoBean` registers it in the Spring container so `@Autowired` beans receive it.
- **Flag the breaking change clearly.** `@MockBean` and `@SpyBean` are gone in Spring Boot 4, not just deprecated - deprecated in 3.4, removed in 4.
- Watch the import: `org.springframework.test.context.bean.override.mockito.MockitoBean`, it moved to `spring-test`.
- Not a 1-to-1 swap: `@MockitoBean` is replace-or-create, `@MockitoSpyBean` wraps an existing bean.
- The `@TestConfiguration` is only there because `SomeBusinessImpl` is a plain class here, not a `@Service`. Say so, so nobody copies it into a real project.

[@MockitoBean and @MockitoSpyBean](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-mockitobean.html) | [Boot 4 migration guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide)

---

## Not included, and why

- **Hamcrest matchers.** AssertJ ships in the same starter and reads better.
- **PowerMock.** No longer needed for static or final mocking.
- **`mockConstruction()`.** Same mechanism as Step 11, narrow use. Mention in passing.
- **`@MockitoSpyBean` demo.** It wraps an existing bean, and this module has no real bean worth wrapping - `DataService` has no implementation outside the tests. Named in Step 12 without a demo.

## New Features

1. `thenAnswer()`: Mockito also supports dynamic answers where the return value depends on the invocation.
2. `doAnswer()`: For void methods
3. `thenThrow()`: Returning values but not throwing exceptions.
4. `doThrow()`: For void methods
5. `InOrder`: You verify that something happened. Mockito can also verify the order.
6. `verifyNoInteractions()`: The mock must-have never been called.
7. `verifyNoMoreInteractions()`: All interactions must already have been verified; nothing extra is allowed.

Reference: https://site.mockito.org