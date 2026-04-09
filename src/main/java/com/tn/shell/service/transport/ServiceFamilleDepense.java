package com.tn.shell.service.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.FamilleDepensetransDAO;
import com.tn.shell.model.transport.Familledepensetransport;

 

 

@Service("ServiceFamilleDepense")
public class ServiceFamilleDepense {
	@Autowired
	FamilleDepensetransDAO depenseDAO;
	
	 
		public void save(Familledepensetransport c){
			depenseDAO.save(c);
		}
		public List<Familledepensetransport> getAll(){
			return  depenseDAO.getAll();
		}
		 /* void update(Familledepense c){
			depenseDAO.update(c);
		}
		public void delete(Depense c){
			depenseDAO.delete(c);
		}*/
		public Familledepensetransport getFamilebyeibelle(String libelle){
			return depenseDAO.getFamilebyeibelle(libelle);
		} 
}
