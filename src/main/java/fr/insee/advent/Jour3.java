package fr.insee.advent;

import java.io.IOException;

public class Jour3 {

    public static void main(String[] args) throws IOException {
        Jour3 jour = new Jour3();
        System.out.println(jour.rank(26));
        System.out.println("Jour3");
        System.out.println("1. " + jour.ex1(1));
    }
	
	public int ex1(int p) {
		int n = this.rank(p);
		Point point = new Point(n);
		int start = 8 * n + 2;
		for(int k = start; k <= p; k ++) {
			
		}
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
	
	static class Point {
		int x, y;
		
		public Point(int n) {
			x = n;
			y = 1 - n;
		}
		
		public void up() {
			y = y + 1;
		}
		
		public void down() {
			y = y - 1;
		}
		
		public void right() {
			x = x + 1;
		}
		
		public void left() {
			x = x - 1;
		}
		
		public int manhattan() {
			return Math.abs(x) + Math.abs(y);
		}
	}
}
