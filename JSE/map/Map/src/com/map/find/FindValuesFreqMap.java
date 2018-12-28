package com.map.find;

import java.util.Map;

public class FindValuesFreqMap {

	public static int findValuesFreq(Map<Integer,Integer> map, Integer value) {
		int count = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if(value==entry.getValue())
				count++;
		}
		return count;
	}
	
}
