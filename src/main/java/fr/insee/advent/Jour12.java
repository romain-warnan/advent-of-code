package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jour12 {

	public static void main(String[] args) throws IOException {
		Jour12 jour = new Jour12();
		System.out.println("Jour12");
		System.out.println("1. " + jour.ex1("src/main/java/input11"));
	}
	
	public long ex1(String path) throws IOException {
		List<Pipe> pipes = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Pipe::fromLine)
			.collect(Collectors.toList());
		
		return 0L;
	}
	
	public static class Pipe {
		
		int in;
		int[] outs;
		
		public static Pipe fromLine(String line) {
			Pipe pipe = new Pipe();
			line = line.replaceAll(" +", "");
			String[] tokens = line.split("(<->|,)");
			pipe.in = Integer.valueOf(tokens[0]);
			pipe.outs = Arrays.stream(tokens)
				.mapToInt(Integer::valueOf)
				.toArray();
			return pipe;
		}
	}
}
