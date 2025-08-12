package com.gl.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    // Swap method (returns a new Pair with swapped elements)
    public Pair<U, T> swap() {
        return new Pair<>(second, first);
    }
}

public class PairDemo {

    // Method to swap elements of a Pair (returns a new swapped Pair)
    public static <T, U> Pair<U, T> swapPair(Pair<T, U> pair) {
        return pair.swap();
    }

    // Method to print elements of a Pair
    public static <T, U> void printPair(Pair<T, U> pair) {
        System.out.println("First: " + pair.getFirst());
        System.out.println("Second: " + pair.getSecond());
    }

    public static void main(String[] args) {
        // Pair with String and Integer
        Pair<String, Integer> pair1 = new Pair<>("Hello", 42);
        printPair(pair1);
        System.out.println("After swap:");
        Pair<Integer, String> swappedPair1 = swapPair(pair1);
        printPair(swappedPair1);

        System.out.println("-----");

        // Pair with two ArrayLists
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Apple");
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(100);
        Pair<ArrayList<String>, ArrayList<Integer>> pair2 = new Pair<>(list1, list2);
        printPair(pair2);
        System.out.println("After swap:");
        Pair<ArrayList<Integer>, ArrayList<String>> swappedPair2 = swapPair(pair2);
        printPair(swappedPair2);

        System.out.println("-----");

        // Pair with HashMap and HashSet
        HashMap<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        HashSet<String> set = new HashSet<>();
        set.add("A");
        Pair<HashMap<String, Integer>, HashSet<String>> pair3 = new Pair<>(map, set);
        printPair(pair3);
        System.out.println("After swap:");
        Pair<HashSet<String>, HashMap<String, Integer>> swappedPair3 = swapPair(pair3);
        printPair(swappedPair3);
    }
}
