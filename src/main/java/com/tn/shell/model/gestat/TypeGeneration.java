package com.tn.shell.model.gestat;

import java.util.HashMap;
import java.util.Map;

public enum TypeGeneration {
	Sauver(1, "Sauver"), Nonsauver(2,"Nonsauver"),Cloture(3,"cloture");
    String valeur;
    int id;
    TypeGeneration(int id, String value){
        this.valeur = value;
        this.id= id;
}
    
    static Map<Integer, TypeGeneration> maps = new HashMap<Integer, TypeGeneration>();
    static {
    	TypeGeneration[] values = TypeGeneration.values();
        for (TypeGeneration s : values) {
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
   
    public static TypeGeneration findById(int id){
        return maps.get(id);
    }
    
}


