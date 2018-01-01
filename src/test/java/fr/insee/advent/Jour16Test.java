package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour16Test {


    @Test
    public void test1() throws Exception {
   	 Jour16 jour = new Jour16();
   	 Assert.assertEquals("baedc", jour.ex1("abcde", "src/test/resources/input16"));
    }
}