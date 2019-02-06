package com.bhargo.dcc;

import java.util.*;

public class DailyCodingChallenge {

    /**
     * Given a list of numbers and a number k
     * return whether any two numbers from the list add up to k.
     *
     * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
     * 5th Feb 2019
     * @param arr
     * @param value
     * @return
     */
    public static Boolean addUp(int[] arr, int value) {
        Map<Boolean, List<Integer>> map = new HashMap<>();
        for (int i: arr) {
            if (map.containsKey(true)) {
                map.get(true).add(i);
            } else {
                map.put(true, new ArrayList<>());
            }
        }

        for (int i =0; i < arr.length; i++) {
            if (checkSum(value, map, arr, i))
                return true;
        }
        return false;
    }

    private static boolean checkSum(int value, Map<Boolean, List<Integer>> map, int[] arr, int index) {
        if (map.get(true).contains(value)) {
            return true;
        }
        if (value < arr[index]) {
            return false;
        }
        boolean result = checkSum(value-arr[index], map, arr, ++index);
        if (result) {
            System.out.println(value + " " + (value-arr[index]));
        }
        return result;
    }

    /**
     * Given an array of integers, return a new array
     * such that each element at index i of the new array
     * is the product of all the numbers in the original array
     * except the one at i.
     *
     * For example, if our input was [1, 2, 3, 4, 5],
     * the expected output would be [120, 60, 40, 30, 24].
     * If our input was [3, 2, 1],
     * the expected output would be [2, 3, 6]
     */
    public static int[] arrayMul(int[] arr) {
        recArrayMul(arr, 1,0);
        return arr;
    }

    private static int totalProd;

    private static void recArrayMul(int[] arr, int value, int index) {
        if (index == arr.length) {
            totalProd = value;
            return;
        }
        recArrayMul(arr, value*arr[index], ++index);
        arr[index-1] = totalProd/arr[index-1];
    }
}
