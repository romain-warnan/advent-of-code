package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour4Test {

    @Test
    public void test1() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(2, jour.ex1("src/test/resources/input41"));
    }
}