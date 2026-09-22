package com.in28minutes.junit;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

// Reference: https://docs.junit.org/6.1.0/writing-tests/parameterized-classes-and-tests.html
@DisplayName("MyMath Tests with Parameterized Methods")
class MyMathParameterizedTest {

    private final MyMath math = new MyMath();

    @ParameterizedTest(name = "Test {index}: multiplier = {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Calculate sum with different multipliers")
    void calculateSum_WithDifferentMultipliers(int multiplier) {
        int baseSum = math.calculateSum(new int[]{1, 2, 3});
        int result = baseSum * multiplier;
        assertTrue(result > 0);
        System.out.println("Multiplier: " + multiplier + ", Result: " + result);
    }
}

// @RepeatedTest(for simple repetition without data variation) internally uses @TestTemplate
// Reference: https://docs.junit.org/6.1.0/writing-tests/repeated-tests.html
// https://docs.junit.org/6.1.0/api/org.junit.jupiter.api/org/junit/jupiter/api/TestTemplate.html
@DisplayName("MyMath Repeated Tests (Simpler Alternative)")
class MyMathRepeatedTemplateTest {

    private MyMath math = new MyMath();

    @RepeatedTest(4)
    @DisplayName("Repeat sum calculations")
    void repeatCalculateSum(RepetitionInfo info) {
        switch (info.getCurrentRepetition()) {
            case 1:
                assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
                break;
            case 2:
                assertEquals(0, math.calculateSum(new int[]{}));
                break;
            case 3:
                assertEquals(30, math.calculateSum(new int[]{5, 10, 15}));
                break;
            case 4:
                assertEquals(7, math.calculateSum(new int[]{7}));
                break;
        }
    }
}

// Reference: https://jspecify.dev
// https://jspecify.dev/docs/api/org/jspecify/annotations/package-summary.html
@NullMarked
class MyMathWithNullability {
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    public void process(@Nullable String input) {
        if (input != null) {
            System.out.println(input);
        }
    }
}

class MyMathNullabilityTest {
    private final MyMathWithNullability math = new MyMathWithNullability();

    @Test
    void testNullableParameter() {
        math.process(null);
    }

    @Test
    void testNonNullRequired() {
        assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
    }
}


// Enhanced Timeout Support
// (passed)
// Reference: https://docs.junit.org/6.1.0/writing-tests/timeouts.html
class MyMathTimeoutTest {
    private final MyMath math = new MyMath();

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("Test must complete within 2 seconds")
    void calculateSum_WithTimeout() {
        assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Quick execution test")
    void calculateSum_FastExecution() {
        assertEquals(0, math.calculateSum(new int[]{}));
    }
}

// ============================================================================================================================================================================================================================

// Built-in Extensions - System Properties
// (passed)
// Reference: https://docs.junit.org/6.1.0/writing-tests/built-in-extensions.html#system-properties
class MyMathWithSystemPropertiesTest {
    private final MyMath math = new MyMath();

    @Test
    void testWithSystemProperty() {
        System.setProperty("test.mode", "junit6");
        assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
    }
}

// ============================================================================================================================================================================================================================

// Default Locale and TimeZone
// (passed)
// Reference: https://docs.junit.org/6.1.0/writing-tests/built-in-extensions.html#DefaultLocaleAndTimeZone
@DisplayName("Tests with specific Locale and TimeZone")
class MyMathLocalizationTest {

    @Test
    void testWithUSLocale() {
        Locale.setDefault(Locale.US);
        assertEquals(6, new MyMath().calculateSum(new int[]{1, 2, 3}));
    }

    @Test
    void testWithIndianTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        assertEquals(6, new MyMath().calculateSum(new int[]{1, 2, 3}));
    }
}

// ============================================================================================================================================================================================================================

// Deterministic @Nested Class Ordering
// (passed)
// Reference: https://docs.junit.org/6.1.0/writing-tests/nested-tests.html

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("MyMath Tests with Nested Class Ordering")
class MyMathNestedOrderingTest {
    private MyMath math = new MyMath();

    @Nested
    @DisplayName("A: First Nested Class (alphabetically)")
    class FirstNestedTest {
        @Test
        void test() {
            assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
        }
    }

