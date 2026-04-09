package com.tn.shell.dao.lavage;

import java.util.List;

import com.tn.shell.model.lavage.Marque;

 

public interface MarqueDAO {
	public List<Marque> getAll();
	public void save(Marque c);
	public Integer getmaxcode();
	public void update(Marque c);
	public Marque getMarquebyid(Integer id);
	public Marque getMarquebyNom(String nom); 
	 
}
