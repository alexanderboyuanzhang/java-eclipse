package com.bz.ocstocrm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcherOCSToCRM {

	// Pattern p = Pattern.compile("([a-zA-Z0-9']+[=][a-zA-Z0-9']+[;]{1})+");
	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss.SSS");
	// private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	// private Date now = new Date();

	private String rulesStr = null;

	protected RegexMatcherOCSToCRM(String rulesStr) {
		this.rulesStr = rulesStr;
	}

	/* CONTROLLA CHE LA STRINGA REGOLA SIA DI QUESTO FORMATO 'field=value'; */
	protected boolean matchCommaSep() {
		/*
		 * PATTERN DI CARATTERI DI INPUT/OUTPUT CHE VERRA' UTILIZZATO PER CONTROLLARE LA
		 * CONFORMITA' DELLA STRINGA DI REGOLA PASSATA DELL'AMBIENTE TIBCO
		 */
//		Pattern p = Pattern.compile(
//				"([A-Za-z0-9_.-ŒœÆæ'¡¢£¤¥¦§¨©ª«¬SHY®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~ŒœÆ]+"
//						+ "[=]"
//						+ "[A-Za-z0-9_.-A-Za-z0-9_.-ŒœÆæ'¡¢£¤¥¦§¨©ª«¬SHY®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~ŒœÆ']+[;]{1})+");

		Pattern p = Pattern.compile("(.+" + "[=]" + ".+[;]{1})+");

		Matcher m = p.matcher(rulesStr);

		try {
			if (rulesStr.equals("") || rulesStr.equals(null)) {
				return false;
			} else if (m.matches()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}