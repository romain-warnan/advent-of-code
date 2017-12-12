package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour2Test {

    @Test
    public void test1() throws Exception {
        Jour2 jour = new Jour2();
        Assert.assertEquals(18, jour.ex1("src/test/resources/input2"));
    }

    @Test
    public void test2() throws Exception {
        Jour2 jour = new Jour2();
        Assert.assertEquals(9, jour.ex2("src/test/resources/input2"));
    }
}