package fr.insee.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day03 {

	public static void main(String[] args) throws IOException {
		Day03 day = new Day03();
		System.out.println("Day3");
		System.out.println("1. " + day.ex1(325489));
		System.out.println("2. " + day.ex2(325489));
	}

	public long ex1(long input) {
		if (input == 1) {
			return 0;
		}
		return this.point(input).manhattan();
	}

	public long ex2(long input) {
		if (input == 1) {
			return 1;
		}
		List<Point> points = new ArrayList<>();
		points.add(this.firstPoint());
		for (int rank = 1; rank < input; rank++) {
			for (long index = this.start(rank); index <= this.end(rank); index++) {
				Point point = this.point(index);
				point.value = this.computeValue(point, points);
				points.add(point);
				if (point.value >= input) {
					return point.value;
				}
			}
		}
		return 0;
	}

	private Point point(long index) {
		long n = this.rank(index);
		Point point = new Point(n);
		long start = this.start(n + 1);
		for (long k = start + 1; k <= index; k++) {
			if (k < start + 2 * n) {
				point.up();
			}
			else
				if (k < start + 4 * n) {
					point.left();
				}
				else
					if (k < start + 6 * n) {
						point.down();
					}
					else {
						point.right();
					}
		}
		return point;
	}

	private long computeValue(Point point, List<Point> points) {
		return points
			.stream()
			.filter(point::touches)
			.mapToLong(Point::value)
			.sum();
	}

	private Point firstPoint() {
		Point point = new Point(0);
		point.value = 1L;
		point.x = 0L;
		point.y = 0L;
		return point;
	}

	private long rank(long p) {
		if (p == 1) {
			return 0;
		}
		long n = 1;
		while (this.start(n) > p || p > this.end(n)) {
			n++;
		}
		return n - 1;
	}

	private long start(long n) {
		return IntStream
			.iterate(0, k -> k + 8)
			.limit(n - 1)
			.sum() + 2;
	}

	private long end(long n) {
		return IntStream
			.iterate(0, k -> k + 8)
			.limit(n)
			.sum() + 1;
	}

	static class Point {

		long x, y, value;

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

		public long value() {
			return this.value;
		}

		public long manhattan() {
			return Math.abs(x) + Math.abs(y);
		}

		public boolean touches(Point other) {
			return Math.abs(this.x - other.x) <= 1 && Math.abs(this.y - other.y) <= 1;
		}
	}
}
