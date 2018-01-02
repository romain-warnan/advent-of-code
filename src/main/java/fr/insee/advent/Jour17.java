package fr.insee.advent;

import java.util.ArrayList;
import java.util.List;

public class Jour17 {
	
	public static void main(String[] args) throws Exception {
		Jour17 jour = new Jour17();
		System.out.println("Jour17");
		System.out.println("1. " + jour.ex1(314));
		System.out.println("2. " + jour.ex2(314));
	}
	
	public int ex1(int steps) {
		List<Integer> buffer = new ArrayList<>(2018);
		buffer.add(0);
		int currentPosition = 0;
		for (int n = 1; n < 2018; n ++) {
			currentPosition = ((currentPosition + steps) % buffer.size()) + 1;
			buffer.add(currentPosition, n);
		}
		int indexOf2017 = buffer.indexOf(2017);
		return buffer.get((indexOf2017 + 1) % buffer.size());
	}
	
	public int ex2(int steps) {
		int currentPosition = 0;
		int indexOfZero = 0;
		int valueAfterZero = 0;
		for (int n = 1; n < 50_000_000; n ++) {
			currentPosition = ((currentPosition + steps) % n) + 1;
			if(currentPosition == (indexOfZero + 1)) {
				valueAfterZero = n;
			}
			else if (currentPosition <= indexOfZero) {
				indexOfZero ++;
			}
		}
		return valueAfterZero;
	}
}
