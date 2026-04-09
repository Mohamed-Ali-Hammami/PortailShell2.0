package com.tn.shell.dao.gestat;

import java.util.List;
 
import com.tn.shell.model.gestat.*;
 
 

public interface ChequeredlementDao {
public void save(Chequereglement Cheque);
public List<Chequereglement> getAll();
 
public List<Chequereglement> Findbynom(String nom);
public Chequereglement Findbycode(Integer code);
public Chequereglement Findbymf(String nom);
public void update(Chequereglement Cheque);
public void delete(Chequereglement Cheque);
public List<Chequereglement> getChequebyCaisse(Caisse c);
public List<Chequereglement> getListchequedate(String date);
public List<Chequereglement> getListchequeetat(Enumcheque etat);
public List<Chequereglement> getListchequeetatandbanque(Enumcheque etat,String banque);
}
