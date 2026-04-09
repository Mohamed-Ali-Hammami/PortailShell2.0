package com.tn.shell.dao.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;

 
 
@Repository
public class TvaDAOImpl  implements TvaDAO{
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public List<Tva> getAll() {
		 List<Tva> result = em.createQuery("SELECT p FROM Tva p  ", Tva.class).getResultList();
		    return result;
	}
	 @Transactional
	 public List<Integer> getAllvaleur(){
		 List<Integer> l=new ArrayList<Integer>();
		 List<Tva> result = em.createQuery("SELECT c  FROM Tva c where c.statut = :statut ", Tva.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Tva c:result) l.add(c.getValeur());
		    return l  ;
	 }
	 @Transactional
	public void update(Tva t) {
		Tva v=em.find(Tva.class, t.getId());
		v.setValeur(t.getValeur());
		em.merge(v);
	}
	 @Transactional
	 public Tva getbyid(Integer t){
			return em.find(Tva.class, t);
	 }
	  
	 @Transactional
	public void save(Tva t) {
		 em.persist(t);
		
	}
	@Transactional
	public Tva getbyvaleur(Integer t) {
		 List<Tva> TvaListem=em.createQuery("SELECT u FROM  Tva u where u.valeur = :valeur",Tva.class).setParameter("valeur", t).getResultList();
	        
	        if (TvaListem.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return TvaListem.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}

}
