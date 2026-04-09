package com.tn.shell.dao.lavage;

import java.util.List;

import com.tn.shell.model.lavage.AffectationFiltre;
import com.tn.shell.model.lavage.Model;

 
public interface AffectationDAO {
	public List<AffectationFiltre> getAll();
	public void save(AffectationFiltre c);
	public Integer getmaxcode();
	public void update(AffectationFiltre c);
	public AffectationFiltre getAffectationFiltrebyid(Integer id);
	public List<AffectationFiltre> getAffectationFiltrebyModel(Model f);
 
	 
}
