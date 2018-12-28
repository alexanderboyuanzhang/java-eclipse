package com.map.loop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListNestedInMap {
	
	//find duplicated values in array
	static void putArrayIndexToListNestedInMap(Map<Integer, List<Integer>> map, int[] a) {
		int n = a.length;
	       for(int i = 0; i<n; i++){        
	            List<Integer> l = new ArrayList<Integer>();
	            if(map.containsKey(a[i])){
	                l = map.get(a[i]);
	                l.add(i);
	            }
	            else
	                l.add(i);
	            map.put(a[i],l);
	        }
	}
}
