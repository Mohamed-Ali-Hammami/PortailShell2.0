package com.tn.shell.model.lavage;

import java.util.HashMap;
import java.util.Map;

 

public enum TypeFiltre {
	Air(1,"Air"), GASOIL(2,"GASOIL"),Huile(3,"Huile"),Essence(3,"Essence");
	
	 String valeur;
   int id;
   TypeFiltre(int id, String value){
       this.valeur = value;
       this.id= id;
   }
   
   static Map<Integer, TypeFiltre> maps = new HashMap<Integer, TypeFiltre>();
   static {
	   TypeFiltre[] values = TypeFiltre.values();
       for (TypeFiltre s : values) {
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
  
   public static TypeFiltre findById(int id){
       return maps.get(id);
   }
}
