package com.hackerrank.boyuanzhang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaStringTokens {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();

		String delimiters = "[,.'@_!? ]+\\s*|[ ]+";

		ArrayList<String> tokensVal = new ArrayList<String>(Arrays.asList(s.split(delimiters)));
		ArrayList<String> tokensVal2 = new ArrayList<String>();
		
		Pattern p = Pattern.compile("");

		for (int i = 0; i < tokensVal.size(); i++) {
			Matcher m = p.matcher(tokensVal.get(i));
			if (m.matches())
				continue;
			else
				tokensVal2.add(tokensVal.get(i));
		}

		System.out.println(tokensVal2.size());
		
		for (int i = 0; i < tokensVal2.size(); i++)
			System.out.println(tokensVal2.get(i));

		scan.close();

	}

}
