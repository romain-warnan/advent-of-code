package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Jour9 {

	public static void main(String[] args) throws IOException {
		Jour9 jour = new Jour9();
		System.out.println("Jour9");
		String input = Files.newBufferedReader(Paths.get("src/main/resources/input9")).readLine();
		System.out.println("1. " + jour.ex1(input));
		
	}
	
	public long ex1(String input) {
		return -1;
	}
}
