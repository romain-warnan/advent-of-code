package fr.insee.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Jour10 {

	public static void main(String[] args) throws IOException {
		Jour10 jour = new Jour10();
		System.out.println("Jour10");
		System.out.println("1. " + jour.ex1(256, "206,63,255,131,65,80,238,157,254,24,133,2,16,0,1,3"));
		System.out.println("2. " + jour.ex2(256, "206,63,255,131,65,80,238,157,254,24,133,2,16,0,1,3"));
	}
	
	public long ex1(int size, String input) {
		List<Integer> marks = marks(size);
		List<Integer> lengths = lengths1(input);
		rounds(lengths, marks, size, 1);
		return marks.get(0) * marks.get(1);
	}
	
	public String ex2(int size, String input) {
		return hashKnot(size, input);
	}

	public static String hashKnot(String input) {
		return hashKnot(256, input);
	}
	
	private static String hashKnot(int size, String input) {
		List<Integer> marks = marks(size);   
		List<Integer> lengths = lengths2(input);
		rounds(lengths, marks, size, 64);
		List<Integer> hashes = hashes(size, marks);
		return hashes.stream()
			.map(Integer::toHexString)
			.map(h -> h.length() == 1 ? "0" + h : h)
			.collect(Collectors.joining());
	}
	
	private static List<Integer> hashes(int size, List<Integer> marks) {
		List<Integer> hashes = new ArrayList<>();
		for (int n = 0; n < size; n ++) {
			if (n % 16 == 0) {
				hashes.add(marks.get(n));
			}
			else {
				int index = n / 16;
				int hash = hashes.get(index) ^ marks.get(n);
				hashes.set(index, hash);
			}
		}
		return hashes;
	}

	private static List<Integer> marks(int size) {
		return IntStream.range(0, size)
			.boxed()
			.collect(Collectors.toList());
	}
	
	private static List<Integer> lengths1(String input) {
		return Arrays.stream(input.split(","))
			.map(Integer::valueOf)
			.collect(Collectors.toList());
	}
	
	private static List<Integer> lengths2(String input) {
		return Arrays.stream(addSuffix(toAscii(input)))
			.boxed()
			.collect(Collectors.toList());
	}
	
	private static void rounds(List<Integer> lengths, List<Integer> marks, int size, int iterations) {
		int position = 0, skipSize = 0;
		for (int rank = 0; rank < iterations; rank ++) {
			for (int lengthRank = 0; lengthRank < lengths.size(); lengthRank ++) {
				List<Integer> copyOfMarks = new ArrayList<>(marks);
				int length = lengths.get(lengthRank);
				List<Integer> indexes = IntStream.iterate(position, i -> (i + 1) % size)
					.limit(length)
					.boxed()
					.collect(Collectors.toList());
				for(int n = 0; n < indexes.size(); n ++) {
					marks.set(indexes.get(n), copyOfMarks.get(indexes.get(indexes.size() - n - 1)));
				}
				position = (position + length + skipSize) % size;
				skipSize ++;
			}
		}
	}
	
	private static final int[] SUFFIX = new int[] {17, 31, 73, 47, 23};
	
	private static int[] toAscii(String input) {
		return input.chars().toArray();
	}

	private static int[] addSuffix(int[] codes) {
		int[] codesWhithSuffix = Arrays.copyOf(codes, codes.length + SUFFIX.length);
		for (int n = 0; n < SUFFIX.length; n ++) {
			codesWhithSuffix[codes.length + n] = SUFFIX[n];
		}
		return codesWhithSuffix;
	}
}
