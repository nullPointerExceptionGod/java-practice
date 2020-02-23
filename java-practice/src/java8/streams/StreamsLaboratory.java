package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsLaboratory {

    public static void main(String[] args) {

    }

    public static void stream1() {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        strings
            .stream()
            .filter(s -> s.startsWith("c"))
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);
    }

    public static void sequentialStreamArraysAsList() {
        Arrays.asList("a1", "a2", "a3")
            .stream()
            .findFirst()
            .ifPresent(System.out::println);
    }

    public static void sequentialStreamStreamOf() {
        Stream.of("a1", "a2", "a3")
            .findFirst()
            .ifPresent(System.out::println);
    }

    public static void sequentialStreamIntStream() {
        IntStream.range(1, 4)
            .forEach(System.out::println);
    }

    public static void sequentialStreamIntStreamAverage() {
        Arrays.stream(new int[] {1, 2, 3})
            .map(n -> 2 * n + 1)
            .average()
            .ifPresent(System.out::println);
    }

    public static void sequentialStreamIntStreamMapToInt() {
        Stream.of("a1", "a2", "a3")
            .map(s -> s.substring(1))
            .mapToInt(Integer::valueOf)
            .max()
            .ifPresent(System.out::println);
    }

    public static void sequentialStreamIntStreamMapToObj() {
        IntStream.range(1, 4)
            .mapToObj(i -> "a" + i)
            .forEach(System.out::println);
    }



}
