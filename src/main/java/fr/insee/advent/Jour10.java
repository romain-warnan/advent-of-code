package fr.insee.advent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jour10 {

	public static void main(String[] args) throws IOException {
		Jour10 jour = new Jour10();
		System.out.println("Jour10");
		System.out.println("1. " + jour.ex1(256, "206,63,255,131,65,80,238,157,254,24,133,2,16,0,1,3"));
	}
	
	public long ex1(int size, String input) {
		List<Integer> numbers = Arrays.stream(input.split(","))
			.map(Integer::valueOf)
			.collect(Collectors.toList());
		
		return 0L;
	}

}