    @Nested
    @DisplayName("B: Second Nested Class (alphabetically)")
    class SecondNestedTest {
        @Test
        void test() {
            assertEquals(0, math.calculateSum(new int[]{}));
        }
    }
}

// ============================================================================================================================================================================================================================

// Parameterized Tests with @CsvSource
// (passed)
// Pass the input and the expected result together on one line, so each row reads
// almost like a little table.
// Reference: https://docs.junit.org/6.1.0/writing-tests/parameterized-classes-and-tests.html
@DisplayName("MyMath Tests with CSV Source")
class MyMathCsvSourceTest {

    private final MyMath math = new MyMath();

    @ParameterizedTest(name = "sum of [{0}] = {1}")
    @CsvSource({
        "'1,2,3',     6",
        "'5',         5",
        "'-1,-2,-3', -6",
        "'10,-10',    0"
    })
    void calculateSum_csvSource(String csv, int expected) {
        int[] numbers = toIntArray(csv);
        assertEquals(expected, math.calculateSum(numbers));
    }

    // "1,2,3" -> {1,2,3}
    private int[] toIntArray(String csv) {
        String[] parts = csv.split(",");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i].trim());
        }
        return numbers;
    }
}

// ============================================================================================================================================================================================================================

// Timeout as an assertion (assertTimeout)
// (passed)
// @Timeout (above) puts the limit on the whole test; assertTimeout wraps only the
// piece of code you want to time, inside the test body.
// Reference: https://docs.junit.org/6.1.0/writing-tests/timeouts.html
class MyMathAssertTimeoutTest {
    private final MyMath math = new MyMath();

    @Test
    void calculateSum_isFast() {
        assertTimeout(Duration.ofMillis(100),
            () -> math.calculateSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}

// ============================================================================================================================================================================================================================

// Assumptions (assumeTrue)
// (passed)
// assumeTrue skips the test (rather than failing it) when a precondition isn't
// met - useful for tests that only make sense in some environments, e.g. only on
// a 64-bit JVM.
// Reference: https://docs.junit.org/6.1.0/writing-tests/assumptions.html
class MyMathAssumptionsTest {
    private final MyMath math = new MyMath();

    @Test
    void runsOnlyOn64BitJvm() {
        assumeTrue("64".equals(System.getProperty("sun.arch.data.model")),
            "Skipping - this test only makes sense on a 64-bit JVM");
        assertNotNull(math);
    }
}

// ============================================================================================================================================================================================================================

// Disabling a test (@Disabled)
// (skipped on purpose)
// Switch a test off without deleting it. Always leave a reason so the next person
// knows why it's parked.
// Reference: https://docs.junit.org/6.1.0/writing-tests/disabling-tests.html
class MyMathDisabledTest {

    @Test
    @Disabled("Demo only - shows how to temporarily skip a test")
    void thisTestIsCurrentlySkipped() {
        throw new IllegalStateException("Should never run");
    }
}

// ============================================================================================================================================================================================================================

// Grouping with @Nested
// (passed)
// Keep related cases together under one readable heading. This is about grouping;
// the nested-ordering demo above is about the order they run in.
// Reference: https://docs.junit.org/6.1.0/writing-tests/nested-tests.html
@DisplayName("calculateSum edge cases")
class MyMathEdgeCasesTest {
    private final MyMath math = new MyMath();

    @Nested
    @DisplayName("Simple array inputs")
    class SimpleArrays {

        @Test
        void emptyArray_returnsZero() {
            assertEquals(0, math.calculateSum(new int[]{}));
        }

        @Test
        void singleElement_returnsThatElement() {
            assertEquals(42, math.calculateSum(new int[]{42}));
        }

        @Test
        void negativeNumbers_areSummedCorrectly() {
            assertEquals(-6, math.calculateSum(new int[]{-1, -2, -3}));
        }
    }
}

// ============================================================================================================================================================================================================================

// Tagging tests (@Tag)
// (passed)
// Label a test so the build can include or exclude it, e.g. mvn test -Dgroups=fast.
// Reference: https://docs.junit.org/6.1.0/writing-tests/tagging-and-filtering.html
class MyMathTaggedTest {
    private final MyMath math = new MyMath();

    @Test
    @Tag("fast")
    void taggedFast() {
        assertEquals(2, math.calculateSum(new int[]{1, 1}));
    }
}