package fr.insee.advent;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jour2 {

    public static void main(String[] args) throws IOException {
        Jour2 jour2 = new Jour2();
        System.out.println("Jour2");
        System.out.println("1. " + jour2.checksum1("src/main/resources/input2"));
    }

    public int checksum1(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream()
            .map(line -> line.split(" +|\t+"))
            .mapToInt(this::diffMinMax)
            .sum();
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
}
