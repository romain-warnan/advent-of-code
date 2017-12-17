package fr.insee.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jour6 {

	public static void main(String[] args) throws IOException {
		Jour5 jour = new Jour5();
		System.out.println("Jour5");
		System.out.println("1. " + jour.ex1("11	11	13	7	0	15	5	5	4	4	1	1	7	1	15	11"));
	}

	public long ex1(String input) {
		List<Memory> memories = new ArrayList<>();
		memories.add(this.firstMemory(input));
		int n = 0;
		while(true) {
			n ++;
			Memory memory = this.reallocate(memories.get(memories.size() - 1));
			if (memories.contains(memory)){
				return n;
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
	
	private int[] reallocate(int[] banks) {
		// TODO code
		return banks;
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
