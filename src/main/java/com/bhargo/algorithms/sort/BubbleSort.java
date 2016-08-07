package com.bhargo.algorithms.sort;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by barya on 8/7/16.
 */
public class BubbleSort {

    public static void doBubbleSort(List<String> list) {
        List<Integer> integerList = list.stream().map(n -> Integer.valueOf(n)).collect(Collectors.toList());
        int temp;
        for(int i =integerList.size()-1;i>1;i--) {
            for(int j =0;j<i;j++) {
                if(integerList.get(j) > integerList.get(j+1)) {
                    temp = integerList.get(j+1);
                    integerList.set(j+1,integerList.get(j));
                    integerList.set(j,temp);
                }
            }
        }
        integerList.forEach(System.out::println);
    }
}
