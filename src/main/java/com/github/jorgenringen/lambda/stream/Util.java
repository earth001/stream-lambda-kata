package com.github.jorgenringen.lambda.stream;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public static List<String> mapToUppercase(List<String> input) {
        input.replaceAll(String::toUpperCase);
        return input;
        // return input.stream()
        // .map(String::toUpperCase)
        // .collect(Collectors.toList());
    }

    public static List<String> removeElementsWithMoreThanFourCharacters(List<String> input) {
        return input.stream()
            .filter(s -> s.length() < 4)
            .collect(Collectors.toList());
    }

    public static List<String> sortStrings(List<String> input) {
        return input.stream()
            .sorted()
            .collect(Collectors.toList());
    }

    public static List<Integer> sortIntegers(List<String> input) {
        return input.stream()
            .map(Integer::valueOf)
            .sorted()
            .collect(Collectors.toList());
    }

    public static List<Integer> sortIntegersDescending(List<String> input) {
        return input.stream()
            .map(Integer::valueOf)
            .sorted((n1, n2) -> n2 - n1)
            .collect(Collectors.toList());
        // return input.stream()
        // .map(Integer::valueOf)
        // .sorted(Comparator.<Integer> reverseOrder())
        // .collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> numbers) {
        return numbers.stream()
            .mapToInt(n -> n)
            // .mapToInt(Integer::intValue)
            .sum();
    }

    public static List<String> flattenToSingleCollection(List<List<String>> input) {
        // List<String> out = new ArrayList<>();
        // input.forEach(i -> out.addAll(i));
        // return out;
        return input.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    public static String separateNamesByComma(List<Person> input) {
        return input.stream()
            .map(Person::getName)
            .collect(Collectors.joining(", ", "Names: ", "."));
    }

    public static String findNameOfOldestPerson(List<Person> input) {
        return input.stream()
            .max(Comparator.comparingInt(Person::getAge))
            .get()
            .getName();
    }

    public static List<String> filterPeopleLessThan18YearsOld(List<Person> input) {
        return input.stream()
            .filter(p -> p.getAge() < 18)
            .map(Person::getName)
            .collect(Collectors.toList());
    }

    public static IntSummaryStatistics getSummaryStatisticsForAge(List<Person> input) {
        return input.stream()
            .mapToInt(Person::getAge)
            .summaryStatistics();
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> input) {
        return input.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() > 18));
    }

    public static Map<String, List<Person>> partitionByNationality(List<Person> input) {
        return input.stream()
            .collect(Collectors.groupingBy(Person::getCountry));
    }

}
