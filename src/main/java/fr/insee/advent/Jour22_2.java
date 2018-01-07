package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.insee.advent.Jour22_2.Node.State;

public class Jour22_2 {

	public static void main(String[] args) throws IOException {
		Jour22_2 jour = new Jour22_2();
		System.out.println("Jour22");
		System.out.println("2. " + jour.ex2(10_000_000, "src/main/resources/input22"));
	}

	public long ex2(int iterations, String path) throws IOException {
		List<char[]> map = map(path);
		int mapWidth = mapWidth(map);
		int mapHeight = mapHeight(map);
		List<Node> nodes = nodes(map, mapWidth, mapHeight);
		VirusCarrier carrier = new VirusCarrier();
		for (int n = 0; n < iterations; n++) {
			if (n % 10_000 == 0) {
				System.out.println(n);
			}
			carrier.burst(nodes);
		}
		return carrier.infections;
	}

	private List<Node> nodes(List<char[]> map, int mapWidth, int mapHeight) {
		List<Node> nodes = new ArrayList<>();
		for (int x = 0; x < mapWidth; x++) {
			for (int y = 0; y < mapHeight; y++) {
				int xNode = x - (mapWidth / 2);
				int yNode = (mapHeight / 2) - y;
				char c = map.get(y)[x];
				if (c == '#') {
					nodes.add(Node.newInfectedNode(xNode, yNode));
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
		State state;
		
		private Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		static Node newCleanNode(int x, int y) {
			Node node = new Node(x, y);
			node.state = State.CLEAN;
			return node;
		}
		
		static Node newInfectedNode(int x, int y) {
			Node node = new Node(x, y);
			node.state = State.INFECTED;
			return node;
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
			changeStateAndWay(node, nodes);
			move();
		}
		
		private void changeStateAndWay(Node node, List<Node> nodes) {
			switch (node.state) {
				case CLEAN:
					turnLeft();
					node.state = State.WEAKENED;
					break;
				case WEAKENED:
					node.state = State.INFECTED;
					infections ++;
					break;
				case INFECTED:
					turnRight();
					node.state = State.FLAGGED;
					break;
				case FLAGGED:
					goBack();
					nodes.remove(node);
					break;
			}
		}
		
		private Node currentNode(List<Node> nodes) {
			return nodes.stream()
				.filter(n -> n.x == this.x && n.y == this.y)
				.findFirst()
				.orElseGet(() -> {
					Node node = Node.newCleanNode(x, y);
					nodes.add(node);
					return node;
				});
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
		
		private void goBack() {
			switch (way) {
				case NORTH:
					way = Way.SOUTH;
					break;
				case EAST:
					way = Way.WEST;
					break;
				case SOUTH:
					way = Way.NORTH;
					break;
				case WEST:
					way = Way.EAST;
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
