package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day08Test {

    @Test
    public void test1() throws Exception {
        Day08 day = new Day08();
        Assert.assertEquals(1, day.ex1("src/test/resources/input8"));
    }
    
    @Test
    public void test2() throws Exception {
        Day08 day = new Day08();
        Assert.assertEquals(10, day.ex2("src/test/resources/input8"));
    }
}