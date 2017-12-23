package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour11Test {

	private static final Jour11 jour = new Jour11();

    @Test
    public void test11() throws Exception {
        Assert.assertEquals(3, jour.ex1("ne,ne,ne"));
    }
    
    @Test
    public void test12() throws Exception {
   	 Assert.assertEquals(0, jour.ex1("ne,ne,sw,sw"));
    }
    
    @Test
    public void test13() throws Exception {
   	 Assert.assertEquals(2, jour.ex1("ne,ne,s,s"));
    }
    
    @Test
    public void test14() throws Exception {
   	 Assert.assertEquals(3, jour.ex1("se,sw,se,sw,sw"));
    }
}