package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day22Test {

	@Test
	public void test11() throws Exception {
		Day22_1 day = new Day22_1();
		Assert.assertEquals(5, day.ex1(7, "src/test/resources/input22"));
	}
	
	@Test
	public void test12() throws Exception {
		Day22_1 day = new Day22_1();
		Assert.assertEquals(41, day.ex1(70, "src/test/resources/input22"));
	}
	
	@Test
	public void test13() throws Exception {
		Day22_1 day = new Day22_1();
		Assert.assertEquals(5_587, day.ex1(10_000, "src/test/resources/input22"));
	}
	
	@Test
	public void test21() throws Exception {
		Day22_2 day = new Day22_2();
		Assert.assertEquals(26, day.ex2(100, "src/test/resources/input22"));
	}
	
	@Test
	public void test22() throws Exception {
		Day22_2 day = new Day22_2();
		Assert.assertEquals(2_511_944, day.ex2(10_000_000, "src/test/resources/input22"));
	}
}