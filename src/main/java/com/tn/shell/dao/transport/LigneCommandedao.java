package com.tn.shell.dao.transport;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.*;
 
public interface LigneCommandedao {
public void save(Lignecommande c);
public void update(Lignecommande c);
public void delete(Lignecommande c);
public List<Lignecommande> getAll();
public List<Lignecommande> getLcbyBL(Bonlivraison bl);
public List<Lignecommande> getLcbyf(Facture bl);
public Lignecommande getLignecommandebyproduit(Integer p,Facture f);
public List<Lignecommande>getAllventepardatearticle(Produit a,String s);
public List<Lignecommande>getAllventepardate(String s);
public double sumdtransport(Date d1,Date d2);
public double sumdtransportv(Date d1,Date d2,int id);
}
