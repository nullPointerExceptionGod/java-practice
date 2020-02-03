package java8;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Laboratory {

	public static void main(String[] args) {

		parallelStreams();

	}

	/*
	 * DEFAULT METHODS
	 */
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

		// TODO: try to understand this output -> java8.Laboratory$1
		System.out.println(formula.getClass());
	}

	/*
	 * LAMBDA EXPRESSIONS
	 */
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
		
		List<Double> specialNumbers = Arrays.asList(2d, 1d, 40585d, 145d);

		// shortest way
		Collections.sort(specialNumbers, (specialNumber1, specialNumber2) -> specialNumber1.compareTo(specialNumber2));
		
		for(Double specialNumber : specialNumbers) {
			System.out.println(specialNumber);
		}
		
	}

	/*
	 * FUNCTIONAL INTERFACES
	 */
	public static void functionalInterfaces() {
		
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		
		Integer converted = converter.convert("123");
		
		System.out.println(converted);
	}
	
	/*
	 * METHOD AND CONSTRUCTOR REFERENCES
	 */
	public static void staticMethodReference() {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println("Converted: " + converted);
		System.out.println("Type of converted: " + converted.getClass());
	}

	public static void objectMethodReference() {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String converted = converter.convert("Java");
		System.out.println(converted);
	}

	public static void constructorMethodReference() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
	}

	/*
	 * LAMBDA SCOPES
	 */
	public static void lambdaScopes() {
		int num = 1; // it's implicitly final (cant change it), but does not require the final keyword
		Converter<Integer, String> converter = (from) -> String.valueOf(from + num);
		System.out.println(converter.convert(2));
	}

	/*
	 * PREDICATES (boolean-valued functions of one argument. The interface contains various default methods for composing predicates to complex logical terms (and, or, negate))
	 */
	public static void predicates() {
		Predicate<String> predicate = (s) -> s.length() > 0;

		predicate.test("hello"); 			// true
		predicate.negate().test("hello"); 	// false

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotempty = isEmpty.negate();
	}

	/*
	 * FUNCTIONS (accept one argument and produce a result.
	 * Default methods can be used to chain multiple functions together (compose, andThen))
	 */
	public static void functions() {
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		backToString.apply("123"); // "123"
	}

	/*
	 * SUPPLIERS (produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments)
	 */
	public static void suppliers() {
		Supplier<Person> personSupplier = Person::new;
		Person p = personSupplier.get();
	}

	/*
	 * CONSUMERS (operations to be performed on a single input argument)
	 */
	public static void consumers () {
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
		greeter.accept(new Person("Peter", "Parker"));
	}

	/*
	 * COMPARATORS
	 */
	public static void comparators() {
		Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		Person p1 = new Person("Marcus", "Holloway");
		Person p2 = new Person("Sitara", "Dhawan");

		System.out.println(comparator.compare(p1, p2));       		// < 0
		System.out.println(comparator.reversed().compare(p1, p2));	// > 0
	}

	/*
	 * OPTIONALS (Optionals are not functional interfaces,
	 * instead it's a nifty utility to prevent NullPointerException)
	 */
	public static void optionals() {
		Optional<String> optional = Optional.of("bam");

		optional.isPresent();        			// true
		optional.get();              			// "bam"
		optional.orElse("fallback");	// "bam"

		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
	}

	/*
	 * STREAMS (a sequence of elements on which one or more operations can be performed)
	 * stream operations are either intermediate or terminal.
	 * While terminal operations return a result of a certain type,
	 * intermediate operations return the stream itself so you can chain multiple method calls in a row
	 */
	public static void sequentialStreams() {
		List<String> strings = new ArrayList<>();
		strings.add("ddd2");
		strings.add("aaa2");
		strings.add("bbb1");
		strings.add("aaa1");
		strings.add("bbb3");
		strings.add("ccc");
		strings.add("bbb2");
		strings.add("ddd1");

		filter(strings);
		sorted(strings);
		map(strings);
		match(strings);
		count(strings);
		reduce(strings);
	}

	/*
	 * FILTER (accepts a predicate to filter all elements of the stream)
	 * It is an intermediate operation
	 */
	public static void filter(List<String> strings) {
		System.out.println("\n########## FILTER ##########");
		strings
			.stream()
			.filter((s) -> s.startsWith("a"))
			.forEach(System.out::println);
		System.out.println("####################\n");
	}

	/*
	 * SORTED (operation which returns a sorted view of the stream.
	 * The elements are sorted in natural order unless you pass a custom Comparator)
	 * It is an intermediate operation.
	 *
	 * Does only create a sorted view of the stream without manipulating the ordering of the backed collection,
	 * the ordering of the collection is untouched:
	 */
	public static void sorted(List<String> strings) {
		System.out.println("\n########## SORTED ##########");
		strings
			.stream()
			.sorted()
			.filter((s) -> s.startsWith("a"))
			.forEach(System.out::println);
		System.out.println("####################\n");
	}

	/*
	 * MAP (converts each element into another object via the given function)
	 * It is an intermediate operation
	 */
	public static void map(List<String> strings) {
		System.out.println("\n########## MAP ##########");
		strings
			.stream()
			.map(String::toUpperCase)
			.sorted((a, b) -> b.compareTo(a))
			.forEach(System.out::println);
		System.out.println("####################\n");
	}

	/*
	 * MATCH (various matching operations can be used to check whether a certain predicate matches the stream)
	 * All of those operations are terminal and return a boolean result
	 */
	public static void match(List<String> strings) {
		System.out.println("\n########## MATCH ##########");
		boolean anyStartWithA =
			strings
				.stream()
				.anyMatch((s) -> s.startsWith("a"));
		System.out.println(anyStartWithA); // true

		boolean allStartWithA =
			strings
				.stream()
				.allMatch((s) -> s.startsWith("a"));
		System.out.println(allStartWithA); // false

		boolean noneStartWithZ =
			strings
				.stream()
				.noneMatch((s) -> s.startsWith("z"));
		System.out.println(noneStartWithZ); // true

		System.out.println("####################\n");
	}

	/*
	 * COUNT (a terminal operation returning the number of elements in the stream as a long)
	 */
	public static void count(List<String> strings) {
		System.out.println("\n########## COUNT ##########");
		long startWithB =
			strings
				.stream()
				.filter((s) -> s.startsWith("b"))
				.count();
		System.out.println(startWithB); // 3
		System.out.println("####################\n");
	}

	/*
	 * REDUCE (a terminal operation performs a reduction on the elements of the stream with the given function)
	 * The result is an Optional holding the reduced value
	 */
	public static void reduce(List<String> strings) {
		System.out.println("\n########## REDUCE ##########");
		Optional<String> reduced =
			strings
				.stream()
				.sorted()
				.reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		System.out.println("####################\n");
	}

	/*
	 * PARALLEL STREAMS
	 *
	 * Operations on sequential streams are performed on a single thread
	 * while operations on parallel streams are performed concurrent on multiple threads
	 */
	public static void parallelStreams() {
		int max = 1000000;
		List<Integer> values = new ArrayList<>(max);
		for (int i = 0 ; i < max; i++) {
			int randomNumber = (int)(Math.random() * ((1000 - 5) + 1)) + 5;
			values.add(randomNumber);
		}

		// Sequential sort
		long t0 = System.nanoTime();
		Optional<Integer> count = values.stream().sorted().reduce(Integer::sum);
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));

		// Parallel sort
		long t2 = System.nanoTime();
		Optional<Integer> count2 = values.parallelStream().sorted().reduce(Integer::sum);
		System.out.println(count2);
		long t3 = System.nanoTime();
		long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
		System.out.println(String.format("parallel sort took: %d ms", millis2));
	}



}
