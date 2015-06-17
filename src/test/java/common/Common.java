package test.java.common;

import static org.junit.Assert.*;

public class Common {
	protected void verifyTrue(boolean condition, String description) {
		println("  Verify " + description + " is true");
		assertTrue(condition);
	}

	protected void verifyEquals(Object expected, Object actual, String description) {
		println("  Verify " + description + " is equal to " + expected);
		assertEquals(expected, actual);
	}
	protected void println(String stringToPrint) {
		System.out.println(stringToPrint);
	}
	
	protected void action(String stringToPrint) {
		println("  Action -- " + stringToPrint);
	}
}
