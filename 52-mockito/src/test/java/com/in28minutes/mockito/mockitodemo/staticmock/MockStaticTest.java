package com.in28minutes.mockito.mockitodemo.staticmock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

//Static methods once required PowerMock. Mockito 5 makes the inline mock maker the default,
//so mockStatic() works with no extra dependency, as does mocking final classes.
//The mock is scoped to this thread and this try block, so it has to be closed.
class MockStaticTest {

	@Test
	void mockStatic_freezeTheClock() {
		//a real LocalDate, built before the static mock exists
		LocalDate fixedDate = LocalDate.of(2000, 1, 1);

		try (MockedStatic<LocalDate> mockedLocalDate = mockStatic(LocalDate.class)) {
			mockedLocalDate.when(LocalDate::now).thenReturn(fixedDate);

			assertThat(LocalDate.now()).isEqualTo(fixedDate);
			mockedLocalDate.verify(LocalDate::now);   //statics can be verified as well
		}

		//outside the try block the mock is closed, so the real behavior is back
		assertThat(LocalDate.now()).isNotEqualTo(fixedDate);
	}

}
