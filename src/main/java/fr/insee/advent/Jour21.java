package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Jour21 {

	static final Pattern biRule = Pattern.compile("([.#]{2})/([.#]{2}) => ([.#]{3})/([.#]{3})/([.#]{3})");
	static final Pattern triRule = Pattern.compile("([.#]{3})/([.#]{3})/([.#]{3}) => ([.#]{4})/([.#]{4})/([.#]{4})/([.#]{4})");
	/**
	 * .#.
	 * ..#
	 * ###
	 */
	static final boolean[][] firstPattern = new boolean[][] { { false, true, false }, { false, false, true }, { true, true, true }
	};

	public static void main(String[] args) throws IOException {
		Jour21 jour = new Jour21();
		System.out.println("Jour21");
		System.out.println("1. " + jour.ex1(5, "src/main/resources/input21"));
	}

	public int ex1(int iterations, String path) throws IOException {
		List<Rule> rules = Files
			.readAllLines(Paths.get(path))
			.stream()
			.map(Rule::fromLine)
			.collect(Collectors.toList());
		return -1;
	}

	static abstract class Rule {

		boolean[][] in;
		boolean[][] out;

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

		boolean matchesExactPattern(boolean[][] pattern) {
			for (int r = 0; r < in.length; r++) {
				for (int c = 0; c < in[0].length; c++) {
					if (in[r][c] ^ pattern[r][c]) {
						return false;
					}
				}
			}
			return true;
		}
		
		boolean matchesPattern(boolean[][] pattern) {
			return
				matchesExactPattern(pattern) ||
				matchesExactPattern(rotate90(pattern)) ||
				matchesExactPattern(rotate180(pattern)) ||
				matchesExactPattern(rotate270(pattern)) ||
				matchesExactPattern(flipH(pattern)) ||
				matchesExactPattern(flipV(pattern))
			;
		}
		
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
		}

		static BiRule from(Matcher matcher) {
			BiRule instance = new BiRule();
			instance.in[0] = fromGroup(matcher.group(1));
			instance.in[1] = fromGroup(matcher.group(2));

			instance.out[0] = fromGroup(matcher.group(3));
			instance.out[1] = fromGroup(matcher.group(4));
			instance.out[2] = fromGroup(matcher.group(5));
			return instance;
		}
	}

	static class TriRule extends Rule {

		TriRule() {
			in = new boolean[3][3];
			out = new boolean[4][4];
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
			return instance;
		}
	}
}
