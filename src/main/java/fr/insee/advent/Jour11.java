package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Jour11 {

	public static void main(String[] args) throws IOException {
		Jour11 jour = new Jour11();
		System.out.println("Jour11");
		String input = Files.newBufferedReader(Paths.get("src/main/resources/input11")).readLine();
		System.out.println("1. " + jour.ex1(input));
	}
	
	public long ex1(String input) {
		// TODO Auto-generated method stub
		return 0L;
	}

}
