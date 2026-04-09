package com.tn.shell.dao.transport;

import java.util.List;

import com.tn.shell.model.transport.*;

public interface ChauffeurDao {
public void save(Chauffeur Chauffeur);
public List<Chauffeur> getAll();
public Chauffeur Findbynom(String nom);
public Chauffeur Findbycode(Integer code);
public Chauffeur Findbymf(String nom);
public void update(Chauffeur chauffeur);
public void delete(Chauffeur chauffeur);
public List<String> getAllnom();
}
