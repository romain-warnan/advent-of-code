package fr.insee.advent;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Jour1Test {

    private static Jour1 jour;

    @BeforeClass
    public static void beforeClass() {
        jour = new Jour1();
    }

    @Test
    public void test11() throws Exception {
        Assert.assertEquals(3, jour.ex1("1122"));
    }

    @Test
    public void test12() throws Exception {
        Assert.assertEquals(4, jour.ex1("1111"));
    }

    @Test
    public void test13() throws Exception {
        Assert.assertEquals(0, jour.ex1("1234"));
    }

    @Test
    public void test14() throws Exception {
        Assert.assertEquals(9, jour.ex1("91212129"));
    }

    @Test
    public void test21() throws Exception {
        Assert.assertEquals(6, jour.ex2("1212"));
    }

    @Test
    public void test22() throws Exception {
        Assert.assertEquals(0, jour.ex2("1221"));
    }

    @Test
    public void test23() throws Exception {
        Assert.assertEquals(4, jour.ex2("123425"));
    }

    @Test
    public void test24() throws Exception {
        Assert.assertEquals(12, jour.ex2("123123"));
    }

    @Test
    public void test25() throws Exception {
        Assert.assertEquals(4, jour.ex2("12131415"));
    }
}
