package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Jour11 {

	public static void main(String[] args) throws IOException {
		Jour11 jour = new Jour11();
		System.out.println("Jour11");
		String input = Files.newBufferedReader(Paths.get("src/main/resources/input11")).readLine();
		System.out.println("1. " + jour.ex1(input));
		System.out.println("2. " + jour.ex2(input));
	}
	
	public long ex1(String input) {
		return this.distance(input.split(","));
	}
	
	public long ex2(String input) {
		long max = 0;
		String[] steps = input.split(",");
		for (int n = 1; n < steps.length; n ++) {
			max = Math.max(max, this.distance(Arrays.copyOfRange(steps, 0, n)));
		}
		return max;
	}
	
	private long distance(String[] steps) {
		Map<String, Long> map = Arrays.stream(steps)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		long s = Optional.ofNullable(map.get("s")).orElse(0L) -  Optional.ofNullable(map.get("n")).orElse(0L);
		long se = Optional.ofNullable(map.get("se")).orElse(0L) -  Optional.ofNullable(map.get("nw")).orElse(0L);
		long sw = Optional.ofNullable(map.get("sw")).orElse(0L) -  Optional.ofNullable(map.get("ne")).orElse(0L);
		long n = 0, ne = 0, nw = 0;
		
		if (s < 0) {
			n = 0 - s;
			s = 0;
		}
		if (se < 0) {
			nw = 0 - se;
			se = 0;
		}
		if (sw < 0) {
			ne = 0 - sw;
			sw = 0;
		}
		
		if (n > 0) {
			if (ne > 0) {
				// Cas n, ne, nw
				return n + Math.max(ne, nw);
			}
			else {
				// Cas n, se, sw
				Math.max(n, Math.max(se, sw));
			}
		}
		else {
			if (ne > 0) {
				// Cas s, ne, nw
				return Math.max(s, Math.max(ne, nw));
			}
			else {
				// Cas s, se, sw
				return s + Math.max(se, sw);
			}
		}
		return 0L;
	}
}
