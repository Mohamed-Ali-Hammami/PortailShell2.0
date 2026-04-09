package com.tn.shell.model.gestat;

import java.util.HashMap;
import java.util.Map;

public enum EProfil {
	// Admin,Caissier,Superviseur,Responsableshop
	Superviseur(1, "Superviseur"), Responsableshop(2, "Responsableshop"), Admin(3, "Admin"), Caissier(4, "Caissier"),Responsablelavage(5, "Responsablelavage")
	,AgentTransport(6, "AgentTransport");
	String valeur;
	int id;

	EProfil(int id, String value) {
		this.valeur = value;
		this.id = id;
	}
	static Map<Integer, EProfil> maps = new HashMap<Integer, EProfil>();
	static{
		EProfil[] values = EProfil.values();
		for (EProfil s : values) {
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

	public static EProfil findById(int id) {
		return maps.get(id);
	}

}
