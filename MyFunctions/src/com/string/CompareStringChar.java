package com.string;

public class CompareStringChar {
	
	static int findOnes(String str1, String str2, int m) {
		int ones = 0;
		
		for(int i=0; i<m; i++)
			if(str1.charAt(i)=='1' || str2.charAt(i)=='1')
				ones++;
		
		return ones;
	}
}
