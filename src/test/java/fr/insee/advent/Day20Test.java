package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day20Test {

	@Test
	public void test1() throws Exception {
		Day20 day = new Day20();
		Assert.assertEquals(0, day.ex1("src/test/resources/input20.1"));
	}
	
	@Test
	public void test2() throws Exception {
		Day20 day = new Day20();
		Assert.assertEquals(1, day.ex2("src/test/resources/input20.2"));
	}
}