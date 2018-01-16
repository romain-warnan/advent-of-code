package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day09 {

	public static void main(String[] args) throws IOException {
		Day09 day = new Day09();
		System.out.println("Day9");
		String input = Files.newBufferedReader(Paths.get("src/main/resources/input9")).readLine();
		System.out.println("1. " + day.ex1(input));
		System.out.println("2. " + day.ex2(input));
	}
	
	public long ex1(String input) {
		return this.score(input);
	}
	
	public long ex2(String input) {
		return this.garbages(input).stream()
			.mapToLong(this::nonCanceledChars)
			.sum();
	}
	
	private long score(String input) {
		input = this.removeAllGarbage(input);
		input = input.replace(",", "");
		char[] chars = input.toCharArray();
		int depth = 0, score = 0;
		for (int n = 0; n < chars.length; n++) {
			char ch = chars[n];
			if(ch == '{') {
				depth ++;
				score = score + depth; 
			}
			else if (ch == '}') {
				depth --;
			}
		}
		return score;
	}
	
	
	
	private String removeAllGarbage(String input) {
		String output = this.removeFirstGarbage(input);
		if(output.equals(input)) {
			return output;
		}
		return this.removeAllGarbage(output);
	}
	
	private String removeFirstGarbage(String input) {
		char[] chars = input.toCharArray();
		int beginIndex = -1, endIndex = -1;
		for (int n = 0; n < chars.length; n++) {
			char ch = chars[n];
			if(ch == '!') {
				n ++;
			}
			else {
				if(beginIndex < 0 && ch == '<') {
					beginIndex = n;
				}
				if(beginIndex >= 0 && ch == '>') {
					endIndex = n + 1;
					break;
				}
			}
		}
		if(endIndex >= 0) {
    		return input.substring(0, beginIndex) + input.substring(endIndex);
		}
		return input;
	}
	
	private List<String> garbages(String input) {
		List<String> garbages = new ArrayList<>();
		char[] chars = input.toCharArray();
		int beginIndex = -1, endIndex = -1;
		for (int n = 0; n < chars.length; n++) {
			char ch = chars[n];
			if(ch == '!') {
				n ++;
			}
			else {
				if(beginIndex < 0 && ch == '<') {
					beginIndex = n;
				}
				if(beginIndex >= 0 && ch == '>') {
					endIndex = n + 1;
				}
			}
			if (endIndex > 0) {
				garbages.add(input.substring(beginIndex, endIndex));
				beginIndex = -1;
				endIndex = -1;
			}
		}
		
		return garbages;
	}
	
	private long nonCanceledChars(String garbage) {
		garbage = garbage.substring(1, garbage.length() - 1);
		char[] chars = garbage.toCharArray();
		int score = 0;
		for (int n = 0; n < chars.length; n++) {
			char ch = chars[n];
			if(ch == '!') {
				n ++;
			}
			else {
				score ++;
			}
		}
		return score;
	}
}
