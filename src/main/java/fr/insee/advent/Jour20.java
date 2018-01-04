package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Jour20 {

	static final Pattern pattern = Pattern.compile("p=<(-?\\d+),(-?\\d+),(-?\\d+)>, v=<(-?\\d+),(-?\\d+),(-?\\d+)>, a=<(-?\\d+),(-?\\d+),(-?\\d+)>");
	
	public static void main(String[] args) throws IOException {
		Jour20 jour = new Jour20();
		System.out.println("Jour20");
		System.out.println("1. " + jour.ex1("src/main/resources/input20"));
		System.out.println("2. " + jour.ex2("src/main/resources/input20"));
	}

	public int ex1(String path) throws IOException {
		List<Particule> particules = particules(path);
		for (int n = 0; n < 10_000; n ++) {
			particules.stream().forEach(Particule::move);
		}
		long minimumDistance = particules.stream()
			.mapToLong(Particule::manhattan)
			.min()
			.getAsLong();
		for (int index = 0; index < particules.size(); index ++) {
			Particule particule = particules.get(index); 
			if (particule.manhattan() == minimumDistance) {
				return particule.id;
			}
		}
		return -1;
	}

	public int ex2(String path) throws IOException {
		List<Particule> particules = particules(path);
		for (int n = 0; n < 10_000; n ++) {
			List<Integer> particulesToRemove = particulesToRemove(particules);
			particules = removeCollisions(particules, particulesToRemove);
			particules.stream().forEach(Particule::move);
		}
		return particules.size();
	}
	
	static List<Particule> particules(String path) throws IOException {
		List<Particule> particules = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Particule::fromLine)
			.collect(Collectors.toList());
		IntStream.range(0, particules.size())
			.forEach(n -> particules.get(n).id = n);
		return particules;
	}
	
	static List<Integer> particulesToRemove(List<Particule> particules) {
		return particules.stream()
			.filter(p -> p.collideWithOneOf(particules))
			.map(p -> p.id)
			.collect(Collectors.toList());
	}
	
	static List<Particule> removeCollisions(List<Particule> particules, List<Integer> particulesToRemove) {
		return particules.stream()
			.filter(p -> !particulesToRemove.contains(p.id))
			.collect(Collectors.toList());
	}
	
	static class Particule {
		int id;
		Position p;
		Velocity v;
		Acceleration a;
		
		static Particule fromLine (String line) {
			Particule particule = new Particule();
			particule.p = new Position();
			particule.v = new Velocity();
			particule.a = new Acceleration();
			
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				particule.p.x = Long.valueOf(matcher.group(1));
				particule.p.y = Long.valueOf(matcher.group(2));
				particule.p.z = Long.valueOf(matcher.group(3));

				particule.v.x = Long.valueOf(matcher.group(4));
				particule.v.y = Long.valueOf(matcher.group(5));
				particule.v.z = Long.valueOf(matcher.group(6));
				
				particule.a.x = Long.valueOf(matcher.group(7));
				particule.a.y = Long.valueOf(matcher.group(8));
				particule.a.z = Long.valueOf(matcher.group(9));
			}
			return particule;
		}
		
		void move() {
			// velocity
			v.x = v.x + a.x;
			v.y = v.y + a.y;
			v.z = v.z + a.z;
			
			// position
			p.x = p.x + v.x;
			p.y = p.y + v.y;
			p.z = p.z + v.z;
		}
		
		long manhattan() {
			return Math.abs(p.x) + Math.abs(p.y) + Math.abs(p.z);
		}
		
		boolean collideWithOther(Particule other) {
			return
				this.p.x == other.p.x &&
				this.p.y == other.p.y && 
				this.p.z == other.p.z &&
				this.id != other.id
			;
		}
		
		boolean collideWithOneOf(List<Particule> particules) {
			return particules.stream()
				.anyMatch(this::collideWithOther);
		}
	}
	
	static class Position {
		long x, y, z;
	}
	
	static class Velocity {
		long x, y, z;
	}
	
	static class Acceleration {
		long x, y, z;
	}

}
