package com.tn.shell.dao.transport;

import java.util.List;

import com.tn.shell.model.transport.*;
 
public interface LigneCommandepassdao {
public void save(Lignecommandepass c);
public void update(Lignecommandepass c);
public void delete(Lignecommandepass c);
public List<Lignecommandepass> getAll();
public List<Lignecommandepass> getLcbyBL(Bonlivraison bl);
public List<Lignecommandepass> getLcbyf(Facturepassager bl);
public Lignecommandepass getLignecommandebyproduit(Integer p,Facture f);
 
}
