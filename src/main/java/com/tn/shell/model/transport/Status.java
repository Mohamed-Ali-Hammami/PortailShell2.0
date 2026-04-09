
package com.tn.shell.model.transport;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author slim
 */
 public  enum Status{
        Payee(1, "payee"), NonPayee(2,"nonpayee"),  Facturee(1, "facture"), NonFacturee(2,"nonfacture");
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

