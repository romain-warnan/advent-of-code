package fr.insee.advent;

import java.util.stream.IntStream;

public class Jour14 {

	public static void main(String[] args) throws Exception {
		Jour14 jour = new Jour14();
		System.out.println("Jour14");
		System.out.println("1. " + jour.ex1("xlqgujun"));
	}
	
	public long ex1(String input) {
		return IntStream.range(0, 128)
			.mapToObj(n -> input + "-" + n)
			.map(Jour10::hashKnot)
			.flatMap(hash -> hash.chars()
				.mapToObj(n -> Character.toString((char) n)))
				.map(hex -> Integer.parseInt(hex, 16))
				.map(Integer::toBinaryString) // Attention, pour la suite, il manque les 0 qui précèdente le chiffre !
				.flatMap(bin -> bin.chars()
					.mapToObj(n -> (char) n))
					.mapToInt(Character::getNumericValue)
					.sum();
	}
}