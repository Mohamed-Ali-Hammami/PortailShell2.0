
package com.tn.shell.model.lavage;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author slim
 */
 public  enum TypeLavage{
        Lavage(1, "lavage"), Vidange(2,"vidange");
        String valeur;
        int id;
        TypeLavage(int id, String value){
            this.valeur = value;
            this.id= id;
        }
        
        static Map<Integer, TypeLavage> maps = new HashMap<Integer, TypeLavage>();
        static {
        TypeLavage[] values = TypeLavage.values();
            for (TypeLavage s : values) {
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
       
        public static TypeLavage findById(int id){
            return maps.get(id);
        }
        
        
        
    }

