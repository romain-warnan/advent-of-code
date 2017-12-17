package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.ToIntFunction;

public class Jour5 {

	public static void main(String[] args) throws IOException {
		Jour5 jour = new Jour5();
		System.out.println("Jour5");
		System.out.println("1. " + jour.ex1("src/main/resources/input5"));
		System.out.println("2. " + jour.ex2("src/main/resources/input5"));
	}
	
	public long count(String path, ToIntFunction<Integer> function) throws IOException {
		Integer[] numbers = Files
			.readAllLines(Paths.get(path))
			.stream()
			.map(Integer::valueOf)
			.toArray(Integer[]::new);
		int count = 0;
		int index = 0;
		while (index >= 0 && index < numbers.length) {
			int jump = numbers[index];
			numbers[index] = function.applyAsInt(numbers[index]);
			index = index + jump;
			count ++;
		}
		return count;
	}
	
	public long ex1(String path) throws IOException {
		return this.count(path, n -> n + 1);
	}

	public long ex2(String path) throws IOException {
		return this.count(path, n -> n >= 3 ? n - 1 : n + 1);
	}
}
