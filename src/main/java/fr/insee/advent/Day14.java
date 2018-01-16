package fr.insee.advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14 {

	public static void main(String[] args) throws Exception {
		Day14 day = new Day14();
		System.out.println("Day14");
		System.out.println("1. " + day.ex1("xlqgujun"));
		System.out.println("2. " + day.ex2("xlqgujun"));
	}
	
	public long ex1(String input) {
		return IntStream.range(0, 128)
			.mapToObj(n -> input + "-" + n)
			.map(Day10::hashKnot)
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
		int zone = 1;
		for (int x = 0; x < 128; x ++) {
			for (int y = 0; y < 128; y ++) {
				this.updateGrid(zone, x, y, grid);
				zone ++;
			}	
		}
		return Arrays.stream(grid)
			.flatMap(row -> Arrays.stream(row).boxed())
			.distinct()
			.filter(n -> n > 0)
			.count();
	}
	
	private int[][] grid(String input) {
		int[][] grid = new int[128][128];
		List<String> hashes = IntStream.range(0, 128)
			.mapToObj(n -> input + "-" + n)
			.map(Day10::hashKnot)
			.collect(Collectors.toList());
		
		for (int i = 0; i < 128; i ++) { 
			String hash = hashes.get(i);
			for (int j = 0; j < 32; j ++) {
				 String hex = String.valueOf(hash.charAt(j));
				 String bin = Integer.toBinaryString(Integer.parseInt(hex, 16));
				 bin = leftPad(bin);
				 for (int index = 0; index < 4; index ++) {
					 grid[i][4 * j + index] = 0 - Character.getNumericValue(bin.charAt(index));
				 }
			}	
		}
		return grid;
	}
	
	private void updateGrid(int zone, int x, int y, int[][] grid) {
		if (isInGrid(x, y) && notInZone(x, y, grid)) {
			grid[x][y] = zone;
			this.updateGrid(zone, x - 1, y, grid);
			this.updateGrid(zone, x + 1, y, grid);
			this.updateGrid(zone, x, y - 1, grid);
			this.updateGrid(zone, x, y + 1, grid);
		}
	}
	
	private static boolean isInGrid(int x, int y) {
		return 0 <= x && x < 128 && 0 <= y && y  < 128;
	}
	
	private static boolean notInZone(int x, int y, int[][] grid) {
		return grid[x][y] == -1;
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