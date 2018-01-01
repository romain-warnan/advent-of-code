package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour15Test {


    @Test
    public void test11() throws Exception {
   	 Jour15 jour = new Jour15();
   	 Assert.assertEquals(1, jour.ex1(5, 65, 8_921));
    }
    
    @Test
    public void test12() throws Exception {
   	 Jour15 jour = new Jour15();
   	 Assert.assertEquals(588, jour.ex1(40_000_000, 65, 8_921));
    }
    
    @Test
    public void test21() throws Exception {
   	 Jour15 jour = new Jour15();
   	 Assert.assertEquals(0, jour.ex2(1_055, 65, 8_921));
   	 Assert.assertEquals(1, jour.ex2(1_056, 65, 8_921));
    }
    
    @Test
    public void test22() throws Exception {
   	 Jour15 jour = new Jour15();
   	 Assert.assertEquals(309, jour.ex2(5_000_000, 65, 8_921));
    }
}