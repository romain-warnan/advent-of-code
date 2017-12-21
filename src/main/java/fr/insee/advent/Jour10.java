package fr.insee.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Jour10 {

	public static void main(String[] args) throws IOException {
		Jour10 jour = new Jour10();
		System.out.println("Jour10");
		System.out.println("1. " + jour.ex1(256, "206,63,255,131,65,80,238,157,254,24,133,2,16,0,1,3"));
	}
	
	public long ex1(int size, String input) {
		List<Integer> marks = IntStream.range(0, 256)
			.mapToObj(n -> n)
			.collect(Collectors.toList());
		List<Integer> numbers = Arrays.stream(input.split(","))
			.map(Integer::valueOf)
			.collect(Collectors.toList());
		int index = 0;
		for (int skip = 0; skip <= 0; skip ++) {
//		for (int skip = 0; skip < numbers.size(); skip ++) {
			List<Integer> copyOfMarks = new ArrayList<>(marks);
			int length = numbers.get(skip);
			for (int p = 0; p < size; p ++) {
				if(index <= p && p < index + length) {
					marks.set(index, copyOfMarks.get(length - p));
				}
			}
			index = (index + skip) % size;
		}
		System.out.println(marks);
		return 0L;
	}

}
