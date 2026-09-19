package com.in28minutes.mockito.mockitodemo.list;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

//A mock does nothing unless you stub it.
//A spy wraps a real object: the real behavior runs, and you can still stub and verify it.
//Prefer a mock. A spy is for code you cannot redesign.
class SpyTest {

	@Test
	void mock_ignoresTheRealBehavior() {
		//mock() infers the type from the variable, so List.class is not needed
		List<String> listMock = mock();

		listMock.add("SomeString");

		assertThat(listMock.size()).isZero();     //the add() went nowhere
		assertThat(listMock.get(0)).isNull();
	}

	@Test
	void spy_keepsTheRealBehavior() {
		List<String> listSpy = spy(new ArrayList<String>());

		listSpy.add("SomeString");

		assertThat(listSpy.size()).isEqualTo(1);  //a real ArrayList did the work
		assertThat(listSpy.get(0)).isEqualTo("SomeString");
		verify(listSpy).add("SomeString");        //and it is still a Mockito mock
	}

	@Test
	void spy_canStillBeStubbed() {
		List<String> listSpy = spy(new ArrayList<String>());
		listSpy.add("SomeString");

		when(listSpy.size()).thenReturn(10);      //stubbing wins over real behavior

		//everything the stub does not cover stays real
		assertThat(listSpy.size()).isEqualTo(10);
		assertThat(listSpy.get(0)).isEqualTo("SomeString");
	}

	//Watch the line above: when(listSpy.size()) runs the real size() before it stubs anything.
	//On an ArrayList that is harmless. On a method that throws, or writes to a database, it is not.
	//doReturn().when() never calls the real method, so it is the safe form for a spy.
	@Test
	void spy_stubbedWithoutCallingTheRealMethod() {
		List<String> listSpy = spy(new ArrayList<String>());

		doReturn(10).when(listSpy).size();

		assertThat(listSpy.size()).isEqualTo(10);
	}

}
