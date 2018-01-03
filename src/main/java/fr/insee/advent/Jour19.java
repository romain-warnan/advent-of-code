package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jour19 {

	public static void main(String[] args) throws IOException {
		Jour19 jour = new Jour19();
		System.out.println("Jour19");
		System.out.println("1. " + jour.ex1("src/main/resources/input19"));
	}

	public String ex1(String path) throws IOException {
		char[][] map = readMapFrom(path);
		return null;
	}
	
	static char[][] readMapFrom(String path) throws IOException {
		List<char[]> lines = Files.readAllLines(Paths.get(path)).stream()
				.map(String::toCharArray)
				.collect(Collectors.toList());
		int width = lines.get(0).length;
		int height = lines.size();
		char[][] map = new char[width][height];
		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				map[i][j] = lines.get(j)[i];
			}	
		}
		lines = null;
		return map;
	}
}
