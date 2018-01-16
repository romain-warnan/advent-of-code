package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;

public class Day04 {

	public static void main(String[] args) throws IOException {
		Day04 day = new Day04();
		System.out.println("Day4");
		System.out.println("1. " + day.ex1("src/main/resources/input4"));
		System.out.println("2. " + day.ex2("src/main/resources/input4"));
	}

	public long countLines(String path, Predicate<String> predicate) throws IOException {
		return Files
			.readAllLines(Paths.get(path))
			.stream()
			.filter(predicate)
			.distinct()
			.count();
	}
	
	public long ex1(String path) throws IOException {
		return this.countLines(path, this::uniqueTokens);
	}
	
	public long ex2(String path) throws IOException {
		return this.countLines(path, this::noAnagrams);
	}

	public boolean uniqueTokens(String line) {
		String[] tokens = line.split(" +|\t+");
		long all = tokens.length;
		long distinct = Arrays.stream(tokens).distinct().count();
		return all == distinct;
	}

	public boolean noAnagrams(String line) {
		String[] tokens = line.split(" +|\t+");
		for (int i = 0; i < tokens.length - 1; i++) {
			for (int j = i + 1; j < tokens.length; j++) {
				if (this.areAnagrams(tokens[i], tokens[j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean areAnagrams(String a, String b) {
		return Arrays.equals(a.chars().sorted().toArray(), b.chars().sorted().toArray());
	}
}
