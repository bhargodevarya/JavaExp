package com.bhargo.dynamic;

import java.util.*;

/**
 * Created by barya on 9/3/16.
 */
public class DynamicDemo {

    static Map<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void longestIncreasingSubsequence(List<Integer> list) {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(list.get(0));

        for(int i =1; i<list.size(); i++) {
            if(data.get(data.size() -1) <= list.get(i)) {
                data.add(list.get(i));
            }
        }
        System.out.println(data.size());
        data.forEach(n -> System.out.print(n + " "));
    }

    public static void longestSubSequenceSum(List<Integer> list) {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(list.get(0));
        int maxSum = sumOfList(data);
        map.put(0,data);
        int maxSumKey = 0;

        for(int i =1; i<list.size(); i++) {
            if(sumOfList(map.get(i-1)) + list.get(i) >= sumOfList(map.get(i-1))) {
                ArrayList<Integer> in = new ArrayList<>();
                map.get(i-1).stream().filter(n -> n>= 0).forEach(m -> in.add(m));
                in.add(list.get(i));
                map.put(i,in);
                if(sumOfList(map.get(i)) > maxSum) {
                    maxSum = sumOfList(map.get(i));
                    maxSumKey = i;
                }
            } else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(list.get(i));
                map.put(i,l);
            }
        }
        //map.entrySet().stream().forEach(n -> System.out.println(n.getKey() + " " + n.getValue()));
        System.out.println("The subsequence with highest sum is " + map.get(maxSumKey));
        System.out.println("The highest sum is " + maxSum);

    }

    private static int sumOfList(List<Integer> list) {
        int sum =0;
        if(list != null) {
            for (Integer in: list) {
                sum = sum + in;
            }
        }
        return sum;
    }

    public static int fibinacci(int n) {
        int[] arr = new int[n+1];
        arr[1] = 0;
        arr[2] = arr[3] = 1;
        return fib(n, arr);
    }

    private static int fib(int n, int[] arr) {
        if (n == 0) {
            return 0;
        }
        if (n == 1||n == 2|| n == 3) {
            return arr[n];
        }
        if (arr[n] != 0) {
            System.out.println("found for " + n);
            return arr[n];
        } else {
            System.out.println("not found for " + n);
        }

        arr[n] = fib(n-1,arr) + fib(n-2, arr);
        return arr[n];
    }

}
