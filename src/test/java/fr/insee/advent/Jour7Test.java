package fr.insee.advent;

import org.junit.Assert;
import org.junit.Test;

import fr.insee.advent.Jour7.Programs;

public class Jour7Test {

    @Test
    public void test1() throws Exception {
        Jour7 jour = new Jour7();
        // Programs programs = Programs.of("src/test/resources/input7").fill();
        // programs.findAll().stream().forEach(System.out::println);
        Assert.assertEquals("tknk", jour.ex1("src/test/resources/input7"));
    }
   
    @Test
    public void test2() throws Exception {
        Jour7 jour = new Jour7();
        Programs programs = Programs.of("src/test/resources/input7").fill();
        programs.findAll().stream()
        		.forEach(p -> System.out.println(p.name + ": " + programs.isBalanced(p)));
    }
}