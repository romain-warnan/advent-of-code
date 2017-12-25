package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour12Test {


    @Test
    public void test11() throws Exception {
   	 Jour12 jour = new Jour12();
   	 Assert.assertEquals(6, jour.ex1("src/test/resources/input11"));
    }
   
}