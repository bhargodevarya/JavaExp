package com.bhargo.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.bhargo.datastructure.DSUtil.fruit;

public class DSUtil {
	

	public enum fruit {
		APPLE("apple"), MANGO("mango"), BANANA("banana"), ORANGE("orange"), GRAPES("grapes");

		String name;

		fruit(String name) {
			this.name = name;
		}

		public String getname() {
			return name;
		}
	}
	
	public static void listToMapConversion(List<fruit> bigList,Map<fruit,Integer> countMap) {
		Iterator<fruit> itr = bigList.iterator();
		boolean keyPresent = false;
		while(itr.hasNext()) {
			fruit fr = itr.next();
			keyPresent = countMap.containsKey(fr);
			if(keyPresent) {
				countMap.put(fr, countMap.get(fr) + 1);
			} else {
				countMap.put(fr, 0);
			}
		}
		
		Set<Map.Entry<fruit, Integer>> entrySet = countMap.entrySet();
		for (Map.Entry<fruit, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

}
