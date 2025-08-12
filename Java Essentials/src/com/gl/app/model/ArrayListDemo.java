package com.gl.app.model;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<>();

        // Add at least 5 elements
        numbers.add(42);
        numbers.add(7);
        numbers.add(19);
        numbers.add(3);
        numbers.add(28);

        // Print all elements
        System.out.println("Original list: " + numbers);

        // Remove element at index 2 (3rd element)
        numbers.remove(2);

        // Print list after removal
        System.out.println("After removing element at index 2: " + numbers);

        // Check if element 28 exists
        boolean contains28 = numbers.contains(28);
        System.out.println("Contains 28? " + contains28);

        // Sort the ArrayList in ascending order
        Collections.sort(numbers);

        // Print the sorted ArrayList
        System.out.println("Sorted list: " + numbers);
    }
}
