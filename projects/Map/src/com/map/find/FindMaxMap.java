package com.map.find;

import java.util.Map;

public class FindMaxMap {

	public static int findMaxValue(Map<Integer,Integer> map) {
		Map.Entry<Integer, Integer> maxEntry = null;

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
	}
}
