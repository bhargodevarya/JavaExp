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

}
