package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day15Test {


    @Test
    public void test11() throws Exception {
   	 Day15 day = new Day15();
   	 Assert.assertEquals(1, day.ex1(5, 65, 8_921));
    }
    
    @Test
    public void test12() throws Exception {
   	 Day15 day = new Day15();
   	 Assert.assertEquals(588, day.ex1(40_000_000, 65, 8_921));
    }
    
    @Test
    public void test21() throws Exception {
   	 Day15 day = new Day15();
   	 Assert.assertEquals(0, day.ex2(1_055, 65, 8_921));
   	 Assert.assertEquals(1, day.ex2(1_056, 65, 8_921));
    }
    
    @Test
    public void test22() throws Exception {
   	 Day15 day = new Day15();
   	 Assert.assertEquals(309, day.ex2(5_000_000, 65, 8_921));
    }
}