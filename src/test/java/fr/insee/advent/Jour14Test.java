package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour14Test {


    @Test
    public void test1() throws Exception {
   	 Jour14 jour = new Jour14();
   	 Assert.assertEquals(8108, jour.ex1("flqrgnkx"));
    }
    
    @Test
    public void test2() throws Exception {
   	 Jour14 jour = new Jour14();
   	 Assert.assertEquals(1242, jour.ex2("flqrgnkx"));
    }
}