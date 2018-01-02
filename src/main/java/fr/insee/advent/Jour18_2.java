package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Jour18_2 {

	public static void main(String[] args) throws IOException {
		Jour18_2 jour = new Jour18_2();
		System.out.println("Jour18.2");
		System.out.println("1. " + jour.ex2("src/main/resources/input18"));
	}
	
	public long ex2(String path) throws IOException {
		Registry r0 = new Registry(0);
		Registry r1 = new Registry(1);
		
		List<Instruction> instructions = instructions(path);
//		int numberOfInstructions = instructions.size() - 1;
//		while (stillRunning(r0, numberOfInstructions) && stillRunning(r1, numberOfInstructions)) {
		while (true) {
			if (!r0.waiting) {
				System.out.println("R0 (" + r0.index + ") : " + instructions.get(r0.index) + " [" + r0.queue.size() + "]");
				instructions.get(r0.index).execute(r0, r1);
			}
			if (!r1.waiting) {
				System.out.println("R1 (" + r1.index + ") : " + instructions.get(r1.index) + " [" + r1.queue.size() + "]");
				instructions.get(r1.index).execute(r1, r0);
			}
			if (r0.waiting && r1.waiting) {
				return r1.numberOfSendEvents;
//				break;
			}
		}
	}
	
//	private static boolean stillRunning(Registry registry, int numberOfInstructions) {
//		return registry.index >= 0 && registry.index <= numberOfInstructions;
//	}
	
	private static List<Instruction> instructions(String path) throws IOException {
		List<Instruction> instructions = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Instructions::fromString)
			.collect(Collectors.toList());
		return instructions;
	}
	
	static class Registry {
		int index = 0;
		int numberOfSendEvents = 0;
		boolean waiting = false;
		Map<String, Long> vars = new HashMap<>();
		Queue<Long> queue = new LinkedList<>();
		
		public Registry(long programId) {
			vars.put("p", programId);
		}
		
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
		void execute(Registry ownRegistry, Registry extRegistry);
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
		public void execute(Registry ownRegistry, Registry extRegistry) {
			extRegistry.queue.add(ownRegistry.compute(this.value));
			extRegistry.waiting = false;
			ownRegistry.numberOfSendEvents ++;
			ownRegistry.index ++;
		}
	}
	
	static class Rcv extends MonoInstruction {
		
		Rcv(String input) {
			super(input);
		}

		@Override
		public void execute(Registry ownRegistry, Registry extRegistry) {
			if (ownRegistry.queue.isEmpty()) {
				ownRegistry.waiting = true;
			}
			else {
				ownRegistry.vars.put(this.value, ownRegistry.queue.poll());
				ownRegistry.index ++;
			}
		}
	}
	
	static class Set extends BiInstruction {

		Set(String input) {
			super(input);
		}

		@Override
		public void execute(Registry ownRegistry, Registry extRegistry) {
			ownRegistry.vars.put(this.var, ownRegistry.compute(this.value));
			ownRegistry.index ++;
		}
	}
	
	static class Add extends BiInstruction {

		Add(String input) {
			super(input);
		}

		@Override
		public void execute(Registry ownRegistry, Registry extRegistry) {
			ownRegistry.vars.put(this.var, ownRegistry.valueOf(this.var) + ownRegistry.compute(this.value));
			ownRegistry.index ++;
		}
	}
	
	static class Mul extends BiInstruction {

		Mul(String input) {
			super(input);
		}

		@Override
		public void execute(Registry ownRegistry, Registry extRegistry) {
			ownRegistry.vars.put(this.var, ownRegistry.valueOf(this.var) * ownRegistry.compute(this.value));
			ownRegistry.index ++;
		}
	}
	
	static class Mod extends BiInstruction {
		
		Mod(String input) {
			super(input);
		}
		
		@Override
		public void execute(Registry ownRegistry, Registry extRegistry) {
			ownRegistry.vars.put(this.var, ownRegistry.valueOf(this.var) % ownRegistry.compute(this.value));
			ownRegistry.index ++;
		}
	}
	
	static class Jgz extends BiInstruction {
		
		Jgz(String input) {
			super(input);
		}
		
		@Override
		public void execute(Registry ownRegistry, Registry extRegistry) {
			if (ownRegistry.valueOf(this.var) > 0) {
				ownRegistry.index = ownRegistry.index + ownRegistry.compute(this.value).intValue();
			}
			else {
				ownRegistry.index ++;
			}
		}
	}
}
