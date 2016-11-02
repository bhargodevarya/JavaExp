package com.bhargo.algorithms.sort;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bhargo on 2/11/16.
 */
public class Sort {

    public static void bubbleSort(List<String> list) {
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

    public static void quickSort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }
        doQuickSort(arr, 0, arr.length -1);
    }

    public static void mergeSort(int[] arr) {
        doMergeSort(arr, 0, arr.length -1);
    }

    private static void doMergeSort(int[] arr, int low, int high) {
        if(low < high) {
            int m = (low+high)/2;
            doMergeSort(arr, low, m -1);
            doMergeSort(arr,m+1,high);
            merge(arr, low, m, high);

        }
    }

    private static void merge (int[] arr, int low, int middle, int high) {

        //create 2 temp arrays
    }

    private static void doQuickSort(int[] arr, int low, int high) {
        int in = (low+high)/2;
        int pivot = arr[in];
        int i =low,j=high;
        while (i<=j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if(i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
            if (i < high) {
                doQuickSort(arr,i,high);
            }
            if (j > low) {
                doQuickSort(arr, low, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
