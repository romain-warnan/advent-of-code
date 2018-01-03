package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour19Test {

	@Test
	public void test1() throws Exception {
		Jour19 jour = new Jour19();
		Assert.assertEquals("ABCDEF", jour.ex1("src/test/resources/input19"));
	}
	
	@Test
	public void test2() throws Exception {
		Jour19 jour = new Jour19();
		Assert.assertEquals(38, jour.ex2("src/test/resources/input19"));
	}
}