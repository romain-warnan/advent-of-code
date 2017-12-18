package fr.insee.advent;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.insee.advent.Jour7.Program;
import fr.insee.advent.Jour7.Programs;

public class Jour7Test {

    @Test
    public void test1() throws Exception {
        Jour7 jour = new Jour7();
        Assert.assertEquals("tknk", jour.ex1("src/test/resources/input7"));
        List<Program> programs = Programs.findAll("src/test/resources/input7");
        programs.stream()
        .forEach(p -> System.out.println(p.name + ": " + Programs.isBalanced(p, programs)));
    }
   
    @Test
    public void test2() throws Exception {
        Jour7 jour = new Jour7();
        List<Program> programs = Programs.findAll("src/test/resources/input7");
        programs.stream()
        		.forEach(p -> System.out.println(p.name + ": " + Programs.isBalanced(p, programs)));
    }
}