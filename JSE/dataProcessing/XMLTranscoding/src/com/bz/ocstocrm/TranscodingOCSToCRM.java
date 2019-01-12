package com.bz.ocstocrm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class TranscodingOCSToCRM {

	/* METODO DI TRASCODIFICA ******************************************* */

	protected TranscodingOCSToCRM() {
		super();
	}

	/*
	 * FUNZIONE DI TRASCODIFICA DELLA SINGOLA STRINGA. VERRANNO PASSATI DUE
	 * ARGOMENTI: IL PRIMO ARGOMENTO E' LA STRINGA DA TRASCODIFICARE, IL SECONDO
	 * ARGOMENTO E' LA STRINGA DI REGOLE ESPRESSIONI REGOLARI
	 */
	protected String transcode(String str, String rulesStr) {
		TranscodingOCSToCRM transcoding = new TranscodingOCSToCRM();
		Hashtable<String, String> htStrVariables = transcoding.processRules(rulesStr);

		/*
		 * TENIAMO PRESENTE CHE QUI LA CHIAVE HASH INDICA IL VALORE IN INPUT DELLA
		 * TRASCODIFICA MENTRE IL VALORE HASH INDICA IL VALORE IN OUTPUT DELLA
		 * TRASCODIFICA
		 */

		Set<String> hashTableKeys = htStrVariables.keySet();
		for (String key : hashTableKeys) {
			String tmpInputStr = key;
			String tmpOutputStr = htStrVariables.get(key);
			str = str.replaceAll(tmpInputStr, tmpOutputStr);
		}
		str = str.toLowerCase();
		return str;
	}

	/*
	 * LA FUNZIONE DI TRASFORMAZIONE DATI CHE TRASFORMA LA STRINGA CARATTERIZZATA DA
	 * REGOLE DI TRASCODIFICA PROVENIENTI DALL'AMBIENTE TIBCO. QUI VIENE FATTO L'USO
	 * DI HASHTABLE. I CARATTERI IN INPUT SARANNO TRATTATI COME CHIAVE HASH MENTRE
	 * QUELLI IN OUTPUT SARANNO TRATTATI COME VALORE HASH
	 */
	public Hashtable<String, String> processRules(String rulesStr) {
		List<String> listStrVariables = new ArrayList<String>(Arrays.asList(rulesStr.split(";")));
		Hashtable<String, String> htStrVariables = new Hashtable<String, String>();
		for (String stringVariable : listStrVariables) {
			List<String> tmpList = new ArrayList<String>(Arrays.asList(stringVariable.split("=")));
			htStrVariables.put(tmpList.get(0).toString(), tmpList.get(1).toString());
		}
		return htStrVariables;
	}
}
