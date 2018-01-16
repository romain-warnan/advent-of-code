package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day14Test {


    @Test
    public void test1() throws Exception {
   	 Day14 day = new Day14();
   	 Assert.assertEquals(8108, day.ex1("flqrgnkx"));
    }
    
    @Test
    public void test2() throws Exception {
   	 Day14 day = new Day14();
   	 Assert.assertEquals(1242, day.ex2("flqrgnkx"));
    }
}