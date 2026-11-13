package com.in28minutes.mockito.mockitodemo.business;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Stubbing controls what the mock returns.
//Verifying checks whether the mock was called, with what, and how often.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplVerifyTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_verifyTheCall() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.findTheGreatestFromAllData();

		verify(dataServiceMock).retrieveAllData();  //no count => exactly once
		verify(dataServiceMock, times(1)).retrieveAllData();
		verify(dataServiceMock, atLeastOnce()).retrieveAllData();

		//nothing was stored, so never() passes
		verify(dataServiceMock, never()).storeGreatest(anyInt());
	}

	@Test
	void storeTheGreatestFromAllData_verifyTheArgument() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock).storeGreatest(25);  //25 is the greatest of the three
		verify(dataServiceMock, never()).storeGreatest(15);
	}

	@Test
	void storeTheGreatestFromAllData_verifyNothingElseHappened() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		businessImpl.storeTheGreatestFromAllData();

		verify(dataServiceMock).retrieveAllData();
		verify(dataServiceMock).storeGreatest(25);
		verifyNoMoreInteractions(dataServiceMock);  //fails if we missed a call
	}

}
