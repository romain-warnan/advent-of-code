package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day12Test {


    @Test
    public void test1() throws Exception {
   	 Day12 day = new Day12();
   	 Assert.assertEquals(6, day.ex1("src/test/resources/input11"));
    }
   
    @Test
    public void test2() throws Exception {
   	 Day12 day = new Day12();
   	 Assert.assertEquals(2, day.ex2("src/test/resources/input11"));
    }
}