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
		List<Integer> marks = this.marks(size);
		List<Integer> lengths = this.lengths1(input);
		this.rounds(lengths, marks, size, 1);
		return marks.get(0) * marks.get(1);
	}
	
	public long ex2(int size, String input) {
		List<Integer> marks = this.marks(size);
		List<Integer> lengths = this.lengths2(input);
		this.rounds(lengths, marks, size, 64);
		return marks.get(0) * marks.get(1);
	}

	private List<Integer> marks(int size) {
		return IntStream.range(0, size)
			.boxed()
			.collect(Collectors.toList());
	}
	
	private List<Integer> lengths1(String input) {
		return Arrays.stream(input.split(","))
			.map(Integer::valueOf)
			.collect(Collectors.toList());
	}
	
	private List<Integer> lengths2(String input) {
		return Arrays.stream(this.addSuffix(this.toAscii(input))).boxed().collect(Collectors.toList());
	}
	
	private void rounds(List<Integer> lengths, List<Integer> marks, int size, int iterations) {
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
	
	private final int[] SUFFIX = new int[] {17, 31, 73, 47, 23};
	
	public int[] toAscii(String input) {
		return input.chars().toArray();
	}

	public int[] addSuffix(int[] codes) {
		int[] codesWhithSuffix = Arrays.copyOf(codes, codes.length + SUFFIX.length);
		for (int n = 0; n < SUFFIX.length; n ++) {
			codesWhithSuffix[codes.length + n] = SUFFIX[n];
		}
		return codesWhithSuffix;
	}
}
