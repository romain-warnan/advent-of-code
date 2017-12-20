package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour8Test {

    @Test
    public void test1() throws Exception {
        Jour8 jour = new Jour8();
        Assert.assertEquals(1, jour.ex1("src/test/resources/input8"));
    }
    
    @Test
    public void test2() throws Exception {
        Jour8 jour = new Jour8();
        Assert.assertEquals(10, jour.ex2("src/test/resources/input8"));
    }
}