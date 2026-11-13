package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//verify(mock).storeGreatest(25) only works when we already know the value.
//A captor records what the code actually passed, for arguments built inside the method.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplCaptorTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Captor
	private ArgumentCaptor<Integer> greatestValueCaptor;

	@Test
	void storeTheGreatestFromAllData_captureTheArgument() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock).storeGreatest(greatestValueCaptor.capture());
		assertThat(greatestValueCaptor.getValue()).isEqualTo(25);
	}

	@Test
	void storeTheGreatestFromAllData_captureMultipleCalls() {
		when(dataServiceMock.retrieveAllData())
				.thenReturn(new int[]{25, 15, 5})
				.thenReturn(new int[]{35});

		businessImpl.storeTheGreatestFromAllData();
		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock, times(2)).storeGreatest(greatestValueCaptor.capture());
		assertThat(greatestValueCaptor.getAllValues()).containsExactly(25, 35);
	}

}
