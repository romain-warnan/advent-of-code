package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Jour24 {

	public static void main(String[] args) throws IOException {
		Jour24 jour = new Jour24();
		System.out.println("Jour24");
		System.out.println("1. " + jour.ex1("src/main/resources/input24"));
	}
	
	public long ex1(String path) throws IOException {
		List<Component> components = components(path);
		
		return 0L;
	}

	private List<Component> components(String path) throws IOException {
		return Files.readAllLines(Paths.get(path))
			.stream()
			.map(Component::fromLine)
			.collect(Collectors.toList());
	}
	
	static class Component {
		int left, right;
		
		void flip() {
			int tmp = left;
			left = right;
			right = tmp;
		}
		
		static Component fromLine (String line) {
			Component component = new Component();
			String[] tokens = line.split("/");
			component.left = Integer.valueOf(tokens[0]);
			component.right = Integer.valueOf(tokens[1]);
			return component;
		}
	}
}
