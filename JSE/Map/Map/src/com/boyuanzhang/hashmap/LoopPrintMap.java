package com.boyuanzhang.hashmap;

import java.util.Map;

public class LoopPrintMap {

	public static void loopPrintIntegerIntegerMap(Map<Integer, Integer> map) {
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
		}
	}
}
