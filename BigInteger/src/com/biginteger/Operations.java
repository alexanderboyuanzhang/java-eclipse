package com.biginteger;

import java.math.BigInteger;
import java.util.Scanner;

public class Operations {

	public static void main(String[] args) {

		BigInteger bi1, bi2, bigSum, bigProd;

		Scanner scanner = new Scanner(System.in);

		String s1 = scanner.next();
		String s2 = scanner.next();
		scanner.close();
		
		bi1 = new BigInteger(s1);
		bi2 = new BigInteger(s2);

		bigSum = bi1.add(bi2);
		bigProd = bi1.multiply(bi2);

		System.out.println(bigSum + "\n" + bigProd);

	}

}
