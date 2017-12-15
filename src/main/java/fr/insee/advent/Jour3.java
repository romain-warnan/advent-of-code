package fr.insee.advent;

import java.io.IOException;

public class Jour3 {

    public static void main(String[] args) throws IOException {
        Jour3 jour = new Jour3();
        System.out.println("Jour3");
        System.out.println("1. " + jour.ex1(1));
    }
	
	public int ex1(int n) {
		return 0;
	}
	
	private int rank(int p) {
		if(p == 1) {
			return 0;
		}
		if(p < 10) {
			return 1;
		}
		int n = 1;
		while ((8 * n + 2) > p || (16 * n + 9) < p) {
			n ++;
		}
		return n + 1;
	}
}
