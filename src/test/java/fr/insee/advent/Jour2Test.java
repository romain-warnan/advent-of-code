package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

public class Jour2Test {

    @Test
    public void checksum() throws Exception {
        Jour2 jour2 = new Jour2();
        Assert.assertEquals(18, jour2.ex1("src/test/resources/input2"));
    }
}
