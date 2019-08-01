package com.hackerrank.java1darray;

import java.util.Scanner;

public class Java1DArray {
	public static boolean canWin(int leap, int[] game) {
		// Return true if you can win the game; otherwise, return false.
		boolean result = false;

		int n = game.length;

		int i = 0;

		while (i < n - 1) {
			if (game[i + 1] == 0) {
				i++;
				if (i == (n - 1))
					result = true;
				System.out.println("breakpoint, i: " + i);
			} else if (game[i + 1] == 1) {
				i = i + leap;
				if (i > (n - 1)) {
					result = true;
					break;
				}
				if (game[i] == 1) {
					System.out.println("breakpoint, i: " + i);
					result = false;
					break;
				}
				if (i == (n - 1) && game[i] == 1) {
					result = false;
					break;
				}
				System.out.println("breakpoint, i: " + i);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int q = scan.nextInt();
		while (q-- > 0) {
			int n = scan.nextInt();
			int leap = scan.nextInt();

			int[] game = new int[n];
			for (int i = 0; i < n; i++) {
				game[i] = scan.nextInt();
			}

			System.out.println((canWin(leap, game)) ? "YES" : "NO");
		}
		scan.close();
	}
}
