package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day09Test {

	private static final Day09 day = new Day09();
	
    @Test
    public void test11() throws Exception {
        Assert.assertEquals(1, day.ex1("{}"));
    }
    
    @Test
    public void test12() throws Exception {
        Assert.assertEquals(6, day.ex1("{{{}}}"));
    }
    
    @Test
    public void test13() throws Exception {
        Assert.assertEquals(5, day.ex1("{{},{}}"));
    }
    
    @Test
    public void test14() throws Exception {
        Assert.assertEquals(16, day.ex1("{{{},{},{{}}}}"));
    }
    
    @Test
    public void test15() throws Exception {
        Assert.assertEquals(1, day.ex1("{<a>,<a>,<a>,<a>}"));
    }
    
    @Test
    public void test16() throws Exception {
        Assert.assertEquals(9, day.ex1("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
    }
    
    @Test
    public void test17() throws Exception {
        Assert.assertEquals(9, day.ex1("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
    }
    
    @Test
    public void test18() throws Exception {
        Assert.assertEquals(3, day.ex1("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
    }

    @Test
    public void test21() throws Exception {
        Assert.assertEquals(0, day.ex2("<>"));
    }
    
    @Test
    public void test22() throws Exception {
        Assert.assertEquals(17, day.ex2("<random characters>"));
    }
    
    @Test
    public void test23() throws Exception {
        Assert.assertEquals(3, day.ex2("<<<<>"));
    }
    
    @Test
    public void test24() throws Exception {
        Assert.assertEquals(2, day.ex2("<{!>}>"));
    }
    
    @Test
    public void test25() throws Exception {
        Assert.assertEquals(0, day.ex2("<!!>"));
    }
    
    @Test
    public void test26() throws Exception {
        Assert.assertEquals(0, day.ex2("<!!!>>"));
    }
    
    @Test
    public void test27() throws Exception {
        Assert.assertEquals(10, day.ex2("<{o\"i!a,<{i<a>"));
    }
    
}