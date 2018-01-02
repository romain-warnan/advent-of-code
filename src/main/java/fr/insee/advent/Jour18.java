package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Jour18 {

	public static void main(String[] args) throws IOException {
		Jour18 jour = new Jour18();
		System.out.println("Jour18");
		System.out.println("1. " + jour.ex1("src/main/resources/input18"));
	}
	
	public long ex1(String path) throws IOException {
		List<String> instructions = Files.readAllLines(Paths.get(path));
		return 0;
	}
}
