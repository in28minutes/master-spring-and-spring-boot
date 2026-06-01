package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * JUnit Jupiter features that the "first 5 steps" lecture
 * does not cover. Each test here is small on purpose. Read the file once and
 * you will recognise these annotations when you see them in real projects.
 *
 * What is shown here:
 *   - @DisplayName        : nice readable names in test reports
 *   - @ParameterizedTest  : same test run with many inputs (@ValueSource, @CsvSource)
 *   - assertThrows        : check that code throws the right exception
 *   - assertTimeout       : fail the test if it takes too long
 *   - assumeTrue          : skip the test when a precondition is not met
 *   - @RepeatedTest       : run the same test N times
 *   - @Disabled           : turn a test off for now (always give a reason)
 *   - @Nested             : group related tests inside an outer class
 *   - @Tag                : label tests so the build can include or skip them
 *
 * Docs: https://docs.junit.org/current/user-guide/#writing-tests
 */
@DisplayName("Modern JUnit 5/6 features - quick tour")
class ModernJUnit5FeaturesTest {

	private final MyMath math = new MyMath();

	// ------------------------------------------------------------------
	// @ParameterizedTest: run the same test with different inputs
	// ------------------------------------------------------------------

	@ParameterizedTest(name = "{0} is even")
	@ValueSource(ints = {0, 2, 4, 100, -8})
	void isEven_evenNumbers(int input) {
		assertTrue(math.isEven(input));
	}

	@ParameterizedTest(name = "{0} is odd")
	@ValueSource(ints = {1, 3, 7, 99, -5})
	void isEven_oddNumbers(int input) {
		assertFalse(math.isEven(input));
	}

	@ParameterizedTest(name = "divide({0}, {1}) = {2}")
	@CsvSource({
		"10, 2, 5",
		"9,  3, 3",
		"-8, 4, -2",
		"0,  5, 0"
	})
	void divide_csvSource(int dividend, int divisor, int expected) {
		assertEquals(expected, math.divide(dividend, divisor));
	}

	// ------------------------------------------------------------------
	// assertThrows: check the exception type AND inspect it
	// ------------------------------------------------------------------

	@Test
	@DisplayName("divide(_, 0) throws and the message mentions 'zero'")
	void divide_byZero() {
		ArithmeticException ex = assertThrows(
			ArithmeticException.class,
			() -> math.divide(7, 0)
		);
		assertTrue(ex.getMessage().contains("zero"));
	}

	// ------------------------------------------------------------------
	// assertTimeout: fail the test if it takes longer than expected
	// ------------------------------------------------------------------

	@Test
	void calculateSum_isFast() {
		assertTimeout(Duration.ofMillis(100),
			() -> math.calculateSum(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}

	// ------------------------------------------------------------------
	// Assumptions: skip the test when a precondition is not met.
	// Use this when a test only makes sense on, say, Linux or with a
	// specific env var set.
	// ------------------------------------------------------------------

	@Test
	void runsOnlyOn64BitJvm() {
		assumeTrue("64".equals(System.getProperty("sun.arch.data.model")),
			"Skipping - test only makes sense on a 64-bit JVM");
		assertNotNull(math);
	}

	// ------------------------------------------------------------------
	// @RepeatedTest: run the test N times (useful for flaky-test triage)
	// ------------------------------------------------------------------

	@RepeatedTest(value = 3, name = "isEven(2) - run {currentRepetition} of {totalRepetitions}")
	void isEven_repeated() {
		assertTrue(math.isEven(2));
	}

	// ------------------------------------------------------------------
	// @Disabled: turn a test off without deleting it. Always give a reason.
	// ------------------------------------------------------------------

	@Test
	@Disabled("Demo only - shows how to temporarily skip a test")
	void thisTestIsCurrentlySkipped() {
		throw new IllegalStateException("Should never run");
	}

	// ------------------------------------------------------------------
	// @Nested: group related cases under one outer class
	// ------------------------------------------------------------------

	@Nested
	@DisplayName("calculateSum edge cases")
	class CalculateSumEdgeCases {

		@Test
		void emptyArray_returnsZero() {
			assertEquals(0, math.calculateSum(new int[] {}));
		}

		@Test
		void singleElement_returnsThatElement() {
			assertEquals(42, math.calculateSum(new int[] {42}));
		}

		@Test
		void negativeNumbers_areSummedCorrectly() {
			assertEquals(-6, math.calculateSum(new int[] {-1, -2, -3}));
		}
	}

	// ------------------------------------------------------------------
	// @Tag: label a test so the build can include or exclude it.
	// Example: mvn test -Dgroups=slow, or set it in surefire config.
	// ------------------------------------------------------------------

	@Test
	@Tag("fast")
	void taggedFast() {
		assertEquals(2, math.calculateSum(new int[] {1, 1}));
	}

}
