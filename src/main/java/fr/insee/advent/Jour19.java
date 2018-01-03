package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Jour19 {

	public static void main(String[] args) throws IOException {
		Jour19 jour = new Jour19();
		System.out.println("Jour19");
		System.out.println("1. " + jour.ex1("src/main/resources/input19"));
	}

	public String ex1(String input) throws IOException {
		char[][] map = readMapFrom(input);
		Path path = new Path(findEntryPoint(map));
		while (!path.endOfPath) {
			path.nextStep(map);
		}
		return path.letters.toString();
	}
	
	enum Way {
		NORTH, SOUTH, EAST, WEST
	}
	
	static class Path {
		
		int row, col;
		Way way;
		StringBuilder letters;
		boolean endOfPath = false;
		
		Path(int entryPoint) {
			this.row = 0;
			this.col = entryPoint;
			this.way = Way.SOUTH;
			this.letters = new StringBuilder();
		}
		
		static boolean isOnPath(char c) {
			return Character.isLetter(c) || c == '-' || c == '|';
		}
		
		void changeWay(char[][] map) {
			switch (way) {
    			case NORTH:
    			case SOUTH:
    				way = isOnPath(map[row][col - 1]) ? Way.WEST : Way.EAST;
    				break;
    			case EAST:
    			case WEST:
    				way = isOnPath(map[row - 1][col]) ? Way.NORTH : Way.SOUTH;
    				break;
			}
		}
		
		void move() {
			switch (way) {
    			case NORTH:
    				row --;
    				break;
    			case SOUTH:
    				row ++;
    				break;
    			case EAST:
    				col ++;
    				break;
    			case WEST:
    				col --;
    				break;
    		}
		}
		
		void nextStep(char[][] map) {
			if(shouldChangeWay(map)) {
				changeWay(map);
			}
			
			move();
			collectLetter(map);
			
			if(!shouldChangeWay(map)) {
				checkIfEndOfPath(map);
			}
		}
		
		boolean shouldChangeWay(char[][] map) {
			return map[row][col] == '+';
		}
		
		void collectLetter(char[][] map) {
			if(Character.isLetter(map[row][col])) {
				letters.append(map[row][col]);
			}
		}
		
		void checkIfEndOfPath(char[][] map) {
			char c = ' ';
			switch (way) {
    			case NORTH:
    				c = map[row - 1][col];
    				break;
    			case SOUTH:
    				c = map[row + 1][col];
    				break;
    			case EAST:
    				c = map[row][col + 1];
    				break;
    			case WEST:
    				c = map[row][col - 1];
    				break;
			}
			endOfPath = !(c == '+' || isOnPath(c));
		}
	}
	
	static char[][] readMapFrom(String path) throws IOException {
		List<char[]> lines = Files.readAllLines(Paths.get(path)).stream()
				.map(String::toCharArray)
				.collect(Collectors.toList());
		int rows = lines.size();
		int cols = lines.get(0).length;
		char[][] map = new char[rows][cols];
		for (int row = 0; row < rows; row ++) {
			for (int col = 0; col < cols; col ++) {
				map[row][col] = lines.get(row)[col];
			}	
		}
		lines = null;
		return map;
	}
	
	static int findEntryPoint(char[][] map) {
		for (int index = 0; index < map[0].length; index ++) {
			if(map[0][index] == '|') {
				return index;
			}
		}
		return -1;
	}
}
