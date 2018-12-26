package com.list;

import java.util.List;

public class FindMinList {
	
	public static int findMinIntegerFromList(List<Integer> list) {
		int min = list.get(0);
		
		for (Integer integer: list)
			if(min>integer)
				min = integer;
		return min;
	}

}
