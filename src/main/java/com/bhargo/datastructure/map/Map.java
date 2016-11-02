package com.bhargo.datastructure.map;

/**
 * Created by bhargo on 30/10/16.
 */
public interface Map<K,V> {
    V put(K k, V v);
    V get(K k);
}
