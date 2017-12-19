package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour7Test {

    @Test
    public void test1() throws Exception {
        Jour7 jour = new Jour7();
        Assert.assertEquals("tknk", jour.ex1("src/test/resources/input7"));
    }
   
    @Test
    public void test2() throws Exception {
        Jour7 jour = new Jour7();
        Assert.assertEquals(60, jour.ex2("src/test/resources/input7"));
    }
}