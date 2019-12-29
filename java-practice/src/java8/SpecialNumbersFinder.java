package java8;

import java.math.BigInteger;

public class SpecialNumbersFinder {

	public static void main(String[] args) {

		 specialNumbersGenerator(4);

	}

	private static void specialNumbersGenerator(int numberOfSpecialNumbers) {

		
		
	}

	private static BigInteger factorial(BigInteger number) {

		if (number.equals(BigInteger.ONE)) {
			return number;
		} else {
			return number.multiply(factorial(number.subtract(BigInteger.ONE)));
		}
	}

}
