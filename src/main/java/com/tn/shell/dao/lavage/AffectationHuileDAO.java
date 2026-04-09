package com.tn.shell.dao.lavage;

import java.util.List;

import com.tn.shell.model.lavage.AffectationHuile;
import com.tn.shell.model.lavage.Model;

 
public interface AffectationHuileDAO {
	public List<AffectationHuile> getAll();
	public void save(AffectationHuile c);
	public Integer getmaxcode();
	public void update(AffectationHuile c);
	public AffectationHuile getAffectationHuilebyid(Integer id);
	public List<AffectationHuile> getAffectationHuilebyModel(Model f);
 
	 
}
