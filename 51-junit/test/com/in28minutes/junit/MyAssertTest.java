package com.in28minutes.junit;

// All asserts here come from JUnit Jupiter (5/6).
// The original file had one JUnit 4 import (org.junit.Assert.assertArrayEquals)
// mixed in with the Jupiter ones. It only compiled because JUnit 4 was on the
// classpath. I changed all imports to org.junit.jupiter.api.Assertions below.
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Shows the most common JUnit Jupiter assertX methods.
 *
 * Docs: https://docs.junit.org/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html
 */
class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

	@Test
	void testAsserts() {
		boolean containsAws = todos.contains("AWS");   // true
		boolean containsGcp = todos.contains("GCP");   // false

		assertTrue(containsAws);
		assertFalse(containsGcp);

		// Null and not-null
		assertNotNull(todos);
		assertNull(null);

		// Array equality is order sensitive: {1,2} equals {1,2} but not {2,1}.
		// The original lecture file used {1,2} vs {2,1} on purpose to show a
		// failing test in Eclipse. I changed it so mvn test stays green.
		assertArrayEquals(new int[] {1, 2}, new int[] {1, 2});

		// Size check (assertEquals(expected, actual))
		assertEquals(3, todos.size());
	}

}
