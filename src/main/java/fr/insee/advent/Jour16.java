package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jour16 {

	public static void main(String[] args) throws Exception {
		Jour16 jour = new Jour16();
		System.out.println("Jour16");
		System.out.println("1. " + jour.ex1("abcdefghijklmnop", "src/main/resources/input16"));
		System.out.println("2. " + jour.ex2("abcdefghijklmnop", "src/main/resources/input16"));
	}
	
	public String ex1(String input, String path) throws IOException {
		String line = Files.newBufferedReader(Paths.get(path)).readLine();
		List<Move> moves = moves(line);
		
		char[] programs = input.toCharArray();
		for (Move move : moves) {
			programs = move.apply(programs);
		}
		return String.valueOf(programs);
	}
	
	public String ex2(String input, String path) throws IOException {
		String line = Files.newBufferedReader(Paths.get(path)).readLine();
		List<Move> moves = moves(line);
		int cycleLength = cycleLength(input, moves);
		int usefulIterations = 1_000_000_000 % cycleLength;
		char[] programs = input.toCharArray();
		for (int n = 0; n < usefulIterations; n ++) {
			for (Move move : moves) {
				programs = move.apply(programs);
			}
		}
		return String.valueOf(programs);
	}
	
	private static int cycleLength(String input, List<Move> moves) {
		char[] programs = input.toCharArray();
		int n = 0;
		while(true) {
			for (Move move : moves) {
				programs = move.apply(programs);
			}
			n ++;
			if(input.equals(String.valueOf(programs))) {
				return n; 
			}
		}
	}
	
	private static List<Move> moves(String line) {
		List<Move> moves = Arrays.stream(line.split(","))
			.map(Moves::fromString)
			.collect(Collectors.toList());
		return moves;
	}

	public static interface Move {
		char[] apply(char[] programs);
	}
	
	public static class Moves {
		
		public static Move fromString(String input) {
			char prefix = input.charAt(0);
			switch (prefix) {
				case 's':
					return Spin.fromString(input);
				case 'x':
					return Exchange.fromString(input);
				case 'p':
					return Partner.fromString(input);
			}
			return null;
		}
	}
	
	public static class Spin implements Move {
		
		int value;
		
		public static Spin fromString(String input) {
			Spin instance = new Spin();
			instance.value = Integer.valueOf(input.substring(1));
			return instance;
		}

		@Override
		public char[] apply(char[] programs) {
			int length = programs.length;
			char[] programsAfterMove = new char[length];
			for (int n = 0; n < length; n ++) {
				programsAfterMove[(n + this.value) % length] = programs[n];
			}
			return programsAfterMove;
		}
	}
	
	public static class Exchange implements Move {
		
		int indexOfA, indexOfB;
		
		public static Exchange fromString(String input) {
			Exchange instance = new Exchange();
			String[] tokens = input.substring(1).split("/");
			instance.indexOfA = Integer.valueOf(tokens[0]);
			instance.indexOfB = Integer.valueOf(tokens[1]);
			return instance;
		}

		@Override
		public char[] apply(char[] programs) {
			int length = programs.length;
			char[] programsAfterMove = Arrays.copyOf(programs, length);
			programsAfterMove[indexOfA] = programs[indexOfB];
			programsAfterMove[indexOfB] = programs[indexOfA];
			return programsAfterMove;
		}
	}
	
	public static class Partner implements Move {
		
		char nameOfA, nameOfB;
		
		public static Partner fromString(String input) {
			Partner instance = new Partner();
			String[] tokens = input.substring(1).split("/");
			instance.nameOfA = tokens[0].charAt(0);
			instance.nameOfB = tokens[1].charAt(0);
			return instance;
		}

		@Override
		public char[] apply(char[] programs) {
			int length = programs.length;
			int indexOfA = this.indexOf(nameOfA, programs);
			int indexOfB = this.indexOf(nameOfB, programs);
			char[] programsAfterMove = Arrays.copyOf(programs, length);
			programsAfterMove[indexOfA] = programs[indexOfB];
			programsAfterMove[indexOfB] = programs[indexOfA];
			return programsAfterMove;
		}
		
		private int indexOf(char name, char[] programs) {
			for (int index = 0; index < programs.length; index ++) {
				if (programs[index] == name) {
					return index;
				}
			}
			return -1;
		}
	}
}
