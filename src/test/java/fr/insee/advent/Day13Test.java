package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Day13Test {


    @Test
    public void test1() throws Exception {
   	 Day13 day = new Day13();
   	 Assert.assertEquals(24, day.ex1("src/test/resources/input13"));
    }
    
    @Test
    public void test2() throws Exception {
   	 Day13 day = new Day13();
   	 Assert.assertEquals(10, day.ex2("src/test/resources/input13"));
    }
}