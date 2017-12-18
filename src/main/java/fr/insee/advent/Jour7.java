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
		List<Program> programs = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Program::fromLine)
			.collect(Collectors.toList());
		Program program = programs.stream()
			.filter(p -> p.isFirstAncestor(programs))
			.findFirst()
			.get();
		return program.name;
	}

	public static class Program {

		String name;
		Integer weight;
		List<String> children;

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
					program.children.add(name);
				}
			}
			return program;
		}

		public boolean hasChildren() {
			return CollectionUtils.isNotEmpty(this.children);
		}

		public boolean isFirstAncestor(List<Program> programs) {
			for (Program program : programs) {
				if (program.children.contains(this.name)) {
					return false;
				}
			}
			return true;
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
			return name + " (" + weight + ") " + (this.hasChildren() ? "-> " + Arrays.toString(children.toArray()) : "");
		}

		
		
	}
}
