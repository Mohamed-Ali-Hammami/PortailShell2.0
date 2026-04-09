package com.tn.shell.model.banque;

import java.util.HashMap;
import java.util.Map;

public enum Enumcheque {
	Entree(1, "Entree"), Impayes(2, "Impayes"), Antidate(3, "Antidate"), Preavis(4, "Preavis"), Encours(5, "Encours"), Mannuel(6, "Mannuel"),
	Annuler(7, "Annuler") ,Payes(8, "payes"),EnCirculation(9, "Encirculation")
	;
	String valeur;
	int id;

	Enumcheque(int id, String value) {
		this.valeur = value;
		this.id = id;
	}
	static Map<Integer, Enumcheque> maps = new HashMap<Integer, Enumcheque>();
	static{
		Enumcheque[] values = Enumcheque.values();
		for (Enumcheque s : values) {
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

	public static Enumcheque findById(int id) {
		return maps.get(id);
	}

}
