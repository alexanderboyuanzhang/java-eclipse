package com.bz.crmtoocs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcherCRMToOCS {

	// Pattern p = Pattern.compile("([a-zA-Z0-9']+[=][a-zA-Z0-9']+[;]{1})+");
	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss.SSS");
	// private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	// private Date now = new Date();

	private String rulesStr = null;

	protected RegexMatcherCRMToOCS() {
		super();
	}

	protected RegexMatcherCRMToOCS(String rulesStr) {
		this.rulesStr = rulesStr;
	}

	/* IL METODO CHE CONTRALLA SE SONO I CARATTERI ACCETTATI DA OCS */
	protected boolean isTheCorrectPattern(String str) {
		/* PATTERN DI CARATTERI ACCETTATI IN OCS */
		Pattern patternRule = Pattern.compile("([A-Z0-9!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~])+");
		Matcher m = patternRule.matcher(str);
		if (m.matches()) {
			return true;
		} else
			return false;
	}

	/* CONTROLLA CHE LA STRINGA REGOLA SIA DI QUESTO FORMATO 'field=value'; */
	protected boolean matchCommaSep() {
		/*
		 * PATTERN DI CARATTERI DI INPUT/OUTPUT CHE VERRA' UTILIZZATO PER CONTROLLARE LA
		 * CONFORMITA' DELLA STRINGA DI REGOLA PASSATA DELL'AMBIENTE TIBCO
		 */
//		Pattern patternRule1 = Pattern.compile(
//				"([A-Za-z0-9_.-ŒœÆæ'¡¢£¤¥¦§¨©ª«¬SHY®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~ŒœÆ]+"
//						+ "[=]"
//						+ "[A-Za-z0-9_.-A-Za-z0-9_.-ŒœÆæ'¡¢£¤¥¦§¨©ª«¬SHY®¯°±²³´µ¶·¸¹º»¼½¾¿À�?ÂÃÄÅÆÇÈÉÊËÌ�?Î�?�?ÑÒÓÔÕÖ×ØÙÚÛÜ�?Þßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~ŒœÆ']+[;]{1})+");

		Pattern patternRule1 = Pattern.compile("(.+" + "[=]" + ".+[;]{1})+");

		Matcher m = patternRule1.matcher(rulesStr);
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