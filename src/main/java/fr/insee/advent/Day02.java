package fr.insee.advent;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) throws IOException {
        Day02 day = new Day02();
        System.out.println("Day2");
        System.out.println("1. " + day.ex1("src/main/resources/input2"));
        System.out.println("2. " + day.ex2("src/main/resources/input2"));
    }

    public int sumLines(String path, ToIntFunction<String[]> function) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream()
            .map(line -> line.split(" +|\t+"))
            .mapToInt(function)
            .sum();
    }
    
    public int ex1(String path) throws IOException {
        return this.sumLines(path, this::diffMinMax);
    }

    public int ex2(String path) throws IOException {
        return this.sumLines(path, this::euclidianDivision);
    }

    private int diffMinMax(String[] tokens) {
        if(CollectionUtils.size(tokens) < 2) {
            return 0;
        }
        List<Integer> numbers = Arrays.stream(tokens)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        return numbers.get(numbers.size() - 1) - numbers.get(0);
    }
    
    private int euclidianDivision(String[] tokens) {
        if(CollectionUtils.size(tokens) < 2) {
            return 0;
        }
        List<Integer> numbers = Arrays.stream(tokens)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        for (int i = 0; i < numbers.size(); i ++) {
            for (int j = i + 1; j < numbers.size(); j ++) {
                int a = numbers.get(i);
                int b = numbers.get(j);
                if(b % a == 0) {
                    return b / a;
                }
            }
        }
        return 0;
    }
}
