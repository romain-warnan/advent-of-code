package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Jour23 {

	public static void main(String[] args) throws IOException {
		Jour23 jour = new Jour23();
		System.out.println("Jour23");
		System.out.println("1. " + jour.ex1("src/main/resources/input23"));
		System.out.println("2. " + jour.ex2("src/main/resources/input23"));
	}
	
	public long ex1(String path) throws IOException {
		List<Instruction> instructions = instructions(path);
		int numberOfInstructions = instructions.size() - 1;
		Registry registry = new Registry();
		while (registry.index >= 0 && registry.index <= numberOfInstructions) {
			Instruction instruction = instructions.get(registry.index);
			instruction.execute(registry);
		}
		return registry.mulNumber;
	}
	
	public long ex2(String path) throws IOException {
		long b = 57 * 100 + 100_000;
		long c = b + 17_000;
		long h = 0;
		while (b < c) {
			if(isPrime(b)) {
				h ++;
			}
			b = b + 17;
		}
		return h;
	}
	
	private boolean isPrime(long n) {
		long sqrt = Math.round(Math.sqrt(n));
		if(n % 2 == 0) {
			return false;
		}
		for (int i = 3; i < sqrt; i = i + 2) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static List<Instruction> instructions(String path) throws IOException {
		List<Instruction> moves = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Instructions::fromString)
			.collect(Collectors.toList());
		return moves;
	}
	
	static class Registry {
		Long mulNumber = 0L;
		int index = 0;
		Map<String, Long> vars = new HashMap<>();
		
		Long compute(String entry) {
			try {
				return Long.valueOf(entry);
			}
			catch (NumberFormatException e) {
				Long value = this.vars.get(entry);
				if (value == null) {
					this.vars.put(entry, 0L);
				}
				return this.vars.get(entry);
			}
		} 
	}
	
	interface Instruction {
		void execute(Registry registry);
	}
	
	static class Instructions {
		
		static Instruction fromString(String input) {
			String name = input.substring(0, 3);
			switch (name) {
				case "set":
					return new Set(input);
				case "mul":
					return new Mul(input);
				case "sub":
					return new Sub(input);
				case "jnz":
					return new Jnz(input);
			}
			return null;
		}
	}
	
	static abstract class BiInstruction implements Instruction {
		
		String name, var, value;
		
		BiInstruction (String input) {
			String[] tokens = input.split(" +");
			this.name = tokens[0];
			this.var = tokens[1];
			this.value = tokens[2];
		}
		
		@Override
		public String toString() {
			return this.name + " " + this.var + " " + this.value;
		}
	}
	
	static class Set extends BiInstruction {

		Set(String input) {
			super(input);
		}

		@Override
		public void execute(Registry registry) {
			registry.vars.put(this.var, registry.compute(this.value));
			registry.index ++;
		}
	}
	
	static class Mul extends BiInstruction {

		Mul(String input) {
			super(input);
		}

		@Override
		public void execute(Registry registry) {
			registry.vars.put(this.var, registry.compute(this.var) * registry.compute(this.value));
			registry.index ++;
			registry.mulNumber ++;
		}
	}
	
	static class Sub extends BiInstruction {
		
		Sub(String input) {
			super(input);
		}
		
		@Override
		public void execute(Registry registry) {
			registry.vars.put(this.var, registry.compute(this.var) - registry.compute(this.value));
			registry.index ++;
		}
	}
	
	static class Jnz extends BiInstruction {
		
		Jnz(String input) {
			super(input);
		}
		
		@Override
		public void execute(Registry registry) {
			if (registry.compute(this.var) != 0) {
				registry.index = registry.index + registry.compute(this.value).intValue();
			}
			else {
				registry.index ++;
			}
		}
	}
}
