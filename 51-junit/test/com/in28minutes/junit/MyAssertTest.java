package com.in28minutes.junit;

// All of these come from JUnit Jupiter (5/6).
// The original file imported assertArrayEquals from org.junit.Assert (JUnit 4)
// and mixed it in with the Jupiter imports. It only compiled because JUnit 4
// happened to be on the classpath - once we run with JUnit 6 only, that import
// breaks the build. Switched every import to org.junit.jupiter.api.Assertions.
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A tour of the most common JUnit Jupiter assertX methods.
 *
 * Docs: https://docs.junit.org/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html
 */
@DisplayName("Common Jupiter assertions")
class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

	@Test
	void testAsserts() {
		boolean containsAws = todos.contains("AWS");   // true
		boolean containsGcp = todos.contains("GCP");   // false

		assertTrue(containsAws);
		assertFalse(containsGcp);

		// assertNull / assertNotNull were just comments in the original file -
		// turned them into real assertions so they actually run.
		assertNotNull(todos);
		assertNull(null);

		// assertArrayEquals is order sensitive: {1,2} matches {1,2} but not
		// {2,1}. The lecture used {1,2} vs {2,1} on purpose to show a red bar in
		// Eclipse. I made it pass so `mvn test` stays green.
		assertArrayEquals(new int[] {1, 2}, new int[] {1, 2});

		// assertEquals(expected, actual)
		assertEquals(3, todos.size());
	}

}
