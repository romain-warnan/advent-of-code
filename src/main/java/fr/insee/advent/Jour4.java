package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Jour4 {

	public static void main(String[] args) throws IOException {
		Jour4 jour = new Jour4();
		System.out.println("Jour4");
		System.out.println("1. " + jour.ex1("src/main/resources/input41"));
	}

	public long ex1(String path) throws IOException {
		return Files
			.readAllLines(Paths.get(path))
			.stream()
			.filter(this::isValid)
			.distinct()
			.count();
	}

	public boolean isValid(String line) {
		String[] tokens = line.split(" +|\t+");
		long all = tokens.length;
		long distinct = Arrays.stream(tokens).distinct().count();
		return all == distinct;
	}
}
