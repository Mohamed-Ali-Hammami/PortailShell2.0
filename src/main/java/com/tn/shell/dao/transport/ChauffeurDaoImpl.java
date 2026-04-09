package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.transport.*;
 
@Repository
public class ChauffeurDaoImpl implements ChauffeurDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Chauffeur Chauffeur) {
	em.persist(Chauffeur);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Chauffeur> result = em.createQuery("SELECT c  FROM Chauffeur c where c.statut = :statut ", Chauffeur.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Chauffeur c:result) l.add(c.getNompenom());
		    return l  ;
	 }
	 @Transactional
	public List<Chauffeur> getAll() {
		List<Chauffeur> result = em.createQuery("SELECT c FROM Chauffeur c  where c.statut = :statut", Chauffeur.class).setParameter("statut", Statut.ACTIF).getResultList();
	    return result;
	}
	 @Transactional
	public Chauffeur Findbynom(String nom) {
		 List<Chauffeur> ChauffeurListem=em.createQuery("SELECT c FROM  Chauffeur c where c.nompenom = :nom and c.statut = :statut",Chauffeur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChauffeurListem.size() > 0){
	        	System.out.println("objet trouvé "+ChauffeurListem.get(0).getNompenom()+"\n\n\n");
	            return ChauffeurListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Chauffeur n exsite pas\n\n");
	            return null;}   
	}
	 
	 @Transactional
		public Chauffeur Findbycode(Integer nom) {
			 List<Chauffeur> ChauffeurListem=em.createQuery("SELECT c FROM  Chauffeur c where c.nom = :nom and c.statut = :statut",Chauffeur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (ChauffeurListem.size() > 0){
		        	System.out.println("objet trouvé "+"\n\n\n");
		            return ChauffeurListem.get(0);}
		        else{
		        	System.out.println("\n\nl  objet Chauffeur n exsite pas\n\n");
		            return null;}   
		}
	 
	 @Transactional
	public Chauffeur Findbymf(String nom) {
		 List<Chauffeur> ChauffeurListem=em.createQuery("SELECT c FROM  Chauffeur c where c.matriculefiscal = :nom and c.statut = :statut",Chauffeur.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (ChauffeurListem.size() > 0){
	        	System.out.println("objet trouvé "+"\n\n\n");
	            return ChauffeurListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Chauffeur n exsite pas\n\n");
	            return null;}   
	}
	 @Transactional
	public void update(Chauffeur chauffeur) {
		Chauffeur c=em.find(Chauffeur.class, chauffeur.getId());
		 
		  
		c.setNompenom(chauffeur.getNompenom());
		c.setNumtel(chauffeur.getNumtel());
		em.merge(c);
	}
	 @Transactional
	public void delete(Chauffeur chauffeur) {
		 Chauffeur c=em.find(Chauffeur.class, chauffeur.getId());
		 c.setStatut(chauffeur.getStatut());
		 em.merge(c);
		
	}

}
