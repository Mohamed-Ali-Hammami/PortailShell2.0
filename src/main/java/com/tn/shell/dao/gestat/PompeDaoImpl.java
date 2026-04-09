package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;

 
 
@Repository
public class PompeDaoImpl implements PompeDao {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Pompe Pompe) {
	em.persist(Pompe);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Pompe> result = em.createQuery("SELECT c  FROM Pompe c where c.statut = :statut ", Pompe.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Pompe c:result) l.add(null);
		    return l  ;
	 }
	 @Transactional
	public List<Pompe> getAll() {
		List<Pompe> result = em.createQuery("SELECT c FROM Pompe c where c.statut = :statut ", Pompe.class)
				.setParameter("statut", Statut.ACTIF)
				.getResultList();
		if(result!=null)
	    return result;
		return null;
	}
	 @Transactional
	public Pompe Findbynom(String nom) {
		 List<Pompe> PompeListem=em.createQuery("SELECT c FROM  Pompe c where c.libelle = :nom and c.statut = :statut",Pompe.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (PompeListem.size() > 0){
	        	System.out.println("objet trouvé " );
	            return PompeListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Pompe n exsite pas\n\n");
	            return null;}   
	}
	 
	 @Transactional
		public Pompe Findbycode(Integer nom) {
			 List<Pompe> PompeListem=em.createQuery("SELECT c FROM  Pompe c where c.id = :nom and c.statut = :statut",Pompe.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		        
		        if (PompeListem.size() > 0){
		        	System.out.println("objet trouvé " );
		            return PompeListem.get(0);}
		        else{
		        	System.out.println("\n\nl  objet Pompe n exsite pas\n\n");
		            return null;}   
		}
	 
	 @Transactional
	public Pompe Findbymf(String nom) {
		 List<Pompe> PompeListem=em.createQuery("SELECT c FROM  Pompe c where c.libelle = :nom and c.statut = :statut",Pompe.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (PompeListem.size() > 0){
	        	 
	            return PompeListem.get(0);}
	        else{
	        	System.out.println("\n\nl  objet Pompe n exsite pas\n\n");
	            return null;}   
	}
	 @Transactional
	public void update(Pompe Pompe) {
		Pompe c=em.find(Pompe.class, Pompe.getId());		 
		 
		em.merge(c);
	}
	 @Transactional
	public void delete(Pompe Pompe) {
		 Pompe c=em.find(Pompe.class, Pompe.getId());
		 
		 c.setStatut(Pompe.getStatut());
		 em.merge(c);
		
	}

}
