package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour5Test {

    @Test
    public void test1() throws Exception {
        Jour5 jour = new Jour5();
        Assert.assertEquals(5, jour.ex1("src/test/resources/input5"));
    }
    
    @Test
    public void test2() throws Exception {
        Jour5 jour = new Jour5();
        Assert.assertEquals(10, jour.ex2("src/test/resources/input5"));
    }
}