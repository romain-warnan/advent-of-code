package fr.insee.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {

	public static void main(String[] args) throws IOException {
		Day06 day = new Day06();
		System.out.println("Day6");
		String input = "11 11 13 7 0 15 5 5 4 4 1 1 7 1 15 11";
		System.out.println("1. " + day.ex1(input));
		System.out.println("1. " + day.ex2(input));
	}

	public long ex1(String input) {
		List<Memory> memories = new ArrayList<>();
		memories.add(this.firstMemory(input));
		int n = 0;
		while (true) {
			n++;
			Memory memory = this.reallocate(memories.get(memories.size() - 1));
			if (memories.contains(memory)) {
				return n;
			}
			memories.add(memory);
		}
	}
	
	public long ex2(String input) {
		List<Memory> memories = new ArrayList<>();
		memories.add(this.firstMemory(input));
		int n = 0;
		while (true) {
			n ++;
			Memory memory = this.reallocate(memories.get(memories.size() - 1));
			int firstIndex = memories.indexOf(memory);
			if (firstIndex > -1) {
				return n - firstIndex;
			}
			memories.add(memory);
		}
	}

	private Memory firstMemory(String input) {
		int[] banks = Arrays
			.stream(input.split(" +|\t+"))
			.mapToInt(Integer::valueOf)
			.toArray();
		return new Memory(banks);
	}

	private Memory reallocate(Memory memory) {
		return new Memory(this.reallocate(memory.banks));
	}

	private int[] reallocate(int[] input) {
		int[] banks = Arrays.copyOf(input, input.length);
		int max = Arrays.stream(banks).max().getAsInt();
		int indexOfMax = indexOf(banks, max);
		banks[indexOfMax] = 0;
		for (int n = 0; n < max; n++) {
			banks[(indexOfMax + n + 1) % banks.length] ++;
		}
		return banks;
	}
	
	private int indexOf(int[] banks, int max) {
		for (int index = 0; index < banks.length; index ++) {
			if (banks[index] == max) {
				return index;
			}
		}
		return -1;
	}
	
	
	public static class Memory {

		private int[] banks;

		public Memory(int[] banks) {
			super();
			this.banks = banks;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(banks);
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof Memory) {
				Memory other = (Memory) object;
				return Arrays.equals(this.banks, other.banks);
			}
			return false;
		}

		@Override
		public String toString() {
			return "[" + Arrays.toString(banks) + "]";
		}
	}
}
