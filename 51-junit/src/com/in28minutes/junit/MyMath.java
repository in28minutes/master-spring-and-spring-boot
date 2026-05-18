package com.in28minutes.junit;

import java.util.Objects;

/**
 * Class used by the JUnit tests in this module.
 *
 * Originally this only had calculateSum(int[]). I added divide() and isEven()
 * so the tests have something to use assertThrows and @ParameterizedTest on,
 * instead of creating another class just for that.
 */
public class MyMath {

	// {1,2,3} => 1+2+3 = 6
	public int calculateSum(int[] numbers) {
		Objects.requireNonNull(numbers, "numbers array must not be null");

		int sum = 0;
		for (int number : numbers) {
			sum += number;
		}
		return sum;
	}

	/**
	 * Integer division. Throws ArithmeticException when divisor is 0,
	 * so the tests have something to use assertThrows on.
	 */
	public int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new ArithmeticException("Cannot divide " + dividend + " by zero");
		}
		return dividend / divisor;
	}

	/** Used by the parameterized tests. */
	public boolean isEven(int number) {
		return number % 2 == 0;
	}

}
