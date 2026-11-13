package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for MyMath.calculateSum.
 *
 * The lecture ships the first two tests. I kept them exactly as they were and
 * added a few more that show patterns you'll meet in real code: @DisplayName,
 * assertAll for grouped assertions, and assertThrows for the error path. All of
 * them still use the existing calculateSum method - no new code in MyMath.
 *
 * Parameterized versions of these live in JUnit6FeaturesDemoTest.
 */
class MyMathTest {

	private final MyMath math = new MyMath();

	@Test
	void calculateSum_ThreeMemberArray() {
		assertEquals(6, math.calculateSum(new int[] {1, 2, 3}));
	}

	@Test
	void calculateSum_ZeroLengthArray() {
		assertEquals(0, math.calculateSum(new int[] {}));
	}

	@Test
	@DisplayName("calculateSum: several inputs grouped into one test with assertAll")
	void calculateSum_grouped() {
		// assertAll runs every assertion even if an earlier one fails, then
		// reports them all together. Nice when you want one test per behaviour
		// rather than a separate method for each input.
		assertAll("calculateSum",
			() -> assertEquals(0,  math.calculateSum(new int[] {})),
			() -> assertEquals(5,  math.calculateSum(new int[] {5})),
			() -> assertEquals(6,  math.calculateSum(new int[] {1, 2, 3})),
			() -> assertEquals(-3, math.calculateSum(new int[] {-1, -2}))
		);
	}

	@Test
	@DisplayName("calculateSum(null) throws NullPointerException")
	void calculateSum_null_throws() {
		// The existing for-each over a null array throws NPE - so we get a real
		// error path to test without adding anything to MyMath.
		assertThrows(NullPointerException.class, () -> math.calculateSum(null));
	}

}
