package com.tn.shell.dao.paie;

 
import java.util.List;
import com.tn.shell.model.paie.*;


public interface PretDAO {
public List<Pret> getAll();
public void save(Pret c);
public void update(Pret c);
public void detele(Pret c) ;
public Pret getPretencours(Employee e);
}
