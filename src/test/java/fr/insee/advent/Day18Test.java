package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day18Test {

    @Test
    public void test1() throws Exception {
   	 Day18_1 day = new Day18_1();
   	 Assert.assertEquals(4, day.ex1("src/test/resources/input18.1"));
    }
    
    @Test
    public void test2() throws Exception {
   	 Day18_2 day = new Day18_2();
   	 Assert.assertEquals(3, day.ex2("src/test/resources/input18.2"));
    }
}