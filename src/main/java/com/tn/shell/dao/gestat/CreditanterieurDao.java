package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;

 
 

public interface CreditanterieurDao {
public void save(Creditanterieur creditanterieur);
public List<Creditanterieur> getAll();
public List<Creditanterieur> getCreditanterieurbyCaisse(Caisse c);
public List<Creditanterieur> Findbynom(String nom);
public Creditanterieur Findbycode(Integer code);
public Creditanterieur Findbymf(String nom);
public void update(Creditanterieur creditanterieur);
public void delete(Creditanterieur creditanterieur);
public Creditanterieur getcreditbyid(Integer id);
public List<Creditanterieur> getListcreditdate(String date);
public  List<Creditanterieur> getCreditanterieurbyclient(Clientgestat c ,String dates);
public  Creditanterieur getCreditclientbyCaisseandclient( Creditanterieur cl);
}
