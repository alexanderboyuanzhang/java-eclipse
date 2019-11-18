package com.boyuanzhang.arraylist;

import java.util.ArrayList;

public class JavaArrayList {
	
	@SuppressWarnings("unused")
	private void getListWithFor(ArrayList<String> arrayList){
	    for (int i = 0; i < arrayList.size(); i++)
	      {
	         System.out.println(arrayList.get(i).toString());
	      }
	}

}
