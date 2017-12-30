package fr.insee.advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

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

	public long ex2(String input) {
		int[][] grid = this.grid(input);
		Arrays.stream(grid)
			.forEach(row -> System.out.println(Arrays.toString(row)));
		return 0L;
	}
	
	private int[][] grid(String input) {
		int[][] grid = new int[128][128];
		List<String> hashes = IntStream.range(0, 128)
			.mapToObj(n -> input + "-" + n)
			.map(Jour10::hashKnot)
			.collect(Collectors.toList());
		
		for (int i = 0; i < 128; i ++) { 
			String hash = hashes.get(i);
			for (int j = 0; j < 32; j ++) {
				 String hex = String.valueOf(hash.charAt(j));
				 String bin = Integer.toBinaryString(Integer.parseInt(hex, 16));
				 bin = leftPad(bin);
				 for (int index = 0; index < 4; index ++) {
					 grid[i][4 * j + index] = Character.getNumericValue(bin.charAt(index));
				 }
			}	
		}
		return grid;
	}
	
	private static String leftPad(String string) {
		if (string.length() >= 4) {
			return string;
		}
		String padded = new String(string);
		for (int n = string.length(); n < 4; n ++) {
			padded = '0' + padded;
		}
		return padded;
	}
}