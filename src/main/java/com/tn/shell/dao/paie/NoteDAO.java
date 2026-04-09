package com.tn.shell.dao.paie;

 
import java.util.List;

import com.tn.shell.model.paie.*;
 


public interface NoteDAO {
public List<Note> getAll(Integer annee,Integer mois);
public void save(Note c);
public void update(Note c);
public void detele(Note c) ;
public Note getNotesByEmployee(Employee e,Integer annee);
public List<Note> getNotesByEmployer(Employee e);
public Note getMaxPointageconge();
 
}
