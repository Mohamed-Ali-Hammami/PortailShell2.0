package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

 

public interface LigneGestiondao {
public void save(Lignegestion c);
public void update(Lignegestion c);
public void delete(Lignegestion c);
public List<Lignegestion> getAll();
public List<Lignegestion> getlistbyemplyee(Employee e);
public  Lignegestion getlignebygestion(Gestion e); 
}
