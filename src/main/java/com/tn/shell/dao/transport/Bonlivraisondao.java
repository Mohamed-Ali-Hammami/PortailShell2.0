package com.tn.shell.dao.transport;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.transport.*;


 
 

public interface Bonlivraisondao {
public void save(Bonlivraison b);
public void update(Bonlivraison b); 
public void delete(Bonlivraison b); 
public double getchiifreaffaireparclient(Integer code);
public Integer getnbBLparclient(Integer code);
public double getquantitrparclient(Integer code);
public List<Bonlivraison> getAll();
public List<Bonlivraison> getBLbyfacture(Facture f);
public Bonlivraison getBLbycodes(String  code);
public List<Bonlivraison> getBLbystatuts(Status s);
public List<Bonlivraison> getBLNonAffectee();
public List<Bonlivraison> getBLbystatutsandclient(Status s,String nom);
public Integer getmaxcode();
public double sumdtransportv(Date d1,Date d2,int id);
public Bonlivraison getBLbycode(Integer code);
public Bonlivraison getMaxbl();
public double gettotaltransport(Vhecule v,Date d1,Date d2);
public List<Bonlivraison>getAllventepardatearticle(Chauffeur a,String s);
public List<Bonlivraison> getbonlivraisonbetween(Date d1,Date d2);
public List<Chauffeur> getlistchauffeurbydate(Date d1,Date d2);
}
