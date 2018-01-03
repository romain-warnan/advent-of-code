package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour20Test {

	@Test
	public void test1() throws Exception {
		Jour20 jour = new Jour20();
		Assert.assertEquals(0, jour.ex1("src/test/resources/input20.1"));
	}
	
	@Test
	public void test2() throws Exception {
		Jour20 jour = new Jour20();
		Assert.assertEquals(1, jour.ex2("src/test/resources/input20.2"));
	}
}