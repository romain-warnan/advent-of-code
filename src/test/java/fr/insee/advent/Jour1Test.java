package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour1Test {

    @Test
    public void captcha1() throws Exception {
        Jour1 jour1 = new Jour1();
        Assert.assertEquals(3, jour1.captcha1("1122"));
        Assert.assertEquals(4, jour1.captcha1("1111"));
        Assert.assertEquals(0, jour1.captcha1("1234"));
        Assert.assertEquals(9, jour1.captcha1("91212129"));
    }

    @Test
    public void captcha2() throws Exception {
        Jour1 jour1 = new Jour1();
        Assert.assertEquals(6, jour1.captcha2("1212"));
        Assert.assertEquals(0, jour1.captcha2("1221"));
        Assert.assertEquals(4, jour1.captcha2("123425"));
        Assert.assertEquals(12, jour1.captcha2("123123"));
        Assert.assertEquals(4, jour1.captcha2("12131415"));
    }
}
