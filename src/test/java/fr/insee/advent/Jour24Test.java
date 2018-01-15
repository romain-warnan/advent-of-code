package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour24Test {

	@Test
	public void test1() throws Exception {
		Jour24 jour = new Jour24();
		Assert.assertEquals(31, jour.ex1("src/test/resources/input24"));
	}
	
	@Test
	public void test2() throws Exception {
		Jour24 jour = new Jour24();
		Assert.assertEquals(19, jour.ex2("src/test/resources/input24"));
	}
}