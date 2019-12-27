package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Laboratory {

	public static void main(String[] args) {
		
		lambdaExpressions();
		
	}
	
	private static void defaultMethod() {
		// anonymous object
		Formula formula = new Formula() {
			
			@Override
			public double calculate(double num) {
				// sqrt is a default method in the same interface
				return (sqrt(num) + 2);
			}
		};
		
		System.out.println(formula.calculate(9));
		System.out.println(formula.sqrt(16));
		
		//TODO: try to understand this output -> java8.Laboratory$1
		System.out.println(formula.getClass());
	}
	
	private static void lambdaExpressions() {
		
		// Java 7 -> with anonymous comparator
		System.out.println("###### Java 7 #####");
		
		List<String> machines = Arrays.asList("Watcher", "Strider", "Stalker", "Thunderjaw");
		
		System.out.println("#### Before sorting ####");
		for(String machine : machines) {
			System.out.println(machine);
		}
		
		Collections.sort(machines, new Comparator<String>() {

			@Override
			public int compare(String name1, String name2) {
				return name1.compareTo(name2);
			}
		});
		
		System.out.println("\n#### After sorting ####");
		for(String machine : machines) {
			System.out.println(machine);
		}
		
		// Java 8
		System.out.println("\n###### Java 8 #####");
		
		List<Integer> numbers = Arrays.asList(1, 40, 6, 90);
		
		// Short way
		Collections.sort(numbers, (Integer number1, Integer number2) -> {
			return number1.compareTo(number2);
		});
		
		for(Integer number : numbers) {
			System.out.println(number);
		}
		
		System.out.println("\n##############");
		
		List<Integer> primeNumbers = Arrays.asList(7, 11 ,5 ,2);
		
		// Shorter way
		Collections.sort(primeNumbers, (Integer prime1, Integer prime2) -> prime1.compareTo(prime2));
		
		for (Integer prime : primeNumbers) {
			System.out.println(prime);
		}
		
		System.out.println("\n##############");
		
		//List<Double> specialNumbers = Arrays.asList(a)
	}

}
