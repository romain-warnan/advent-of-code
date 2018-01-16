package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day25Test {

	@Test
	public void test1() throws Exception {
		Day25 day = new Day25();
		Assert.assertEquals(3, day.ex1(6, "src/test/resources/input25"));
	}
}