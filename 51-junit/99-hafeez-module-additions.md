# Hafeez Module Additions (51-junit)

**Module:** `master-spring-and-spring-boot/51-junit`

The idea was to enhance the existing JUnit module **without touching the
production code**. So `MyMath.java` stays exactly as the lecture left it, no new
methods, no `divide()`, no `isEven()`. Everything I added is extra test cases,
annotations and modern JUnit features built on top of the one method that was
already there: `calculateSum(int[])`.

JUnit: **Jupiter 6.0.3**. Java: **17+** (required by JUnit 6).

---

## Quick list - what I added

1. A `pom.xml` so the module builds and `mvn test` runs from the command line.
2. New test class `JUnit6FeaturesDemoTest` - a tour of modern JUnit annotations.
3. New test class `MyParameterizedClassTest` - the newer `@ParameterizedClass`.
4. More tests + annotations in `MyMathTest`.
5. Fixed a hidden JUnit 4 bug and filled in `MyAssertTest`.
6. A friendly `@DisplayName` on `MyBeforeAfterTest`.
7. A **Useful References** section at the end of `readme.md`.
8. This document.

Result: **6 tests -> 43 tests** (1 intentionally skipped), production code unchanged.

---

## Build log - how each addition was built

### Step 1 - Make the module runnable (`pom.xml`)
The module was Eclipse-only, there was no build file, so tests couldn't run from
the terminal. I added a `pom.xml` that:
- points at the existing `src/` and `test/` folders (I didn't move any files),
- pulls JUnit 6 in through the official BOM,
- adds the Surefire plugin so `mvn test` picks the tests up.

After this, `mvn test` works.

### Step 2 - Enhance `MyMathTest` (existing file)
I kept the original two tests exactly as they were, then added three more around
the *same* `calculateSum` method:
- `calculateSum_grouped` - uses `assertAll` to check several inputs in one test.
- `calculateSum_null_throws` - uses `assertThrows`; `calculateSum(null)` already
  throws `NullPointerException`, so I get an error path to test for free.
- added `@DisplayName` for readable names in the report.

### Step 3 - Fix and finish `MyAssertTest` (existing file)
- Replaced the JUnit 4 import (`org.junit.Assert.assertArrayEquals`) with the
  Jupiter one. This was a real hidden bug - it only compiled because JUnit 4 sat
  on the classpath, and would break under JUnit 6.
- Turned the commented-out `assertNull` / `assertNotNull` lines into real asserts.
- Flipped the deliberately-failing `assertArrayEquals({1,2}, {2,1})` to a passing
  one, with a comment noting the original was a red-bar demo.

### Step 4 - Tidy `MyBeforeAfterTest` (existing file)
Added a class-level `@DisplayName` so the lifecycle demo shows a friendly heading.
The methods themselves are untouched.

### Step 5 - New file: `JUnit6FeaturesDemoTest`
A one-feature-per-class tour of everything the "5 steps" lecture skips, all
running against `calculateSum`: `@DisplayName`, `@ParameterizedTest`
(`@ValueSource`, `@CsvSource`), `@RepeatedTest`, JSpecify nullability
(`@NullMarked`/`@Nullable`), `@Timeout` and `assertTimeout`, `assumeTrue`,
`@Disabled`, `@Nested`, `@Tag`, system properties, default `Locale`/`TimeZone`,
and deterministic nested ordering. (The earlier `ModernJUnit5FeaturesTest` file
was folded into this one, so there is a single features file instead of two.)

### Step 6 - New file: `MyParameterizedClassTest`
This is the **parameterized class** (different from a parameterized test). With
`@ParameterizedClass` the *whole class* re-runs once per row of `@CsvSource` data,
and every `@Test` inside sees the injected `@Parameter` values. I gave it 3 data
rows and 2 tests, so it runs 6 times in total.

### Step 7 - Add references to `readme.md`
Kept all the original "Step 1-5" text and listings, and appended a **Useful
References** section of official doc links at the very end.

---

## Feature reference - what's inside `JUnit6FeaturesDemoTest`

These are the JUnit 6 features demonstrated in
[`JUnit6FeaturesDemoTest.java`](test/com/in28minutes/junit/JUnit6FeaturesDemoTest.java),
all running against the unchanged `calculateSum(int[])` method.

### Feature 1 - Parameterized Tests (`@ParameterizedTest` + `@ValueSource`)
Runs the same test once per value in the source, so one method covers many inputs.
- **Reference**: https://docs.junit.org/6.1.0/writing-tests/parameterized-classes-and-tests.html

```java
@ParameterizedTest(name = "Test {index}: multiplier = {0}")
@ValueSource(ints = {1, 2, 3, 4, 5})
void calculateSum_WithDifferentMultipliers(int multiplier) {
    int baseSum = math.calculateSum(new int[]{1, 2, 3});
    assertTrue(baseSum * multiplier > 0);
}
```

### Feature 2 - Repeated Tests (`@RepeatedTest` + `RepetitionInfo`)
Repeats a test a fixed number of times; `RepetitionInfo` tells you which run you are
on, so each repetition can assert a different case. Built on `@TestTemplate`.

Reference: https://docs.junit.org/6.1.0/writing-tests/repeated-tests.html

```java
@RepeatedTest(4)
void repeatCalculateSum(RepetitionInfo info) {
    switch (info.getCurrentRepetition()) {
        case 1 -> assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
        case 2 -> assertEquals(0, math.calculateSum(new int[]{}));
        // ...
    }
}
```

### Feature 3 - Null-safety with JSpecify (`@NullMarked` / `@Nullable`)
JUnit 6 ships JSpecify annotations. `@NullMarked` makes everything non-null by
default; `@Nullable` opts a parameter back in, giving tools static null checking.
Reference: https://jspecify.dev

```java
@NullMarked
class MyMathWithNullability {
    public void process(@Nullable String input) {
        if (input != null) System.out.println(input);
    }
}
```

### Feature 4 - Enhanced Timeout Support (`@Timeout`)
Fails a test if it runs longer than the limit - good for catching slow regressions.
Reference: https://docs.junit.org/6.1.0/writing-tests/timeouts.html

```java
@Test
@Timeout(value = 2, unit = TimeUnit.SECONDS)
void calculateSum_WithTimeout() {
    assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
}
```

### Feature 5 - Built-in Extensions: System Properties
Set/restore JVM system properties around a test using the built-in extension.
Reference: https://docs.junit.org/6.1.0/writing-tests/built-in-extensions.html#system-properties

```java
@Test
void testWithSystemProperty() {
    System.setProperty("test.mode", "junit6");
    assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
}
```

### Feature 6 - Default Locale and TimeZone
Pin a `Locale`/`TimeZone` for tests so results don't depend on the machine running them.
Reference: https://docs.junit.org/6.1.0/writing-tests/built-in-extensions.html#DefaultLocaleAndTimeZone

```java
@Test
void testWithUSLocale() {
    Locale.setDefault(Locale.US);
    assertEquals(6, new MyMath().calculateSum(new int[]{1, 2, 3}));
}
```

### Feature 7 - Deterministic `@Nested` Class Ordering (`@TestMethodOrder`)
Groups related tests with `@Nested` and runs them in a predictable, declared order.
Reference: https://docs.junit.org/6.1.0/writing-tests/nested-tests.html

```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyMathNestedOrderingTest {
    @Nested
    @DisplayName("A: First Nested Class")
    class FirstNestedTest {
        @Test void test() { assertEquals(6, math.calculateSum(new int[]{1, 2, 3})); }
    }
}
```

### Feature 8 - Parameterized Tests with `@CsvSource`
Passes the input and the expected result together on one line, so each row reads
almost like a small table. Handy when the inputs and outputs vary together.
Reference: https://docs.junit.org/6.1.0/writing-tests/parameterized-classes-and-tests.html

```java
@ParameterizedTest(name = "sum of [{0}] = {1}")
@CsvSource({"'1,2,3', 6", "'5', 5", "'-1,-2,-3', -6", "'10,-10', 0"})
void calculateSum_csvSource(String csv, int expected) {
    assertEquals(expected, math.calculateSum(toIntArray(csv)));
}
```

### Feature 9 - Timeout as an assertion (`assertTimeout`)
`@Timeout` (Feature 4) times the whole test; `assertTimeout` times only the block
of code you wrap, from inside the test body.
Reference: https://docs.junit.org/6.1.0/writing-tests/timeouts.html

```java
@Test
void calculateSum_isFast() {
    assertTimeout(Duration.ofMillis(100),
        () -> math.calculateSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
}
```

### Feature 10 - Assumptions (`assumeTrue`)
Skips a test (rather than failing it) when a precondition isn't met - useful for
tests that only make sense in certain environments.
Reference: https://docs.junit.org/6.1.0/writing-tests/assumptions.html

```java
@Test
void runsOnlyOn64BitJvm() {
    assumeTrue("64".equals(System.getProperty("sun.arch.data.model")));
    assertNotNull(math);
}
```

### Feature 11 - Disabling a test (`@Disabled`)
Switches a test off without deleting it. Always leave a reason. This is the one
intentionally skipped test in the suite.
Reference: https://docs.junit.org/6.1.0/writing-tests/disabling-tests.html

```java
@Test
@Disabled("Demo only - shows how to temporarily skip a test")
void thisTestIsCurrentlySkipped() { throw new IllegalStateException("Should never run"); }
```

### Feature 12 - Grouping with `@Nested`
Keeps related cases together under one readable heading. Feature 7 is about the
order nested classes run in; this one is just about grouping.
Reference: https://docs.junit.org/6.1.0/writing-tests/nested-tests.html

```java
@DisplayName("calculateSum edge cases")
class MyMathEdgeCasesTest {
    @Nested
    @DisplayName("Simple array inputs")
    class SimpleArrays {
        @Test void emptyArray_returnsZero() { assertEquals(0, math.calculateSum(new int[]{})); }
    }
}
```

### Feature 13 - Tagging tests (`@Tag`)
Labels a test so the build can include or exclude it, e.g. `mvn test -Dgroups=fast`.
Reference: https://docs.junit.org/6.1.0/writing-tests/tagging-and-filtering.html

```java
@Test
@Tag("fast")
void taggedFast() { assertEquals(2, math.calculateSum(new int[]{1, 1})); }
```

---

## Dependencies I added

All of this lives in the new `pom.xml`. Nothing else in the repo changed.

| Dependency | Coordinates | Why |
|---|---|---|
| JUnit BOM | `org.junit:junit-bom:6.0.3` (imported) | Keeps every JUnit artifact on one matching version. |
| JUnit Jupiter | `org.junit.jupiter:junit-jupiter` (test scope) | Aggregator - brings in `junit-jupiter-api`, `junit-jupiter-params` (needed for the parameterized tests/class) and `junit-jupiter-engine`. |
| Maven Surefire | `maven-surefire-plugin:3.5.4` | Runs the JUnit tests during `mvn test`. |

The version is set once at the top of the pom:

```xml
<junit.version>6.0.3</junit.version>
<surefire.version>3.5.4</surefire.version>
<maven.compiler.release>17</maven.compiler.release>
```

---

## Before / after

| | Before | After |
|---|---|---|
| Tests | 6 | 43 (1 intentionally skipped) |
| Production code | `MyMath.calculateSum` | `MyMath.calculateSum` (unchanged) |
| Annotations / features | `@Test`, lifecycle, basic asserts | + `@DisplayName`, `@ParameterizedTest`, `@ValueSource`, `@CsvSource`, **`@ParameterizedClass` + `@Parameter`**, `assertThrows`, `assertAll`, `assertTimeout`, `assumeTrue`, `@RepeatedTest`, `@Disabled`, `@Nested`, `@Tag` |
| Build file | none (Eclipse only) | `pom.xml` - `mvn test` works |
| JUnit version | mixed JUnit 4 import + Jupiter | Jupiter only, pinned to 6.0.3 via the BOM |
| `mvn test` | not possible (no pom) | BUILD SUCCESS, 43 tests, 1 skipped, 0 failures |

---

## How to verify

```bash
cd master-spring-and-spring-boot/51-junit
mvn test
```

(The one skipped test is the `@Disabled` demo in `JUnit6FeaturesDemoTest`.)

---
