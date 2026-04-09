package com.tn.shell.model.gestat;

import java.util.HashMap;
import java.util.Map;

public enum Poste {
	Poste1(1, "Poste1"), Poste2(2,"Poste2");
    String valeur;
    int id;
    Poste(int id, String value){
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


