package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day18_1 {

	public static void main(String[] args) throws IOException {
		Day18_1 day = new Day18_1();
		System.out.println("day18.1");
		System.out.println("1. " + day.ex1("src/main/resources/input18"));
	}
	
	public long ex1(String path) throws IOException {
		List<Instruction> instructions = instructions(path);
		int numberOfInstructions = instructions.size() - 1;
		Registry registry = new Registry();
		while (registry.index >= 0 && registry.index <= numberOfInstructions) {
			instructions.get(registry.index).execute(registry);
		}
		return registry.lastSoundPlayed;
	}
	
	private static List<Instruction> instructions(String path) throws IOException {
		List<Instruction> moves = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Instructions::fromString)
			.collect(Collectors.toList());
		return moves;
	}
	
	static class Registry {
		Long lastSoundPlayed = null;
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
		
		Long valueOf(String entry) {
			Long value = this.vars.get(entry);
			if (value == null) {
				this.vars.put(entry, 0L);
			}
			return this.vars.get(entry);
		}
	}
	
	interface Instruction {
		void execute(Registry registry);
	}
	
	static class Instructions {
		
		static Instruction fromString(String input) {
			String name = input.substring(0, 3);
			switch (name) {
				case "snd":
					return new Snd(input);
				case "set":
					return new Set(input);
				case "add":
					return new Add(input);
				case "mul":
					return new Mul(input);
				case "mod":
					return new Mod(input);
				case "rcv":
					return new Rcv(input);
				case "jgz":
					return new Jgz(input);
			}
			return null;
		}
	}
	
	static abstract class MonoInstruction implements Instruction {
		
		String name, value;
		
		MonoInstruction(String input) {
			String[] tokens = input.split(" +");
			this.name = tokens[0];
			this.value = tokens[1];
		}

		@Override
		public String toString() {
			return this.name + " " + this.value;
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
	
	static class Snd extends MonoInstruction {
		
		Snd(String input) {
			super(input);
		}

		@Override
		public void execute(Registry registry) {
			registry.lastSoundPlayed = registry.compute(this.value);
			registry.index ++;
		}
	}
	
	static class Rcv extends MonoInstruction {
		
		Rcv(String input) {
			super(input);
		}

		@Override
		public void execute(Registry registry) {
			if (registry.compute(this.value) != 0) {
				registry.index = -1;
			}
			else {
				registry.index ++;
			}
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
	
	static class Add extends BiInstruction {

		Add(String input) {
			super(input);
		}

		@Override
		public void execute(Registry registry) {
			registry.vars.put(this.var, registry.valueOf(this.var) + registry.compute(this.value));
			registry.index ++;
		}
	}
	
	static class Mul extends BiInstruction {

		Mul(String input) {
			super(input);
		}

		@Override
		public void execute(Registry registry) {
			registry.vars.put(this.var, registry.valueOf(this.var) * registry.compute(this.value));
			registry.index ++;
		}
	}
	
	static class Mod extends BiInstruction {
		
		Mod(String input) {
			super(input);
		}
		
		@Override
		public void execute(Registry registry) {
			registry.vars.put(this.var, registry.valueOf(this.var) % registry.compute(this.value));
			registry.index ++;
		}
	}
	
	static class Jgz extends BiInstruction {
		
		Jgz(String input) {
			super(input);
		}
		
		@Override
		public void execute(Registry registry) {
			if (registry.valueOf(this.var) > 0) {
				registry.index = registry.index + registry.compute(this.value).intValue();
			}
			else {
				registry.index ++;
			}
		}
	}
}
