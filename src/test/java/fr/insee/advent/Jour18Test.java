package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour18Test {


    @Test
    public void test1() throws Exception {
   	 Jour18_1 jour = new Jour18_1();
   	 Assert.assertEquals(4, jour.ex1("src/test/resources/input18.1"));
    }
    
    @Test
    public void test2() throws Exception {
   	 Jour18_2 jour = new Jour18_2();
   	 Assert.assertEquals(3, jour.ex2("src/test/resources/input18.2"));
    }
}