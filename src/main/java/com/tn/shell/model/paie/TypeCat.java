package com.tn.shell.model.paie;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 
public enum TypeCat {
	Implementation(1,"Implementation"), Administratif(2,"Administratif"), Cadre(2,"Cadre");
	 String valeur;
	    int id;
	    TypeCat(int id, String value){
	        this.valeur = value;
	        this.id= id;
	    }
	    
	    static Map<Integer, Status> maps = new HashMap<Integer, Status>();
	    static {
	    Status[] values = Status.values();
	        for (Status s : values) {
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
	   
	    public static Status findById(int id){
	        return maps.get(id);
	    }
}
