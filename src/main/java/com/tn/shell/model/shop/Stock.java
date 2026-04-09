
package com.tn.shell.model.shop;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author slim
 */
 public  enum Stock{
        Depot(1, "Depot"), Shop(2,"Shop"),Les2(2,"Les 2");
        String valeur;
        int id;
        Stock(int id, String value){
            this.valeur = value;
            this.id= id;
        }
        
        static Map<Integer, Stock> maps = new HashMap<Integer, Stock>();
        static {
        Stock[] values = Stock.values();
            for (Stock s : values) {
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
       
        public static Stock findById(int id){
            return maps.get(id);
        }
        
        
        
    }

