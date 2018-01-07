package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Jour22_2 {

	public static void main(String[] args) throws IOException {
		Jour22_2 jour = new Jour22_2();
		System.out.println("Jour22");
		System.out.println("1. " + jour.ex1(10_000, "src/main/resources/input22"));
	}

	public long ex1(int iterations, String path) throws IOException {
		List<char[]> map = map(path);
		int mapWidth = mapWidth(map);
		int mapHeight = mapHeight(map);
		List<Node> nodes = nodes(map, mapWidth, mapHeight);
		VirusCarrier carrier = new VirusCarrier();
		for (int n = 0; n < iterations; n++) {
			carrier.burst(nodes);
		}
		return carrier.infections;
	}

	private List<Node> nodes(List<char[]> map, int mapWidth, int mapHeight) {
		List<Node> nodes = new ArrayList<>();
		for (int x = 0; x < mapWidth; x++) {
			for (int y = 0; y < mapHeight; y++) {
				char c = map.get(y)[x];
				if (c == '#') {
					nodes.add(new Node(x - (mapWidth / 2), (mapHeight / 2) - y));
				}
			}	
		}
		return nodes;
	}
	
	List<char[]> map(String path) throws IOException {
		return Files.readAllLines(Paths.get(path))
			.stream()
			.map(String::toCharArray)
			.collect(Collectors.toList());
	}
	
	int mapWidth(List<char[]> map) {
		return map.get(0).length;
	}
	
	int mapHeight(List<char[]> map) {
		return map.size();
	}
	
	static class Node {
		int x, y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		enum State {
			CLEAN, WEAKENED, INFECTED, FLAGGED
		}
	}
	
	static class VirusCarrier {
		int x, y;
		Way way;
		long infections;
		
		VirusCarrier() {
			x = 0;
			y = 0;
			way = Way.NORTH;
			infections = 0;
		}
		
		enum Way {
			NORTH, EAST, SOUTH, WEST
		}
		
		void burst(List<Node> nodes) {
			Node node = this.currentNode(nodes);
			turn(node);
			infectOrClean(node, nodes);
			move();
		}
		
		private void infectOrClean(Node node, List<Node> nodes) {
			if (node == null) {
				infect(nodes);
			}
			else {
				clean(node, nodes);
			}
		}
		
		private void infect(List<Node> nodes) {
			nodes.add(new Node(x, y));
			infections ++;
		}
		
		private void clean(Node node, List<Node> nodes) {
			nodes.remove(node);
		}
		
		private Node currentNode(List<Node> nodes) {
			return nodes.stream()
				.filter(node -> node.x == this.x && node.y == this.y)
				.findFirst()
				.orElse(null);
		}
		
		private void turn (Node node) {
			if (node == null) {
				turnLeft();
			}
			else {
				turnRight();
			}
		}

		private void move() {
			switch (way) {
				case NORTH:
					y++;
					break;
				case EAST:
					x++;
					break;
				case SOUTH:
					y--;
					break;
				case WEST:
					x--;
					break;
			}
		}
		
		private void turnRight() {
			switch (way) {
				case NORTH:
					way = Way.EAST;
					break;
				case EAST:
					way = Way.SOUTH;
					break;
				case SOUTH:
					way = Way.WEST;
					break;
				case WEST:
					way = Way.NORTH;
					break;
			}
		}
		
		private void turnLeft() {
			switch (way) {
				case NORTH:
					way = Way.WEST;
					break;
				case EAST:
					way = Way.NORTH;
					break;
				case SOUTH:
					way = Way.EAST;
					break;
				case WEST:
					way = Way.SOUTH;
					break;
			}
		}
	}
}
