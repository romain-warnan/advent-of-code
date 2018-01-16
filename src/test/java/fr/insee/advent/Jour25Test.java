package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour25Test {

	@Test
	public void test1() throws Exception {
		Jour25 jour = new Jour25();
		Assert.assertEquals(3, jour.ex1(6, "src/test/resources/input25"));
	}
}