package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour9Test {

	private static final Jour9 jour = new Jour9();
	
    @Test
    public void test11() throws Exception {
        Assert.assertEquals(1, jour.ex1("{}"));
    }
    
    @Test
    public void test12() throws Exception {
        Assert.assertEquals(6, jour.ex1("{{{}}}"));
    }
    
    @Test
    public void test13() throws Exception {
        Assert.assertEquals(5, jour.ex1("{{},{}}"));
    }
    
    @Test
    public void test14() throws Exception {
        Assert.assertEquals(16, jour.ex1("{{{},{},{{}}}}"));
    }
    
    @Test
    public void test15() throws Exception {
        Assert.assertEquals(1, jour.ex1("{<a>,<a>,<a>,<a>}"));
    }
    
    @Test
    public void test16() throws Exception {
        Assert.assertEquals(9, jour.ex1("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
    }
    
    @Test
    public void test17() throws Exception {
        Assert.assertEquals(9, jour.ex1("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
    }
    
    @Test
    public void test18() throws Exception {
        Assert.assertEquals(3, jour.ex1("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
    }
}