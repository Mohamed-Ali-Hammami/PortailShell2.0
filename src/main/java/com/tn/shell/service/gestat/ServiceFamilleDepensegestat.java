package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*;

 
 

@Service("ServiceFamilleDepensegestat")
public class ServiceFamilleDepensegestat {
	@Autowired
	FamilleDepenseDAO depenseDAO;
	
	 
		public void save(Familledepensegestat c){
			depenseDAO.save(c);
		}
		public List<Familledepensegestat> getAll(){
			return  depenseDAO.getAll();
		}
		 public  void update(Familledepensegestat c){
			depenseDAO.update(c);
		}
		  
		public void delete(Familledepensegestat c){
			depenseDAO.delete(c);
		}  
		public Familledepensegestat getFamilebyeibelle(String libelle){
			return depenseDAO.getFamilebyeibelle(libelle);
		} 
		
		public Familledepensegestat getFamilebyeid(Integer id) {
			return depenseDAO.getFamilebyeid(id);
		}
}
