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
//		for (int row = 0; row < map.length; row ++) {
//			for (int col = 0; col < map[0].length; col ++) {
//				System.out.print(map[row][col]);
//			}
//			System.out.println();
//		}
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
		
		static boolean isHorizontal(char c) {
			return c != '|' && c != ' ' && c != '+';
		}
		
		static boolean isVertical(char c) {
			return c != '-' && c != ' ' && c != '+';
		}
		
		static boolean isLetter(char c) {
			return c != '|' && c != '-' && c != ' ' && c != '+';
		}
		
		void changeWay(char[][] map) {
			switch (way) {
    			case NORTH:
    			case SOUTH:
    				if (isHorizontal(map[row][col - 1])) {
    					way = Way.WEST;
    				}
    				else if (isHorizontal(map[row][col + 1])) {
    					way = Way.EAST;
    				}
    				else {
    					endOfPath = true;
    				}
    				break;
    			case EAST:
    			case WEST:
    				if (isVertical(map[row - 1][col])) {
    					way = Way.NORTH;
    				}
    				else if (isVertical(map[row + 1][col])) {
    					way = Way.SOUTH;
    				}
    				else {
    					endOfPath = true;
    				}
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
			if(map[row][col] == '+') {
				changeWay(map);
			}
			move();
			collectLetter(map);
		}
		
		void collectLetter(char[][] map) {
			if(isLetter(map[row][col])) {
				letters.append(map[row][col]);
			}
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
