package com.tn.shell.dao.gestat;

import java.util.List;
import com.tn.shell.model.gestat.*;


 
public interface FamilleDepenseDAO {
public void save(Familledepensegestat familleDepense);
public void update(Familledepensegestat familleDepense);
public void delete(Familledepensegestat familleDepense);
public List<Familledepensegestat> getAll();
public Familledepensegestat getFamilebyeibelle(String libelle);
public Familledepensegestat getFamilebyeid(Integer id);
}
