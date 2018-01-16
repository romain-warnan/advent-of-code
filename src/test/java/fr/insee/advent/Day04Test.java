package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day04Test {

    @Test
    public void test1() throws Exception {
        Day04 day = new Day04();
        Assert.assertEquals(2, day.ex1("src/test/resources/input4"));
    }
    
    @Test
    public void test21() throws Exception {
        Day04 day = new Day04();
        Assert.assertEquals(true, day.noAnagrams("abcde fghij"));
    }
    
    @Test
    public void test22() throws Exception {
        Day04 day = new Day04();
        Assert.assertEquals(false, day.noAnagrams("abcde xyz ecdab"));
    }
    
    @Test
    public void test23() throws Exception {
        Day04 day = new Day04();
        Assert.assertEquals(true, day.noAnagrams("a ab abc abd abf abj"));
    }
    
    @Test
    public void test24() throws Exception {
        Day04 day = new Day04();
        Assert.assertEquals(true, day.noAnagrams("iiii oiii ooii oooi oooo"));
    }
    
    @Test
    public void test25() throws Exception {
        Day04 day = new Day04();
        Assert.assertEquals(false, day.noAnagrams("oiii ioii iioi iiio"));
    }
}