package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour18Test {


    @Test
    public void test1() throws Exception {
   	 Jour18 jour = new Jour18();
   	 Assert.assertEquals(4, jour.ex1("src/test/resources/input18"));
    }
}