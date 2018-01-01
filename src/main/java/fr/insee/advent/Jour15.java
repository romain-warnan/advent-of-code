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
}
