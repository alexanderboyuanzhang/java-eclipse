package com.list;

import java.util.List;

public class LoopPrintList {
	
	static void loopPrintList(List<?> list) {
		for(Object l: list) {
			System.out.println(l.toString());
		}
	}
}
