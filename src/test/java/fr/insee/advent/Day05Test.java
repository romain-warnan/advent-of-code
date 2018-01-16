package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day05Test {

    @Test
    public void test1() throws Exception {
        Day05 day = new Day05();
        Assert.assertEquals(5, day.ex1("src/test/resources/input5"));
    }
    
    @Test
    public void test2() throws Exception {
        Day05 day = new Day05();
        Assert.assertEquals(10, day.ex2("src/test/resources/input5"));
    }
}