package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day02Test {

    @Test
    public void test1() throws Exception {
        Day02 day = new Day02();
        Assert.assertEquals(18, day.ex1("src/test/resources/input2.1"));
    }

    @Test
    public void test2() throws Exception {
        Day02 day = new Day02();
        Assert.assertEquals(9, day.ex2("src/test/resources/input2.2"));
    }
}