package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @ParameterizedClass - the newer JUnit feature (5.13+, here on JUnit 6).
 *
 * The difference from @ParameterizedTest: there, ONE method re-runs with many
 * inputs. Here the WHOLE class re-runs once per row of data, and every @Test
 * inside it sees the same injected values. Useful when several tests share the
 * same set of inputs.
 *
 * Below the class runs three times (once per @CsvSource row). The values land
 * in the @Parameter fields, and both @Test methods use them.
 */
@ParameterizedClass
@CsvSource({
	"'1,2,3',  6",
	"'5',      5",
	"'10,-10', 0"
})
@DisplayName("Parameterized class - whole class re-runs per row of data")
class MyParameterizedClassTest {

	@Parameter(0)
	String numbersCsv;   // e.g. "1,2,3"

	@Parameter(1)
	int expectedSum;     // e.g. 6

	private final MyMath math = new MyMath();

	@Test
	void sumMatchesExpected() {
		assertEquals(expectedSum, math.calculateSum(toIntArray(numbersCsv)));
	}

	@Test
	void sumIsTheSameWhenCalledTwice() {
		int[] numbers = toIntArray(numbersCsv);
		assertEquals(math.calculateSum(numbers), math.calculateSum(numbers));
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
