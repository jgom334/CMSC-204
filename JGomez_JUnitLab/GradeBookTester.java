import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradeBookTester {

	GradeBook gradeBook1;
	GradeBook gradeBook2;

	@Before
	public void setUp() throws Exception {
		gradeBook1 = new GradeBook(5);
		gradeBook2 = new GradeBook(5);

		gradeBook1.addScore(1);
		gradeBook1.addScore(2);
		gradeBook1.addScore(3);
		gradeBook1.addScore(4);
		gradeBook1.addScore(5);

		gradeBook2.addScore(1.5);
		gradeBook2.addScore(2.5);
		gradeBook2.addScore(3.5);
		gradeBook2.addScore(4.5);
		gradeBook2.addScore(5.5);

	}

	@After
	public void tearDown() throws Exception {
		gradeBook1 = null;
		gradeBook2 = null;

	}

	@Test
	public void testAddScore() {

		assertTrue(gradeBook1.toString().equals("1.0 2.0 3.0 4.0 5.0"));
		assertTrue(gradeBook2.toString().equals("1.5 2.5 3.5 4.5 5.5"));

		assertEquals(gradeBook1.getScoresSize(), 5);
		assertEquals(gradeBook2.getScoresSize(), 5);

	}

	@Test
	public void testSum() {
		assertTrue(gradeBook1.sum() == 15.0);
		assertTrue(gradeBook2.sum() == 17.5);
	}

	@Test
	public void testMinimum() {
		assertTrue(gradeBook1.minimum() == 1.0);
		assertTrue(gradeBook2.minimum() == 1.5);
	}

	@Test
	public void testFinalScore() {
		assertTrue(gradeBook1.finalScore() == 14.0);
		assertTrue(gradeBook2.finalScore() == 16.0);
	}

}