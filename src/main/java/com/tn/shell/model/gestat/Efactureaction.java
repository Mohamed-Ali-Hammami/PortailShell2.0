package com.tn.shell.model.gestat;

import java.util.HashMap;
import java.util.Map;

public enum Efactureaction {
	FacturationParMouvement(1, "Facturation Par Mouvement"), FacturationParMontantGlobal(2,"Facturation Par Montant Global");
    String valeur;
    int id;
    Efactureaction(int id, String value){
        this.valeur = value;
        this.id= id;
}
    
    static Map<Integer, Poste> maps = new HashMap<Integer, Poste>();
    static {
    	Poste[] values = Poste.values();
        for (Poste s : values) {
            maps.put(s.id, s);
        }
    }

    @Override
    public String toString() {
        return  valeur ;
    }
    public int toInt(){
        return id;
    }
   
    public static Poste findById(int id){
        return maps.get(id);
    }
}
