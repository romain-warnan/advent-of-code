package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour17Test {


    @Test
    public void test1() throws Exception {
   	 Jour17 jour = new Jour17();
   	 Assert.assertEquals(638, jour.ex1(3));
    }
}