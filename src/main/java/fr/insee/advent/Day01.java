package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day01 {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get("src/main/resources/input1")).get(0);
        Day01 day = new Day01();
        System.out.println("Day1");
        System.out.println("1. " + day.ex1(input));
        System.out.println("2. " + day.ex2(input));
    }

    public int ex1(String input) {
        return this.captcha(input, 1);
    }

    public int ex2(String input) {
        return this.captcha(input, input.length() / 2);
    }

    public int captcha(String input, int step) {
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
