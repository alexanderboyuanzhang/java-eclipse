package com.list;

import java.util.ArrayList;
import java.util.Arrays;

public class StringToArrayList {
	static ArrayList<String> stringToArrayList(String str) {
		
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(str.split(" ")));
		
		return al;
	}
}
