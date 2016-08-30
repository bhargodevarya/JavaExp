package com.bhargo.algorithms.search;

import com.bhargo.util.Util;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by barya on 8/7/16.
 */
public class BinarySearch {

    /**
     * Performs binary search, only works for sorted list/array
     * @param fileName
     * @throws IOException
     */
    public static void doBinarySeach(String fileName, Integer numToFind) throws IOException {
        List<Integer> data = Util.readFileAsListOfString(fileName," ").stream()
                .map(n -> Integer.valueOf(n)).collect(Collectors.toList());
        int lowerLimit=0,upperLimit=data.size()-1,pivot,i=0;
        while(lowerLimit <= upperLimit) {
            System.out.println("iteration " + (++i));
            pivot=(lowerLimit+upperLimit)/2;
            if(numToFind < data.get(pivot)) {
                upperLimit = pivot -1;
            } else if(numToFind > data.get(pivot)) {
                lowerLimit = pivot +1;
            } else {
                System.out.println(numToFind + " found at " + pivot);
                break;
            }
        }
    }

    public void testForReflection () {
        System.out.println("This has been invoked using reflection");
    }
}
