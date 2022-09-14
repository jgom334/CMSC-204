import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * 
 * @author Jonathan Gomez
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> thePasswords;
	String password, password2;

	@Before
	public void setUp() throws Exception {
		String[] passwordArray = { "ThisJ0hn!!", "Kangethe", "table20", "424225", "m@ne%", "password", "IIDDKK",
				"ChickFil@" };
		thePasswords = new ArrayList<String>();
		thePasswords.addAll(Arrays.asList(passwordArray));
	}

	@After
	public void tearDown() throws Exception {
		thePasswords = null;
	}

	/**
	 * Test if the password is less than 6 characters long. This test should throw a
	 * LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Short"));
			assertTrue("Did not throw lengthException", false);
		} catch (LengthException e) {
			assertTrue("Successfully threw a LengthException", true);
		}

		catch (Exception e) {
			assertTrue("Threw some other exception besides LengthException", false);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character This test
	 * should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() {
		try {
			assertFalse(PasswordCheckerUtility.isValidPassword("alllowercase"));
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("george2ZZZ"));

			assertTrue("Did not throw NoUpperAlphaException", false);
		} catch (NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaExcepetion", true);
		} catch (Exception e) {
			System.out.print(e);
			assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character This test
	 * should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("ALUPERCASE23@"));

			assertTrue("Did not throw NoLowerAlphaException", false);
		} catch (NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaExcepetion", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides NoLowerAlphaException", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		try {

			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("987abc7");
			assertTrue("Did not throw WeakPassword Exception", false);
		} catch (WeakPasswordException e) {
			assertTrue("Successfully threw a WeakPasswordException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("ALLLan3@1293"));
			assertTrue("Did not throw an InvalidSequenceException", false);
		} catch (InvalidSequenceException e) {
			assertTrue("Successfully threw an InvalidSequenceExcepetion", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException", false);
		}
	}

	/**
	 * Test if the password has at least one digit One test should throw a
	 * NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		try {
			assertEquals(PasswordCheckerUtility.isValidPassword("nOdigits@here!"), true);
			assertTrue("did not throw NoDigitException", false);
		} catch (NoDigitException e) {
			assertTrue("threw NoDigitException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an NoDigitException", false);
		}
	}

	/**
	 * Test correct passwords This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Chicago23!"));
			assertTrue("This is a valid password", true);
		} catch (Exception e) {
			assertTrue("Threw an exception", false);
		}
	}

	/**
	 * Test the invalidPasswords method Check the results of the ArrayList of
	 * Strings returned by the validPasswords method
	 */
	// @Test
	public void testInvalidPasswords() {

		ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(thePasswords);

		Scanner scan = new Scanner(results.get(0));
		assertEquals(scan.next(), "Kangethe");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special"));

		scan = new Scanner(results.get(1));
		assertEquals(scan.next(), "table20");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));

		scan = new Scanner(results.get(2));
		assertEquals(scan.next(), "424225");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));

		scan = new Scanner(results.get(3));
		assertEquals(scan.next(), "m@ne%");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));

		scan = new Scanner(results.get(4));
		assertEquals(scan.next(), "password");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));

		scan = new Scanner(results.get(5));
		assertEquals(scan.next(), "IIDDKK");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence"));
	}

}