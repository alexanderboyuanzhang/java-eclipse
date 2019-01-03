package com.regex;

public class JavaRegexTest {

	public static void main(String[] args) {

		String str = "ddg=gdgd;dgd=dfd;";
		String str1 = "ddg=gdgd;dgd=dfd";
		String str2 = "";
		JavaRegex javaRegex = new JavaRegex();
		
		javaRegex.matchCommaSep(str1);
			
		
	}

}
