package com.numbers;

public class TimeInWords {

	//given hour(h) and minutes(m)
	static String timeInWords(int h, int m) {
		String time = "";

		final String[] numbers = { "o' clock", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
				"ten", "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen",
				"nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
				"twenty six", "twenty seven", "twenty eight", "twenty nine", "half" };

		int k = 60 - m;

		if (m == 0)
			time = numbers[h] + " " + numbers[m];
		else if (m == 1)
			time = numbers[m] + " minute past " + numbers[h];
		else if (m > 1 && m < 15 || m > 15 && m < 30)
			time = numbers[m] + " minutes past " + numbers[h];
		else if (m == 15)
			time = numbers[m] + " past " + numbers[h];
		else if (m == 30)
			time = numbers[m] + " past " + numbers[h];
		else if (k == 1)
			time = numbers[k] + " minute to " + numbers[h + 1];
		else if (k > 1 && k < 15 || k > 15 && k < 30)
			time = numbers[k] + " minutes to " + numbers[h + 1];
		else if (k == 15)
			time = numbers[k] + " to " + numbers[h + 1];

		return time;
	}

}
