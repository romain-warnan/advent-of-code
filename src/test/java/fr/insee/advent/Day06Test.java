package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day06Test {

    @Test
    public void test1() throws Exception {
        Day06 day = new Day06();
        Assert.assertEquals(5, day.ex1("0 2 7 0"));
    }
    
    @Test
    public void test2() throws Exception {
        Day06 day = new Day06();
        Assert.assertEquals(4, day.ex2("0 2 7 0"));
    }
}