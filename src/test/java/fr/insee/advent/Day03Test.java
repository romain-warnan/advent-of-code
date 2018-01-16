package fr.insee.advent;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Day03Test {

	private static Day03 day;
	
	@BeforeClass
	public static void beforeClass() {
		day = new Day03();
	}
	
	
    @Test
    public void test11() throws Exception {
        Assert.assertEquals(0, day.ex1(1));
    }
    
    @Test
    public void test12() throws Exception {
        Assert.assertEquals(3, day.ex1(12));
    }
    
    @Test
    public void test13() throws Exception {
        Assert.assertEquals(2, day.ex1(23));
    }
    
    @Test
    public void test14() throws Exception {
        Assert.assertEquals(31, day.ex1(1024));
    }
}