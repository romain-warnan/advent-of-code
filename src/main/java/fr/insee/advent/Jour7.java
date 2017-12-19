package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class Jour7 {

	public static void main(String[] args) throws IOException {
		Jour7 jour = new Jour7();
		System.out.println("Jour7");
		System.out.println("1. " + jour.ex1("src/main/resources/input7"));
	}
	
	public String ex1(String path) throws IOException {
		Programs programs = Programs.of(path).fill();
		Program firstAncestor = programs.findFirstAncestor();
		return firstAncestor.name;
	}

	public static class Programs {
		
		private List<Program> programs;
		
		private Programs(){}
		
		public static Programs of(String path) throws IOException {
			Programs instance = new Programs();
			instance.programs = Files.readAllLines(Paths.get(path))
				.stream()
				.map(Program::fromLine)
				.collect(Collectors.toList());
			return instance;
		}

		public Programs fill() {
			for (Program program : this.programs) {
				program.children = program.children.stream()
					.map(child -> this.findByName(child.name))
					.collect(Collectors.toList());
			}
			return this;
		}
		
		public List<Program> findAll() throws IOException{
			return this.programs;
		}
		
		public Program findFirstAncestor() {
			return this.programs.stream()
				.filter(p -> p.isFirstAncestor(programs))
				.findFirst()
				.get();
		}
		
		public Program findByName(String name){
			return this.programs.stream()
				.filter(p -> p.name.equals(name))
				.findFirst()
				.get();
		}
	}
	
	public static class Program {

		String name;
		Integer weight;
		List<Program> children;

		public Program() {
			this.children = new ArrayList<>();
		}

		public static Program fromLine(String line) {
			Program program = new Program();
			String[] tokens = line.split(" +|\t+");
			program.name = tokens[0];
			program.weight = Integer.valueOf(tokens[1].replaceAll("[^\\d.]", ""));
			if (tokens.length > 2) {
				for (int n = 3; n < tokens.length; n++) {
					String name = tokens[n].replace(",", "");
					Program child = new Program();
					child.name = name;
					program.children.add(child);
				}
			}
			return program;
		}

		public boolean hasChildren() {
			return CollectionUtils.isNotEmpty(this.children);
		}

		public boolean isFirstAncestor(List<Program> programs) {
			return programs.stream()
				.flatMap(p -> p.children.stream())
				.noneMatch(p -> p.name.equals(this.name));
		}

		public boolean isBalanced(){
			if (this.hasChildren()) {
				return this.children.stream()
					.map(Program::totalWeight)
					.distinct()
					.count() == 1;
			}
			return true;
		}
		
		public int totalWeight() {
			if (this.hasChildren()) {
				return this.children.stream()
					.mapToInt(Program::totalWeight)
					.sum();
						
			}
			return this.weight;
		}
		
		@Override
		public int hashCode() {
			return Objects.hashCode(this.name);
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof Program) {
				Program other = (Program) object;
				this.name.equals(other.name);
			}
			return false;
		}

		@Override
		public String toString() {
			return name + " (" + weight + ") " + (this.hasChildren() ? "-> " + Arrays.toString(children.stream().map(child -> child.name).toArray()) : "");
		}
	}
}
