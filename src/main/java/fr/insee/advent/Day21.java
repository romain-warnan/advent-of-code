package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day21 {

	static final Pattern biRule = Pattern.compile("([.#]{2})/([.#]{2}) => ([.#]{3})/([.#]{3})/([.#]{3})");
	static final Pattern triRule = Pattern.compile("([.#]{3})/([.#]{3})/([.#]{3}) => ([.#]{4})/([.#]{4})/([.#]{4})/([.#]{4})");
	/**
	 * .#.
	 * ..#
	 * ###
	 */
	static final boolean[][] originalImage = new boolean[][] { { false, true, false }, { false, false, true }, { true, true, true }
	};

	public static void main(String[] args) throws IOException {
		Day21 day = new Day21();
		System.out.println("Day21");
		System.out.println("1. " + day.ex(5, "src/main/resources/input21"));
		System.out.println("2. " + day.ex(18, "src/main/resources/input21"));
	}

	public long ex(int iterations, String path) throws IOException {
		List<Rule> rules = Files
			.readAllLines(Paths.get(path))
			.stream()
			.map(Rule::fromLine)
			.collect(Collectors.toList());

		boolean[][] image = originalImage;
		for (int n = 0;  n < iterations; n++) {
			image = this.applyRules(image, rules);
			
		}
		return countPixels(image);
	}

	static long countPixels(boolean[][] pattern) {
		long count = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[0].length; j++) {
				if(pattern[i][j]) {
					count ++;
				}
			}
		}
		return count;
	}
	
	boolean[][] applyRules(boolean[][] image, List<Rule> rules) {
		int length = image.length;
		if (image.length % 2 == 0) {
			return applyBiRule(image, rules, length);
		}
		return applyTriRule(image, rules, length);
	}

	boolean[][] applyBiRule(boolean[][] image, List<Rule> rules, int length) {
		int d = length / 2;
		boolean[][] enhancedImage = new boolean[3 * d][3 * d];
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				boolean[][] pattern = new boolean[2][2];
				for (int r = 0; r < 2; r++) {
					for (int c = 0; c < 2; c++) {
						pattern[r][c] = image[2 * i + r][2 * j + c];
					}	
				}
				Rule rule = findMatchingRule(pattern, rules);
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						enhancedImage[3 * i + r][3 * j + c] = rule.out[r][c];
					}	
				}
			}
		}
		return enhancedImage;
	}
	
	boolean[][] applyTriRule(boolean[][] image, List<Rule> rules, int length) {
		int d = length / 3;
		boolean[][] enhancedImage = new boolean[4 * d][4 * d];
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				boolean[][] pattern = new boolean[3][3];
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						pattern[r][c] = image[3 * i + r][3 * j + c];
					}	
				}
				Rule rule = findMatchingRule(pattern, rules);
				for (int r = 0; r < 4; r++) {
					for (int c = 0; c < 4; c++) {
						enhancedImage[4 * i + r][4 * j + c] = rule.out[r][c];
					}	
				}
			}
		}
		return enhancedImage;
	}

	Rule findMatchingRule(boolean[][] pattern, List<Rule> rules) {
		return rules
			.stream()
			.filter(rule -> rule.matchesPattern(pattern))
			.findFirst()
			.get();
	}

	static abstract class Rule {

		boolean[][][] ins;
		boolean[][] in;
		boolean[][] out;
		
		/**
		 * '.' = 46
		 * '#' = 35
		 */
		static boolean[] fromGroup(String group) {
			Boolean[] boxed = group
				.chars()
				.boxed()
				.map(n -> (n == 35))
				.toArray(Boolean[]::new);
			boolean[] pattern = new boolean[boxed.length];
			for (int index = 0; index < boxed.length; index++) {
				pattern[index] = boxed[index].booleanValue();
			}
			boxed = null;
			return pattern;
		}

		static Rule fromLine(String line) {
			Matcher triMatcher = triRule.matcher(line);
			if (triMatcher.matches()) {
				return TriRule.from(triMatcher);
			}
			Matcher biMatcher = biRule.matcher(line);
			if (biMatcher.matches()) {
				return BiRule.from(biMatcher);
			}
			return null;
		}

		static boolean[][] rotate90(boolean[][] in) {
			final int M = in.length;
			final int N = in[0].length;
			boolean[][] rotation = new boolean[N][M];
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					rotation[c][M - 1 - r] = in[r][c];
				}
			}
			return rotation;
		}

		static boolean[][] rotate180(boolean[][] in) {
			final int M = in.length;
			final int N = in[0].length;
			boolean[][] rotation = new boolean[N][M];
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					rotation[M - r - 1][N - c - 1] = in[r][c];
				}
			}
			return rotation;
		}

		static boolean[][] rotate270(boolean[][] in) {
			final int M = in.length;
			final int N = in[0].length;
			boolean[][] rotation = new boolean[N][M];
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					rotation[N - c - 1][r] = in[r][c];
				}
			}
			return rotation;
		}

		static boolean[][] flipV(boolean[][] in) {
			final int M = in.length;
			final int N = in[0].length;
			boolean[][] rotation = new boolean[N][M];
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					rotation[r][M - 1 - c] = in[r][c];
				}
			}
			return rotation;
		}

		static boolean[][] flipH(boolean[][] in) {
			final int M = in.length;
			final int N = in[0].length;
			boolean[][] rotation = new boolean[N][M];
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					rotation[N - 1 - r][c] = in[r][c];
				}
			}
			return rotation;
		}

		static void computeIns(Rule instance) {
			instance.ins[0] = instance.in;
			instance.ins[1] = flipV(instance.in); 
			instance.ins[2] = flipH(instance.in);
			
			instance.ins[3] = rotate90(instance.in); 
			instance.ins[4] = rotate180(instance.in); 
			instance.ins[5] = rotate270(instance.in); 
			
			instance.ins[6] = rotate90(flipV(instance.in)); 
			instance.ins[7] = rotate180(flipV(instance.in)); 
			instance.ins[8] = rotate270(flipV(instance.in));
			
			instance.ins[9] = rotate90(flipH(instance.in)); 
			instance.ins[10] = rotate180(flipH(instance.in)); 
			instance.ins[11] = rotate270(flipH(instance.in));
		}

		boolean matchesPattern(boolean[][] pattern) {
				if (pattern.length == 2 && this instanceof TriRule) {
					return false;
				}
				if (pattern.length == 3 && this instanceof BiRule) {
					return false;
				}
			for (int n = 0; n < 12; n++) {
				if (matchesOnePattern(pattern, ins[n])) {
					return true;
				}
			}
			return false;
		}
		
		boolean matchesOnePattern(boolean[][] pattern, boolean[][] in) {
			for (int r = 0; r < in.length; r++) {
				for (int c = 0; c < in[0].length; c++) {
					if (in[r][c] ^ pattern[r][c]) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < in.length; i++) {
				for (int j = 0; j < in[0].length; j++) {
					builder.append(in[i][j] ? '#' : '.');
				}
				builder.append('\n');
			}
			return builder.toString();
		}
	}

	static class BiRule extends Rule {

		BiRule() {
			in = new boolean[2][2];
			out = new boolean[3][3];
			ins = new boolean[12][2][2];
		}

		static BiRule from(Matcher matcher) {
			BiRule instance = new BiRule();
			instance.in[0] = fromGroup(matcher.group(1));
			instance.in[1] = fromGroup(matcher.group(2));

			instance.out[0] = fromGroup(matcher.group(3));
			instance.out[1] = fromGroup(matcher.group(4));
			instance.out[2] = fromGroup(matcher.group(5));
			
			computeIns(instance); 
			return instance;
		}
	}

	static class TriRule extends Rule {

		TriRule() {
			in = new boolean[3][3];
			out = new boolean[4][4];
			ins = new boolean[12][3][3];
		}

		static TriRule from(Matcher matcher) {
			TriRule instance = new TriRule();
			instance.in[0] = fromGroup(matcher.group(1));
			instance.in[1] = fromGroup(matcher.group(2));
			instance.in[2] = fromGroup(matcher.group(3));

			instance.out[0] = fromGroup(matcher.group(4));
			instance.out[1] = fromGroup(matcher.group(5));
			instance.out[2] = fromGroup(matcher.group(6));
			instance.out[3] = fromGroup(matcher.group(7));

			computeIns(instance); 
			return instance;
		}
	}
}
