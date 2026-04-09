package com.tn.shell.model.banque;

import java.util.HashMap;
import java.util.Map;

public enum Reglement {
	Espece(1, "Espece"), Cheque(2,"cheque");
    String valeur;
    int id;
    Reglement(int id, String value){
        this.valeur = value;
        this.id= id;
}
    
    static Map<Integer, Reglement> maps = new HashMap<Integer, Reglement>();
    static {
    	Reglement[] values = Reglement.values();
        for (Reglement s : values) {
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
   
    public static Reglement findById(int id){
        return maps.get(id);
    }
    
}


