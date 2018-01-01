package fr.insee.advent;


public class Jour15 {

	private static final int
		generatorForA = 16_807,
		generatorForB = 48_271,
		divider = 2_147_483_647,
		bitmask = 0b1111_1111_1111_1111
	;
	
	public static void main(String[] args) throws Exception {
		Jour15 jour = new Jour15();
		System.out.println("Jour15");
		System.out.println("1. " + jour.ex1(40_000_000, 116, 299));
		System.out.println("2. " + jour.ex2(5_000_000, 116, 299));
	}
	
	public long ex1(long numberOfPairs, long seedForA, long seedForB) {
		long result = 0;
		long a = nextA(seedForA);
		long b = nextB(seedForB);
		for (long n = 0; n <= numberOfPairs; n ++) {
			if (((a ^ b) & bitmask) == 0) {
				result ++;
			}
			a = nextA(a);
			b = nextB(b);
		}
		return result;
	}
	
	private static long nextA(long a) {
		return (a * generatorForA) % divider; 
	}
	
	private static long nextB(long b) {
		return (b * generatorForB) % divider; 
	}

	public long ex2(long numberOfPairs, long seedForA, long seedForB) {
		long result = 0;
		long a = nextWithCriteriaA(seedForA);
		long b = nextWithCriteriaB(seedForB);
		for (long n = 0; n < numberOfPairs; n ++) {
			if (((a ^ b) & bitmask) == 0) {
				result ++;
			}
			a = nextWithCriteriaA(a);
			b = nextWithCriteriaB(b);
		}
		return result;
	}

	private static long nextWithCriteriaA(long a) {
		long nextA = nextA(a);
		if (nextA % 4 == 0) {
			return nextA;
		}
		return nextWithCriteriaA(nextA);
	}
	
	private static long nextWithCriteriaB(long b) {
		long nextB = nextB(b);
		if (nextB % 8 == 0) {
			return nextB;
		}
		return nextWithCriteriaB(nextB);
	}
}
