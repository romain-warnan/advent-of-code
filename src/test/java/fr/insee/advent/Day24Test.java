package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day24Test {

	@Test
	public void test1() throws Exception {
		Day24 day = new Day24();
		Assert.assertEquals(31, day.ex1("src/test/resources/input24"));
	}
	
	@Test
	public void test2() throws Exception {
		Day24 day = new Day24();
		Assert.assertEquals(19, day.ex2("src/test/resources/input24"));
	}
}