package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour22Test {

	@Test
	public void test11() throws Exception {
		Jour22 jour = new Jour22();
		Assert.assertEquals(5, jour.ex1(7, "src/test/resources/input22"));
	}
	
	@Test
	public void test12() throws Exception {
		Jour22 jour = new Jour22();
		Assert.assertEquals(41, jour.ex1(70, "src/test/resources/input22"));
	}
	
	@Test
	public void test13() throws Exception {
		Jour22 jour = new Jour22();
		Assert.assertEquals(5_587, jour.ex1(10_000, "src/test/resources/input22"));
	}
}