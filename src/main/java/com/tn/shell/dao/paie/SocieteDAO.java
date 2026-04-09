package com.tn.shell.dao.paie;

import java.util.List;

import com.tn.shell.model.paie.*;

public interface SocieteDAO {
public void save(Societe s); 
public void update(Societe s);
public List<Societe> getAll();
}
