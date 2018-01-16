package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day21Test {

	@Test
	public void test1() throws Exception {
		Day21 day = new Day21();
		Assert.assertEquals(12, day.ex(2, "src/test/resources/input21"));
	}
}