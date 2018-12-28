package com.map.loop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoopPrintMap {

	//argument type: Map<Integer,Integer>
	public static void loopPrintMap(Map<Integer, Integer> map) {
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
		}
	}
	
	
	//Overloading - argument type: HashMap<Integer, ArrayList<Integer>>
    public static void loopPrintMap(HashMap<Integer, ArrayList<Integer>> hashMap) {
        for (Map.Entry<Integer, ArrayList<Integer>> entry : hashMap.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
