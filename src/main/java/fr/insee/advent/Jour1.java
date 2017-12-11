package fr.insee.advent;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Jour1 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get("src/main/resources/input1")).get(0);
        Jour1 jour1 = new Jour1();
        System.out.println("Jour1");
        System.out.println("1. " + jour1.captcha1(input));
    }

    public int captcha1(String input) {
        if(StringUtils.length(input) < 2) {
            return 0;
        }
        int length = input.length();
        int score = 0;
        for(int n = 0 ; n < length ; n ++) {
            char a = input.charAt(n);
            char b = input.charAt((n + 1) % length);
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
