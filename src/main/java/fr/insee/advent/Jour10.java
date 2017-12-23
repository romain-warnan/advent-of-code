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
	}
	
	public long ex1(int size, String input) {
		List<Integer> marks = IntStream.range(0, size)
			.boxed()
			.collect(Collectors.toList());
		List<Integer> lengths = Arrays.stream(input.split(","))
			.map(Integer::valueOf)
			.collect(Collectors.toList());
		int index = 0;
		for (int skip = 0; skip < lengths.size(); skip ++) {
			List<Integer> copyOfMarks = new ArrayList<>(marks);
			int length = lengths.get(skip);
			List<Integer> numbers = IntStream.iterate(index, i -> (i + 1) % size)
				.limit(length)
				.boxed()
				.collect(Collectors.toList());
			for(int n = 0; n < numbers.size(); n ++) {
				marks.set(numbers.get(n), copyOfMarks.get(numbers.get(numbers.size() - n - 1)));
			}
			index = (index + length + skip) % size;
		}
		return marks.get(0) * marks.get(1);
	}

}
