
package com.tn.shell.model.shop;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author slim
 */
 public  enum Status{
        Payee(1, "payee"), NonPayee(2,"nonpayee");
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

		public String toDatabaseValue() {
			switch (this) {
			case NonPayee:
				return "NonPayée";
			case Payee:
				return "Payée";
			default:
				return name();
			}
		}
       
        public static Status findById(int id){
            return maps.get(id);
        }

		public static Status fromValue(String value) {
			if (value == null) {
				return null;
			}
			String normalized = normalizeValue(value);
			if (normalized.isEmpty()) {
				return null;
			}
			if (normalized.startsWith("nonpay")) {
				return NonPayee;
			}
			if (normalized.startsWith("pay")) {
				return Payee;
			}
			for (Status status : Status.values()) {
				if (status.name().equalsIgnoreCase(value.trim())) {
					return status;
				}
			}
			return null;
		}

		private static String normalizeValue(String raw) {
			String normalized = raw.trim()
					.replace("ÃƒÂ©", "e")
					.replace("ÃƒÂ¨", "e")
					.replace("ÃƒÂª", "e")
					.replace("Ãƒ", "a")
					.replace("Ãš", "e");
			normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
			normalized = normalized.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
			return normalized;
		}
    }

