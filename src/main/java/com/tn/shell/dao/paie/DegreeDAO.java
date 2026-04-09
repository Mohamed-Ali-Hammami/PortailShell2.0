package com.tn.shell.dao.paie;

import java.util.List;
import com.tn.shell.model.paie.*;
 

public interface  DegreeDAO  {
public void save( Degree   Degree );
public List<Degree> getAll();
 public Degree findbyDesignation(Integer v);
 public void update(Degree Degree ); 
  
 public Degree findbyid(Integer id);
}
