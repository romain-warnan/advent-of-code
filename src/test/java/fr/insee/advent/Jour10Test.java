package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour10Test {

    @Test
    public void test1() throws Exception {
    	Jour10 jour = new Jour10();
        Assert.assertEquals(12, jour.ex1(5, "3,4,1,5"));
    }
}