package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day22_2 {

	public static void main(String[] args) throws IOException {
		Day22_2 day = new Day22_2();
		System.out.println("day22");
		System.out.println("2. " + day.ex2(10_000_000, "src/main/resources/input22"));
	}

	public long ex2(int iterations, String path) throws IOException {
		List<char[]> map = map(path);
		int mapWidth = mapWidth(map);
		int mapHeight = mapHeight(map);
		State[][] nodes = nodes(map, mapWidth, mapHeight);
		VirusCarrier carrier = new VirusCarrier();
		carrier.x = mapWidth / 2;
		carrier.y =  mapHeight / 2;
		for (int n = 0; n < iterations; n++) {
			nodes = carrier.burst(nodes);
		}
		return carrier.infections;
	}

	private State[][] nodes(List<char[]> map, int mapWidth, int mapHeight) {
		State[][] states = new State[mapWidth][mapHeight];
		for (int x = 0; x < mapWidth; x++) {
			for (int y = 0; y < mapHeight; y++) {
				char c = map.get(y)[x];
				if (c == '#') {
					states[y][x] = State.INFECTED;
				}
				else {
					states[y][x] = State.CLEAN;
				}
			}	
		}
		return states;
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
	
	enum State {
		CLEAN, WEAKENED, INFECTED, FLAGGED
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
		
		State[][] burst(State[][] nodes) {
			nodes = resizeMap(nodes);
			State state = Optional.ofNullable(nodes[y][x]).orElse(State.CLEAN);
			nodes[y][x] = changeStateAndWay(state);
			move();
			return nodes;
		}

		private State[][] resizeMap(State[][] nodes) {
			int height = nodes.length;
			int width = nodes[0].length;
			if (y < 0) {
				y ++;
				State[][] copyOfNodes = new State[height + 1][width];
				for (int i = 0; i < height; i++) {
					copyOfNodes[i + 1] = nodes[i];
				}
				return copyOfNodes;
			}
			if (y >= height) {
				State[][] copyOfNodes = new State[height + 1][width];
				for (int i = 0; i < height; i++) {
					copyOfNodes[i] = nodes[i];
				}
				return copyOfNodes;
			}
			if (x < 0) {
				x ++;
				State[][] copyOfNodes = new State[height][width + 1];
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						copyOfNodes[i][j + 1] = nodes[i][j];
					}
				}
				return copyOfNodes;
			}
			if (x >= width) {
				State[][] copyOfNodes = new State[height][width + 1];
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						copyOfNodes[i][j] = nodes[i][j];
					}
				}
				return copyOfNodes;
			}
			return nodes;
		}
		
		private State changeStateAndWay(State state) {
			switch (state) {
				case CLEAN:
					turnLeft();
					return State.WEAKENED;
				case WEAKENED:
					infections ++;
					return State.INFECTED;
				case INFECTED:
					turnRight();
					return State.FLAGGED;
				case FLAGGED:
					goBack();
					return State.CLEAN;
			}
			return null;
		}

		private void move() {
			switch (way) {
				case NORTH:
					y--;
					break;
				case EAST:
					x++;
					break;
				case SOUTH:
					y++;
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
