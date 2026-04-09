
package com.tn.shell.model.paie;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author slim
 */
 public  enum Status{
        Declare(1, "Declare"), NonDeclaree(2,"NonDeclaree"),ParVoiture(3,"ParVoiture");
        String valeur;
        int id;
        Status(int id, String value){
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

