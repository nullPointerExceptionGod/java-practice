package java8;

import java.math.BigInteger;

public class SpecialNumbersFinder {

	public static void main(String[] args) {

		specialNumbersGenerator(5);

	}

	// TODO: Needs optimization
	private static void specialNumbersGenerator(int numberOfSpecialNumbers) {

		BigInteger number = new BigInteger("1");

		BigInteger digitFactorial = null;

		BigInteger sum = new BigInteger("0");

		while (numberOfSpecialNumbers > 0) {

			String[] digits = number.toString().split("");
			
			for (int i = 0; i < digits.length; i++) {

				digitFactorial = factorial(new BigInteger(digits[i].toString()));

				sum = sum.add(digitFactorial);

			}

			if (number.equals(sum)) {
				System.out.println(number);
				numberOfSpecialNumbers--;
			}

			sum = new BigInteger("0");
			number = number.add(BigInteger.ONE);
			
		}

	}

	private static BigInteger factorial(BigInteger number) {

		if (number.compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		}
		
		if (number.equals(BigInteger.ONE)) {
			return number;
		} else {
			return number.multiply(factorial(number.subtract(BigInteger.ONE)));
		}
	}

}
