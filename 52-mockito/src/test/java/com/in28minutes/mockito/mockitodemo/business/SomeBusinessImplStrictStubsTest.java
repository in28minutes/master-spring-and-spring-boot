package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//MockitoExtension defaults to strict stubs. Two failures it reports:
// - UnnecessaryStubbingException : you stubbed something the code never called
// - PotentialStubbingProblem     : the code called your stub with a different argument
//lenient() opts a single stub out.
@ExtendWith(MockitoExtension.class)
class SomeBusinessImplStrictStubsTest {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImpl businessImpl;

	@Test
	void stubThatIsUsed_isHappy() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		assertThat(businessImpl.findTheGreatestFromAllData()).isEqualTo(25);
	}

	//DEMO: delete lenient() below and run again -> UnnecessaryStubbingException
	@Test
	void stubThatIsNeverUsed_needsLenient() {
		lenient().when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		assertThat(businessImpl).isNotNull();  //we never call retrieveAllData()
	}

}
