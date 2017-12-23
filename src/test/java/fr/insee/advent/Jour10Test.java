package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour10Test {

	private static final Jour10 jour = new Jour10();
	
    @Test
    public void test1() throws Exception {
        Assert.assertEquals(12, jour.ex1(5, "3,4,1,5"));
    }
    
    @Test
    public void test2_toAscii() {
   	 Assert.assertArrayEquals(new int[] {49, 44, 50, 44, 51}, jour.toAscii("1,2,3"));
    }
    
    @Test
    public void test2_addSuffix() {
   	 Assert.assertArrayEquals(new int[] {49, 44, 50, 44, 51, 17, 31, 73, 47, 23}, jour.addSuffix(new int[] {49, 44, 50, 44, 51}));
    }
    
}