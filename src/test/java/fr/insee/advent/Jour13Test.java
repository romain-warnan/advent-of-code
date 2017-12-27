package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour13Test {


    @Test
    public void test1() throws Exception {
   	 Jour13 jour = new Jour13();
   	 Assert.assertEquals(24, jour.ex1("src/test/resources/input13"));
    }
}