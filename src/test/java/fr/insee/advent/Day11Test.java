package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day11Test {

	private static final Day11 day = new Day11();

    @Test
    public void test11() throws Exception {
        Assert.assertEquals(3, day.ex1("ne,ne,ne"));
    }
    
    @Test
    public void test12() throws Exception {
   	 Assert.assertEquals(0, day.ex1("ne,ne,sw,sw"));
    }
    
    @Test
    public void test13() throws Exception {
   	 Assert.assertEquals(2, day.ex1("ne,ne,s,s"));
    }
    
    @Test
    public void test14() throws Exception {
   	 Assert.assertEquals(3, day.ex1("se,sw,se,sw,sw"));
    }
}