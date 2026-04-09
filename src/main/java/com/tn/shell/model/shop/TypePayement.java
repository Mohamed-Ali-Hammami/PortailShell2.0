package com.tn.shell.model.shop;

import java.util.HashMap;
import java.util.Map;

public enum TypePayement {
	espece(1, "espece"), cheque(2,"cheque"),NonPayer(3,"NonPayer");
    String valeur;
    int id;
    TypePayement(int id, String value){
        this.valeur = value;
        this.id= id;
}
    
    static Map<Integer, TypePayement> maps = new HashMap<Integer, TypePayement>();
    static {
    	TypePayement[] values = TypePayement.values();
        for (TypePayement s : values) {
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
   
    public static TypePayement findById(int id){
        return maps.get(id);
    }
    
}


