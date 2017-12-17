package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour4Test {

    @Test
    public void test1() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(2, jour.ex1("src/test/resources/input4"));
    }
    
    @Test
    public void test21() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(true, jour.noAnagrams("abcde fghij"));
    }
    
    @Test
    public void test22() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(false, jour.noAnagrams("abcde xyz ecdab"));
    }
    
    @Test
    public void test23() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(true, jour.noAnagrams("a ab abc abd abf abj"));
    }
    
    @Test
    public void test24() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(true, jour.noAnagrams("iiii oiii ooii oooi oooo"));
    }
    
    @Test
    public void test25() throws Exception {
        Jour4 jour = new Jour4();
        Assert.assertEquals(false, jour.noAnagrams("oiii ioii iioi iiio"));
    }
}