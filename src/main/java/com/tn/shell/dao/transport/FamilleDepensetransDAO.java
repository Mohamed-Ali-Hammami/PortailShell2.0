package com.tn.shell.dao.transport;

import java.util.List;

import com.tn.shell.model.transport.Familledepensetransport;

 
public interface FamilleDepensetransDAO {
public void save(Familledepensetransport  familleDepense);
public List<Familledepensetransport> getAll();
public Familledepensetransport getFamilebyeibelle(String libelle);

}
