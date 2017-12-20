package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Jour9 {

	public static void main(String[] args) throws IOException {
		Jour9 jour = new Jour9();
		System.out.println("Jour9");
		String input = Files.newBufferedReader(Paths.get("src/main/resources/input9")).readLine();
		System.out.println("1. " + jour.ex1(input));
		
	}
	
	public long ex1(String input) {
		this.removeAllGarbage(input);
		return -1;
	}
	
	public String removeAllGarbage(String input) {
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
				if(ch == '<') {
					beginIndex = n;
				}
				if(beginIndex >= 0 && ch == '>') {
					endIndex = n + 1;
					break;
				}
			}
		}
		if(endIndex >= 0) {
    		String garbage = input.substring(beginIndex, endIndex);
    		return input.replace(garbage, "");
		}
		return input;
	}
}
