package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

public interface FactureAchatcarburantDAO {
	 
		public void save(Factureachatcarburant facture);
		public List<Factureachatcarburant> getAll();
		 
		public void update(Factureachatcarburant facture);
		public void delete(Factureachatcarburant facture);
		public Factureachatcarburant getbycode(String code);
		public List<Factureachatcarburant> getfacturebydate(Date d1,Date d2);
		public List<Factureachatcarburant> getfacturebyStatus(Status s);
		public Factureachatcarburant getMaxfacture();		 
		public List<Factureachatcarburant> getfacturebyfour(Fournisseur four);
}
