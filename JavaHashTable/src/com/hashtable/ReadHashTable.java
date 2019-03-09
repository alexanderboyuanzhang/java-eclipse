package com.hashtable;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class ReadHashTable {

	@SuppressWarnings("unused")
	private void readByKeySet(Hashtable<String, String> hashTable) {

		System.out.println(hashTable);
		Set<String> keys = hashTable.keySet();
		for (String key : keys) {
			System.out.println("Value of " + key + " is: " + hashTable.get(key));
		}
	}

	@SuppressWarnings("unused")
	private void readByKeysHasMore(Hashtable<String, String> hashTable) {
		Enumeration<String> names = hashTable.keys();

		while (names.hasMoreElements()) {
			String str = (String) names.nextElement();
			System.out.println(str + ": " + hashTable.get(str));
		}
	}
}
