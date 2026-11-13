package com.in28minutes.mockito.mockitodemo.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//@Mock creates a mock for a plain JUnit test. @MockitoBean puts one in the Spring context,
//so injected beans receive it. Spring Boot 4 removed @MockBean and @SpyBean - use
//@MockitoBean and @MockitoSpyBean, imported from spring-test.
@SpringBootTest
class SomeBusinessImplMockitoBeanTest {

	@MockitoBean
	private DataService dataServiceMock;

	@Autowired
	private SomeBusinessImpl businessImpl;

	@Test
	void findTheGreatestFromAllData_withTheMockInsideTheSpringContext() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25, 15, 5});

		assertThat(businessImpl.findTheGreatestFromAllData()).isEqualTo(25);
	}

	//SomeBusinessImpl is not a @Service in this module, so register it just for this test
	@TestConfiguration
	static class TestConfig {

		@Bean
		SomeBusinessImpl someBusinessImpl(DataService dataService) {
			return new SomeBusinessImpl(dataService);
		}

	}

}
