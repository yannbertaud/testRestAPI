package test.java.common;

import static org.junit.Assert.*;

public class Common {
	protected void verifyTrue(boolean condition, String description) {
		println("verify " + description + " is true");
		assertTrue(condition);
	}

	protected void verifyEquals(Object actual, Object expected, String description) {
		println("verify " + description + " is equal to" + expected);
		assertEquals(actual, expected);
	}
	protected void println(String stringToPrint) {
		System.out.println(stringToPrint);
	}
}
