package fr.insee.advent;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Jour1 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get("src/main/resources/input1")).get(0);
        Jour1 jour = new Jour1();
        System.out.println("Jour1");
        System.out.println("1. " + jour.ex1(input));
        System.out.println("2. " + jour.ex2(input));
    }

    public int ex1(String input) {
        return this.captcha(input, 1);
    }

    public int ex2(String input) {
        return this.captcha(input, input.length() / 2);
    }

    public int captcha(String input, int step) {
        if(StringUtils.length(input) < 2) {
            return 0;
        }
        int length = input.length();
        int score = 0;
        for(int n = 0 ; n < length ; n ++) {
            char a = input.charAt(n);
            char b = input.charAt((n + step) % length);
            if (a == b) {
                score = score + this.charToInt(a);
            }
        }
        return score;
    }

    private Integer charToInt(char a) {
        return Integer.valueOf(String.valueOf(a));
    }
}
