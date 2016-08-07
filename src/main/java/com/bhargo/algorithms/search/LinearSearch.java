package com.bhargo.algorithms.search;

import com.bhargo.util.Util;

import java.io.IOException;
import java.util.List;

/**
 * Created by barya on 8/7/16.
 */
public class LinearSearch {

    /**
     * Performs linear search
     * @param fileName
     * @throws IOException
     */
    public static void doLinearSearch(String fileName, String numToFind) throws IOException {
        List<String> data = Util.readFileAsListOfString(fileName," ");
        for (int i =0;i<data.size();i++) {
            if(data.get(i).equals(numToFind)) {
                System.out.println("found " + numToFind + " at " + i);
                break;
            }
        }

    }
}
