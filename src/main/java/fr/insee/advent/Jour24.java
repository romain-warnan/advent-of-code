package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;

public class Jour24 {

	static int id = 0;
	
	public static void main(String[] args) throws IOException {
		Jour24 jour = new Jour24();
		System.out.println("Jour24");
		System.out.println("1. " + jour.ex1("src/main/resources/input24"));
	}
	
	public long ex1(String path) throws IOException {
		List<Component> components = components(path);
		return 0L;
	}
	
	List<Component> nextComponents(Component previous, List<Component> unused) {
		return unused.stream()
			.filter(c -> c.canFollow(previous))
			.collect(Collectors.toList());
	}
	
	void strength (int strength, Component previous, List<Component> unused) {
		List<Component> nextComponents = nextComponents(previous, unused);
		
		Iterator<Component> iterator = unused.iterator();
		while (iterator.hasNext()) {
			Component component = (Component) iterator.next();
			if (component.canFollow(previous)) {
				iterator.remove();
				strength = strength + component.strength();
			}
		}
	}
	
	private List<Component> components(String path) throws IOException {
		return Files.readAllLines(Paths.get(path))
			.stream()
			.map(Component::fromLine)
			.collect(Collectors.toList());
	}
	
//	List<List<Component>> powerList(List<Component> originalList) {
//		List<List<Component>> lists = new ArrayList<>();
//		if (originalList.isEmpty()) {
//			lists.add(new ArrayList<>());
//			return lists;
//		}
//		Component head = originalList.get(0);
//		List<Component> rest = originalList.subList(1, originalList.size());
//		for (List<Component> list : powerList(rest)) {
//			List<Component> newList = new ArrayList<>();
//			newList.add(head);
//			for (int index = 0; index < rest.size(); index ++) {
//				Component component = rest.get(index);
//				if (true || component.canFollow(newList.get(index))) {
//					newList.add(component);
//				}
//				else {
//					break;
//				}
//			}
//			lists.add(newList);
//			lists.add(list);
//		}
//		return lists;
//	}
	
	static class Component {
		int id, left, right;
		
		void flip() {
			int tmp = left;
			left = right;
			right = tmp;
		}
		
		boolean canFollow(Component other) {
			if (this.left == other.right) {
				return true;
			}
			this.flip();
			return this.left == other.right;
		}
		
		int strength() {
			return left + right;
		}
		
		static Component fromLine (String line) {
			Component component = new Component();
			String[] tokens = line.split("/");
			component.left = Integer.valueOf(tokens[0]);
			component.right = Integer.valueOf(tokens[1]);
			component.id = Jour24.id;
			Jour24.id ++;
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
