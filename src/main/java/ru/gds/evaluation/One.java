package ru.gds.evaluation;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Write a Java method that takes an array of "sets" of String objects, and determines whether all sets in the array are equivalent.
 * <p>
 * Each "set" in the input array is represented as an array of String objects, in no particular order, and possibly containing duplicates.
 * Nevertheless, when determining whether two of these "sets" are equivalent, you should disregard order and duplicates. For example,
 * the sets represented by these arrays are all equivalent:
 * <p>
 * {"a", "b"} {"b", "a"} {"a", "b", "a"}
 * <p>
 * The signature for your method should be:
 * <p>
 * public static boolean allStringSetsIdentical(String[ ][ ] sets)
 * <p>
 * Examples of the method in operation:
 * <p>
 * allStringSetsIdentical(new String[][] {{"a","b"},{"b","b","a"},{"b","a"}}) returns true
 * <p>
 * allStringSetsIdentical(new String[][] {{"a","b"},{"a"},{"b"}}) returns false
 */
public class One {

    public static void main(String[] args) {

        String[][] inputEmpty = {};
        String[][] inputEmptyOfEmpty = {{}, {}, {}};
        String[][] inputOk = {{"a", "b"}, {"b", "b", "a"}, {"b", "a"}};
        String[][] inputNok = {{"a", "b"}, {"a"}, {"b"}};


        assert allStringSetsIdentical(inputEmpty);
        assert allStringSetsIdentical(inputEmptyOfEmpty);
        assert allStringSetsIdentical(inputOk);
        assert !allStringSetsIdentical(inputNok);
    }


    /**
     * @param sets input data
     * @return true if all sets are equal, false otherwise.
     */
    public static boolean allStringSetsIdentical(String[][] sets) {
        return 1L >= Arrays.stream(sets)
                .parallel()
                .map(s -> Arrays.stream(s).collect(Collectors.<String>toSet()))
                .sequential()
                .unordered()
                .distinct()
                .count();
    }
}
