package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day07Test {

    @Test
    public void test1() throws Exception {
        Day07 day = new Day07();
        Assert.assertEquals("tknk", day.ex1("src/test/resources/input7"));
    }
   
    @Test
    public void test2() throws Exception {
        Day07 day = new Day07();
        Assert.assertEquals(60, day.ex2("src/test/resources/input7"));
    }
}