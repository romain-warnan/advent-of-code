package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day16Test {


    @Test
    public void test1() throws Exception {
   	 Day16 day = new Day16();
   	 Assert.assertEquals("baedc", day.ex1("abcde", "src/test/resources/input16"));
    }
}