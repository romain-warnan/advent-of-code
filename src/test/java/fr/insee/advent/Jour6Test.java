package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour6Test {

    @Test
    public void test1() throws Exception {
        Jour6 jour = new Jour6();
        Assert.assertEquals(5, jour.ex1("0 2 7 0"));
    }
}