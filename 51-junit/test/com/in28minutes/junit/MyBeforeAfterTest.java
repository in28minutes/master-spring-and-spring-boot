package com.in28minutes.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The lifecycle demo from the lecture. Run it and watch the console to see the
 * order things fire in: beforeAll once, then beforeEach/test/afterEach for every
 * test, then afterAll once at the end.
 *
 * I added a class-level @DisplayName so the run shows a friendly heading - the
 * methods themselves are untouched.
 */
@DisplayName("Lifecycle: @BeforeAll / @BeforeEach / @AfterEach / @AfterAll")
class MyBeforeAfterTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
	}

	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}

	@Test
	void test3() {
		System.out.println("test3");
	}

	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}

}
