package com.bhargo.reflection;

import com.bhargo.algorithms.search.BinarySearch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by barya on 8/30/16.
 */
public class ReflectionDemo {

    public static void  reflect() throws ClassNotFoundException {
        Class binarySearch = Class.forName("com.bhargo.algorithms.search.BinarySearch");
        Method[] methods = binarySearch.getMethods();
        Arrays.stream(methods).filter(n -> n.getName().equals("testForReflection")).forEach(n -> {
            try {
                n.invoke(new BinarySearch());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
