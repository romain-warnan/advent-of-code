package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Jour8 {

	public static void main(String[] args) throws IOException {
		Jour8 jour = new Jour8();
		System.out.println("Jour8");
		System.out.println("1. " + jour.ex1("src/main/resources/input8"));
		System.out.println("2. " + jour.ex2("src/main/resources/input8"));
		
	}
	
	public long ex1(String path) throws IOException {
		Stack stack = new Stack();
		Files.readAllLines(Paths.get(path))
			.stream()
			.map(Instruction::fromLine)
			.forEachOrdered(stack::operate);
		return stack.maxValue();
	}

	public long ex2(String path) throws IOException {
		Stack stack = new Stack();
		return Files.readAllLines(Paths.get(path))
			.stream()
			.map(Instruction::fromLine)
			.mapToLong(stack::operate)
			.max()
			.getAsLong();
	}
	
	public static class Stack {
		private Map<String, Long> variables;
		
		public Stack() {
			variables = new HashMap<>();
		}
		
		public Long valueOf(String variable) {
			Long value = variables.get(variable);
			return value == null ? 0 : value;
		}
		
		public Long operate(Instruction instruction) {
			if(instruction.evaluateCondition(this)) {
				Long value = this.valueOf(instruction.variable);
				switch (instruction.op) {
    				case "inc":
    					value = value + instruction.increment;
    					break;
    				case "dec":
    					value = value - instruction.increment;
    					break;
				}
				this.variables.put(instruction.variable, value);
			}
			return this.valueOf(instruction.variable);
		}
		
		public Long maxValue() {
			return variables.values()
				.stream()
				.mapToLong(l -> l)
				.max().getAsLong();
		}
	}
	
	public static class Instruction {
		
		String variable;
		String op;
		Long increment;
		String leftVariable;
		String symbol;
		Long rightValue;
		
		public boolean evaluateCondition(Stack stack) {
			long right = rightValue.longValue();
			long left = stack.valueOf(leftVariable).longValue();
			switch(symbol) {
    			case ">":
    				return left > right;
    			case ">=":
    				return left >= right;
    			case "==":
    				return left == right;
    			case "!=":
    				return left != right;
    			case "<=":
    				return left <= right;
    			case "<":
    				return left < right;
			}
			return false;
		}
		
		public static Instruction fromLine(String line) {
			Instruction instance = new Instruction();
			String[] tokens = line.split(" +");
			instance.variable = tokens[0];
			instance.op = tokens[1];
			instance.increment = Long.valueOf(tokens[2]);
			instance.leftVariable = tokens[4];
			instance.symbol = tokens[5];
			instance.rightValue = Long.valueOf(tokens[6]);
			return instance;
		}
	}
}
