package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;
 

public interface PompeDao {
public void save(Pompe Pompe);
public List<Pompe> getAll();
public List<String> getAllnom();
public Pompe Findbynom(String nom);
public Pompe Findbycode(Integer code);
public Pompe Findbymf(String nom);
public void update(Pompe Pompe);
public void delete(Pompe Pompe);
}
