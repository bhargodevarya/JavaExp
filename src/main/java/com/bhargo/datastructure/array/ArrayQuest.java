package com.bhargo.datastructure.array;

import com.bhargo.datastructure.array.MOAlgo.MOAlgo;

import java.util.List;

public class ArrayQuest {

    public static void MOAlgoDemo() {
        List<MOAlgo.Query> queries = MOAlgo.getQueries();
        MOAlgo.demo(queries, new Integer[]{4,1,3,6,7,3,8,7,9,2,0,1,1,5,7});
    }

    public static int[] arrayRotation(int[] arr, int rotateBy) {
        if (rotateBy == arr.length) {
            return arr;
        } else if(rotateBy > arr.length) {
            arrayRotation(arr, rotateBy%arr.length);
        } else {
            int newIndex=0, startIndex=0, temp=arr[0], i =0, temp2;
            do {
                newIndex = arr.length - rotateBy + newIndex;
                if (newIndex >= arr.length) {
                    newIndex %= arr.length;
                }
                temp2 = arr[newIndex];
                arr[newIndex] = temp;
                temp = temp2;
            } while (newIndex != startIndex);
        }
        return arr;
    }
}
