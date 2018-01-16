package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day19Test {

	@Test
	public void test1() throws Exception {
		Day19 day = new Day19();
		Assert.assertEquals("ABCDEF", day.ex1("src/test/resources/input19"));
	}
	
	@Test
	public void test2() throws Exception {
		Day19 day = new Day19();
		Assert.assertEquals(38, day.ex2("src/test/resources/input19"));
	}
}