package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for MyMath.
 *
 * The original lecture has the two calculateSum tests. I kept them and added a
 * few more that show things you will see in real code: @DisplayName, assertThrows
 * (with a message check), and assertAll for grouped assertions.
 *
 * Parameterized versions of these tests are in ModernJUnit5FeaturesTest.
 */
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
		// assertAll runs every assertion even if one fails, then reports them all
		// together. Handy when you want one test per behaviour, not per input.
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
		// assertThrows returns the exception, so we can check its message too.
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
