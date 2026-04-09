package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;

 
 

public interface PompisteDAO {
public List<Pompiste> getAll();
public void save(Pompiste c);
public Integer getmaxcode();
public void update(Pompiste c);
public Pompiste getPompistebyid(Integer id);
public Pompiste getPompistebydate(String date,Poste poste);
 
}
