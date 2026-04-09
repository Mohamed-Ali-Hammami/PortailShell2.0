package com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.shop.Factureachat;

 
public interface FactureAchatDAO {
	 
		public void save(Factureachat facture);
		public List<Factureachat> getAll();
		public void update(Factureachat facture);
		public void delete(Factureachat facture);
		public Factureachat getbycode(String code);
		public List<Factureachat> getfacturebydate(Date d1,Date d2);
		 public Factureachat getMaxfacture();
}
