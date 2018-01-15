package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Jour24 {

	static int id = 0;
	
	public static void main(String[] args) throws IOException {
		Jour24 jour = new Jour24();
		System.out.println("Jour24");
		System.out.println("1. " + jour.ex1("src/main/resources/input24"));
		System.out.println("2. " + jour.ex2("src/main/resources/input24"));
	}
	
	public long ex1(String path) throws IOException {
		List<Component> components = this.components(path);
		return this.strength(0, 0, components);
	}
	
	public long ex2(String path) throws IOException {
		List<Component> components = this.components(path);
		return this.bridge(new Bridge(0, 0), 0, components).strength;
	}
	
	List<Component> nextComponents(int previous, List<Component> unused) {
		return unused.stream()
			.filter(c -> c.canFollow(previous))
			.collect(Collectors.toList());
	}
	
	int strength (int strength, int previous, List<Component> unused) {
		List<Component> nextComponents = this.nextComponents(previous, unused);
		if (nextComponents.isEmpty()) {
			return strength;
		}
		int maxStrength = 0;
		for (Component next : nextComponents) {
			List<Component> rest = new ArrayList<>(unused);
			rest.remove(next);
			int newStrength = this.strength(strength + next.strength(), next.otherSide(previous), rest);
			maxStrength = Math.max(newStrength, maxStrength);
		}
		return maxStrength;
	}
	
	private List<Component> components(String path) throws IOException {
		return Files.readAllLines(Paths.get(path))
			.stream()
			.map(Component::fromLine)
			.collect(Collectors.toList());
	}
	
	Bridge bridge(Bridge bridge, int previous, List<Component> unused) {
		List<Component> nextComponents = this.nextComponents(previous, unused);
		if (nextComponents.isEmpty()) {
			return bridge;
		}
		Bridge maxBridge = new Bridge(0, 0);
		for (Component next : nextComponents) {
			List<Component> rest = new ArrayList<>(unused);
			rest.remove(next);
			Bridge newBridge = new Bridge(bridge.length + 1, bridge.strength + next.strength());
			Bridge nextStrength = this.bridge(newBridge, next.otherSide(previous), rest);
			maxBridge = Collections.max(Arrays.asList(nextStrength, maxBridge));
		}
		return maxBridge;
	}
	
	static class Bridge implements Comparable<Bridge> {
		int length, strength;

		public Bridge(int length, int strength) {
			this.length = length;
			this.strength = strength;
		}

		@Override
		public int compareTo(Bridge other) {
			if (this.length == other.length) {
				return this.strength - other.strength;
			}
			return this.length - other.length;
		}
	}
	
	static class Component {
		int id, left, right;
		
		boolean canFollow(int right) {
			return this.left == right || this.right == right;
		}
		
		int otherSide(int previous) {
			return left == previous ? right : left;
		}
		
		int strength() {
			return left + right;
		}
		
		static Component fromLine (String line) {
			Component component = new Component();
			String[] tokens = line.split("/");
			Jour24.id ++;
			component.id = Jour24.id;
			component.left = Integer.valueOf(tokens[0]);
			component.right = Integer.valueOf(tokens[1]);
			return component;
		}
		
		@Override
		public String toString() {
			return left + "/" + right;
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object object) {
			if (this == object)
				return true;
			if (object == null)
				return false;
			if (getClass() != object.getClass())
				return false;
			Component other = (Component) object;
			if (id != other.id)
				return false;
			return true;
		}
	}
}
