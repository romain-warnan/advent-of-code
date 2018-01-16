package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day17Test {


    @Test
    public void test1() throws Exception {
   	 Day17 day = new Day17();
   	 Assert.assertEquals(638, day.ex1(3));
    }
}