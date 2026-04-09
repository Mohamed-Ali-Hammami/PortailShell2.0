package com.tn.shell.model.banque;

import java.util.HashMap;
import java.util.Map;

public enum TypeTransaction {
	Debit(1, "Debit"), Credit(2, "Credit");
	
	String valeur;
	int id;

	TypeTransaction(int id, String value) {
		this.valeur = value;
		this.id = id;
	}
	static Map<Integer, TypeTransaction> maps = new HashMap<Integer, TypeTransaction>();
	static{
		TypeTransaction[] values = TypeTransaction.values();
		for (TypeTransaction s : values) {
			maps.put(s.id, s);
		 }
	}

	@Override
	public String toString() {
		return valeur;
	}

	public int toInt() {
		return id;
	}

	public static TypeTransaction findById(int id) {
		return maps.get(id);
	}

}
