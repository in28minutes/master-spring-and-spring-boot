package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.never;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//The same tests in given/when/then form.
//when-thenReturn becomes given-willReturn, and verify becomes then-should.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplBddTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		//given
		given(dataServiceMock.retrieveAllData()).willReturn(new int[]{25, 15, 5});

		//when
		int result = businessImpl.findTheGreatestFromAllData();

		//then
		assertThat(result).isEqualTo(25);
		then(dataServiceMock).should().retrieveAllData();
	}

	@Test
	void storeTheGreatestFromAllData_basicScenario() {
		//given
		given(dataServiceMock.retrieveAllData()).willReturn(new int[]{25, 15, 5});

		//when
		businessImpl.storeTheGreatestFromAllData();

		//then
		then(dataServiceMock).should().storeGreatest(25);
		then(dataServiceMock).should(never()).storeGreatest(15);
	}

}
