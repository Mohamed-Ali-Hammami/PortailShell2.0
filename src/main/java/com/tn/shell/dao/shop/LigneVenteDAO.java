package com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.shop.*;

public interface LigneVenteDAO {
	public void save(Lignevente c);

	public void update(Lignevente c);

	public void delete(Lignevente c);

	public List<Lignevente> getAll();

	public List<Lignevente> getAllventeparposte(String d);
	public double getAllventeparDateandproduit2(Date d1,Date d2,Produit p);
	public List<Lignevente> getAllventeparposteandDate(String d, Poste poste);

	public List<Lignevente> getAllventeparposteNegatif(String d, Poste poste);

	public Lignevente getlignebyid(Integer id);

	public List<Lignevente> getAllbyticket(Ticket ticket);
	public List<Lignevente> getAllbyNumticket(Integer ticket);

	public List<Lignevente> getAllbyProduit(Produit p);

	public double getProfilBrutParFamillebetwendate2(Date d1, Date d2, int f);

	public List<Lignevente> getbetwendates(Date d1, Date d2, Produit p);

	public List<Lignevente> getbetwendate(Date d1, Date d2);

	public Lignevente getmaxlignevente();

	public List<Lignevente> getAllventeparposteandDate2(String d, Poste poste);

	public List<Lignevente> getAllventeparDate(String d);

	public List<Lignevente> getAllventeparDateandp(String d, Produit p);

	public List<Lignevente> getAllventeparposteandDate3(String d, Poste poste);

	public List<Lignevente> getAllventeparposteandDate(String d);

	public List<Lignevente> getAllventeparparfamille(String f, String d);

	public List<Lignevente> getAllventeparparfamille2(String f, String d, Poste poste);

	public List<Lignevente> getAllventeparparfamille3(String f, String d, Poste poste);

	public List<Lignevente> getAllventeparposteandDate33(String f, String d, Poste poste);

	public List<Lignevente> getAllventeparposteandDate33(String d, Poste poste);

	public double getmontantbetwendate(Date d1, Date d2);

	public double getmargebetwendate(Date d1, Date d2);

	public double getAllventeparDateandproduit(String d, Produit p);

	public double getQuantiteParFamillebetwendate(Date d1, Date d2, Famillearticle f);

	public double getTotalttcParFamillebetwendate(Date d1, Date d2, Famillearticle f);

	public double getProfilBrutParFamillebetwendate(Date d1, Date d2, Famillearticle f);
	
	 

	public double getTotalTCtbytDate(String d2);
}
