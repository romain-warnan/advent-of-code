package fr.insee.advent;

import java.io.IOException;
import java.util.stream.IntStream;

public class Jour3 {

    public static void main(String[] args) throws IOException {
        Jour3 jour = new Jour3();
        System.out.println("Jour3");
        System.out.println("1. " + jour.ex1(325489));
    }
	
	public long ex1(long p) {
		if (p == 1) {
			return 0;
		}
		long n = this.rank(p);
		Point point = new Point(n);
		long start = this.start(n + 1);
		for(long k = start + 1; k <= p; k ++) {
			if (k < start + 2 * n) {
				point.up();
			}
			else if (k < start + 4 * n) {
				point.left();
			}
			else if (k < start + 6 * n) {
				point.down();
			}
			else {
				point.right();
			}
		}
		return point.manhattan();
	}
	
	private long rank(long p) {
		if (p == 1) {
			return 0;
		}
		long n = 1;
		while (this.start(n) > p || p > this.end(n)) {
			n ++;
		}
		return n - 1;
	}
	
	private long start(long n) {
		return IntStream.iterate(0, k -> k + 8)
		.limit(n - 1)
		.sum() + 2;
	}
	
	private long end(long n) {
		return IntStream.iterate(0, k -> k + 8)
		.limit(n)
		.sum() + 1;
	}
	
	static class Point {
		long x, y;
		
		public Point(long n) {
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
		
		public long manhattan() {
			return Math.abs(x) + Math.abs(y);
		}
	}
}
