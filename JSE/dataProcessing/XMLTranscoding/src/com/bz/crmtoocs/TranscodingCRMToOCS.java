package com.bz.crmtoocs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class TranscodingCRMToOCS {

	/* METODO DI TRASCODIFICA ******************************************* */

	protected TranscodingCRMToOCS() {
		super();
	}

	// private final String mu = "�";
	// private int j;

	/*
	 * FUNZIONE DI TRASCODIFICA DELLA SINGOLA STRINGA. VERRANNO PASSATI DUE
	 * ARGOMENTI: IL PRIMO ARGOMENTO E' LA STRINGA DA TRASCODIFICARE, IL SECONDO
	 * ARGOMENTO E' LA STRINGA DI REGOLE ESPRESSIONI REGOLARI
	 */
	protected String transcode(String str, String rulesStr) {

		TranscodingCRMToOCS transcoding = new TranscodingCRMToOCS();
		Hashtable<String, String> htStrVariables = transcoding.processRules(rulesStr);

		/*
		 * TENIAMO PRESENTE CHE QUI LA CHIAVE HASH INDICA IL VALORE IN INPUT DELLA
		 * TRASCODIFICA MENTRE IL VALORE HASH INDICA IL VALORE IN OUTPUT DELLA
		 * TRASCODIFICA
		 */
		Set<String> hashTableKeys = htStrVariables.keySet();
		for (String key : hashTableKeys) {
			String tmpInputStr = "[" + key + "]";
			String tmpOutputStr = htStrVariables.get(key);
//			str = str.replaceAll("(" + tmpInputStr + " |" + tmpInputStr + "$)", tmpOutputStr + " ");
			str = str.replaceAll("(" + tmpInputStr + " )", tmpOutputStr + " ");
			str = str.replaceAll("(" + tmpInputStr + "$)", tmpOutputStr);
		}

		/*
		 * PER IL FATTO CHE DOBBIAMO TRASCODIFICARE SOLTANTO L'ULTIMO CARATTERE VOCALE
		 * DELLE PAROLE, TRASCODIFICHIAMO LE VOCALI ACCENTATI TRA I CARATTERI DELLA
		 * PAROLA ECCETTO L'ULTIMO CARATTERE IN UNA VOCALE NORMALE, ES: "�mbito"
		 * DIVENTERA' "Ambito". PER QUESTO MOTIVO SOSTITUIAMO ANCHE LA REGOLA
		 */
		Hashtable<String, String> htStrVariables1 = htStrVariables;
		Set<String> hashTableKeys1 = htStrVariables1.keySet();
		for (String key : hashTableKeys1) {
			String tmpOutputStr = htStrVariables1.get(key);
			tmpOutputStr = tmpOutputStr.replace("'", "");

			String tmpInputStr = "[" + key + "]";
			str = str.replaceAll("(" + tmpInputStr + ")", tmpOutputStr);
		}

		/* TRASCODIFICA DI CARATTERI NON ACCETTATI IN '-' */
		RegexMatcherCRMToOCS javaRegex = new RegexMatcherCRMToOCS();
		/*
		 * LA STRINGA DI CARATTERI DA TRASOFRMARE IN -, PER UN ADATTAMENTO MIGLIORE IN
		 * OCS
		 */
		final String LOCAL_RULE_STR = "¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼½¾¿×ÝÞß÷ýþÿ";
		if (!javaRegex.isTheCorrectPattern(str)) {
			String tmpInputStr = "[" + LOCAL_RULE_STR + "]";
			str = str.replaceAll(tmpInputStr, "-");
		}

		str = str.toUpperCase();

		// if (!str.contains(mu)) {
		// str = str.toUpperCase();
		// }
		//
		// if (str.contains(mu)) {
		// char[] charArray = str.toCharArray();
		// j = 0;
		// while (j < charArray.length) {
		// char tmpChar = charArray[j];
		// String tmpStr = String.valueOf(tmpChar);
		// if (!mu.equals(tmpStr)) {
		// tmpStr = tmpStr.toUpperCase();
		// }
		// charArray[j] = tmpStr.charAt(0);
		// j++;
		// }
		// str = String.valueOf(charArray);
		// }

		return str;
	}

	/*
	 * LA FUNZIONE DI TRASFORMAZIONE DATI CHE TRASFORMA LA STRINGA CARATTERIZZATA DA
	 * REGOLE DI TRASCODIFICA PROVENIENTI DALL'AMBIENTE TIBCO. QUI VIENE FATTO L'USO
	 * DI HASHTABLE. I CARATTERI IN INPUT SARANNO TRATTATI COME CHIAVE HASH MENTRE
	 * QUELLI IN OUTPUT SARANNO TRATTATI COME VALORE HASH
	 */
	private Hashtable<String, String> processRules(String rulesStr) {
		List<String> listStrVariables = new ArrayList<String>(Arrays.asList(rulesStr.split(";")));

		Hashtable<String, String> htStrVariables = new Hashtable<String, String>();

		for (String stringVariable : listStrVariables) {
			List<String> tmpList = new ArrayList<String>(Arrays.asList(stringVariable.split("=")));
			htStrVariables.put(tmpList.get(0).toString(), tmpList.get(1).toString());
		}
		return htStrVariables;
	}
}
