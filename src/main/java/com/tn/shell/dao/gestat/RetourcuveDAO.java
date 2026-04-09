package com.tn.shell.dao.gestat;

import java.util.List;

import com.tn.shell.model.gestat.*;

 
 

public interface RetourcuveDAO {
public List<Retourcuve> getAll();
public void save(Retourcuve c);
public Integer getmaxcode();
public void update(Retourcuve c);
public Retourcuve getRetourcuvebyid(Integer id);
public List<Retourcuve> getRetourcuvebyCaisse(Caisse c);
public List<Retourcuve> getRetourcuvebydate(String c);
public Retourcuve getretpourbypompe(Pompe p,Caisse c);
public long getquantitebyarticledates(Articlecarburant a,String d);
}
