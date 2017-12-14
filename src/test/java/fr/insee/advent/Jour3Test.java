package fr.insee.advent;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Jour3Test {

	private static Jour3 jour;
	
	@BeforeClass
	public static void beforeClass() {
		jour = new Jour3();
	}
	
	
    @Test
    public void test11() throws Exception {
        Assert.assertEquals(0, jour.ex1(1));
    }
    
    @Test
    public void test12() throws Exception {
        Assert.assertEquals(3, jour.ex1(12));
    }
    
    @Test
    public void test13() throws Exception {
        Assert.assertEquals(2, jour.ex1(23));
    }
    
    @Test
    public void test14() throws Exception {
        Assert.assertEquals(31, jour.ex1(1024));
    }
}