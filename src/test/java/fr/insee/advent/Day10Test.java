package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day10Test {

	private static final Day10 day = new Day10();
	
    @Test
    public void test1() throws Exception {
        Assert.assertEquals(12, day.ex1(5, "3,4,1,5"));
    }
    
    @Test
    public void test21() throws Exception {
        Assert.assertEquals("a2582a3a0e66e6e86e3812dcb672a272", day.ex2(256, ""));
    }
    
    @Test
    public void test22() throws Exception {
   	 Assert.assertEquals("33efeb34ea91902bb2f59c9920caa6cd", day.ex2(256, "AoC 2017"));
    }
    
    @Test
    public void test23() throws Exception {
   	 Assert.assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", day.ex2(256, "1,2,3"));
    }
    
    @Test
    public void test24() throws Exception {
   	 Assert.assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", day.ex2(256, "1,2,4"));
    }
}