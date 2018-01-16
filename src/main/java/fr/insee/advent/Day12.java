package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class Day12 {

	public static void main(String[] args) throws IOException {
		Day12 day = new Day12();
		System.out.println("Day12");
		System.out.println("1. " + day.ex1("src/main/resources/input12"));
		System.out.println("2. " + day.ex2("src/main/resources/input12"));
	}
	
	public long ex1(String path) throws IOException {
		List<Pipe> pipes = pipes(path);
		Map<Integer, Set<Integer>> groups = pipes.stream()
			.collect(Collectors.toMap(pipe -> pipe.in, pipe -> this.descendants(pipe, pipes)));
		return groups.get(0).size();
	}
	
	public long ex2(String path) throws IOException {
		List<Pipe> pipes = pipes(path);
		return pipes.stream()
			.map(pipe -> this.descendants(pipe, pipes))
			.map(Group::new)
			.collect(Collectors.toSet())
			.size() ;
	}

	private static List<Pipe> pipes(String path) throws IOException {
		List<Pipe> pipes = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Pipe::fromLine)
			.collect(Collectors.toList());
		return pipes;
	}
	
	public Set<Integer> descendants(Pipe pipe, List<Pipe> pipes) {
		return this.descendants(pipe, new TreeSet<>(), pipes);
	}
	
	public Set<Integer> descendants (Pipe pipe, Set<Integer> descendants, List<Pipe> pipes) {
		if (!descendants.contains(pipe.in)) {
			descendants.add(pipe.in);
			for (Pipe child : pipe.children(pipes)) {
				this.descendants(child, descendants, pipes);
			}
		}
		return descendants;
	}
	
	public static class Group {
		
		Set<Integer> programs;

		public Group(Set<Integer> programs) {
			super();
			this.programs = programs;
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(programs);
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof Group) {
				Group other = (Group) object;
				return CollectionUtils
					.disjunction(this.programs, other.programs)
					.size() == 0;
			}
			return false;
		}
		
		
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
				.skip(1)
				.toArray();
			return pipe;
		}
		
		public static Pipe fromId(int id, List<Pipe> pipes) {
			return pipes.stream()
				.filter(pipe -> pipe.in == id)
				.findFirst()
				.orElse(null);
		}
		
		public List<Pipe> children(List<Pipe> pipes) {
			return Arrays.stream(this.outs)
				.mapToObj(out -> Pipe.fromId(out, pipes))
				.collect(Collectors.toList());
		}
		
	}
}
