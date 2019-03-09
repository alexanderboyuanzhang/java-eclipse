package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegex {

	Pattern p = Pattern.compile("([a-zA-Z0-9]+[=][a-zA-Z0-9]+[;]{1})+");

	protected boolean matchCommaSep(String str) {
		Matcher m = p.matcher(str);

		if (str.equals("") || str.equals(null)) {
			System.out.println("Errore di mappatura: inserire la " + "stringa di regole");
			return false;
		} else if (m.matches()) {
			System.out.println("Formato stringa corretto");
			return true;
		} else {
			System.out.println("Errore di mappatura, " + "\nil formato corretto è 'campo1=valore1;campo2=valore2;'");
			return false;
		}

	}

}
