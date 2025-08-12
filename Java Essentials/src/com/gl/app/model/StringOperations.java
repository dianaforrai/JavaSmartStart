package com.gl.app.model;

import java.util.Scanner;

public class StringOperations {

    // Method to count vowels
    public static int countVowels(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // Method to count consonants
    public static int countConsonants(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c) && vowels.indexOf(c) == -1) {
                count++;
            }
        }
        return count;
    }

    // Method to replace all occurrences of a character with another
    public static String replaceChar(String str, char oldChar, char newChar) {
        return str.replace(oldChar, newChar);
    }

    // Method to check if string is palindrome
    public static boolean isPalindrome(String str) {
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        // Count vowels and consonants
        int vowels = countVowels(input);
        int consonants = countConsonants(input);

        System.out.println("Number of vowels: " + vowels);
        System.out.println("Number of consonants: " + consonants);

        // Replace characters
        System.out.println("Enter the character to replace:");
        char oldChar = scanner.next().charAt(0);

        System.out.println("Enter the replacement character:");
        char newChar = scanner.next().charAt(0);

        String replacedString = replaceChar(input, oldChar, newChar);
        System.out.println("String after replacement: " + replacedString);

        // Check palindrome
        boolean palindrome = isPalindrome(input);
        System.out.println("Is the string a palindrome? " + (palindrome ? "Yes" : "No"));

        scanner.close();
    }
}
