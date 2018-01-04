package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour21Test {

	@Test
	public void test1() throws Exception {
		Jour21 jour = new Jour21();
		Assert.assertEquals(12, jour.ex1(2, "src/test/resources/input21"));
	}
}