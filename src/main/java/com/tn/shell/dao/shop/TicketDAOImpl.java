package com.tn.shell.dao.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.shop.*;
@Repository
public class TicketDAOImpl  implements TicketDAO{
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public List<Ticket> getAll() {
		 List<Ticket> result = em.createQuery("SELECT p FROM Ticket p where p.statut = :statut ", Ticket.class).setParameter("statut", Statut.ACTIF).getResultList();
		    return result;
	}
	 @Transactional
	 public List<Ticket> getticketbydate(String d){
		 List<Ticket> result = em.createQuery("SELECT p FROM Ticket p where p.statut = :statut and p.dates = :date", Ticket.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("date", d).getResultList();
		 if(result.size()>0)
		 return result;
		 else return null;
	 }
	 
 
	  
	 @Transactional
	public void update(Ticket t) {
		Ticket v=em.find(Ticket.class, t.getId());
		 v.setTotal_recu(t.getTotal_recu());
		 v.setTotal_rendu(t.getTotal_rendu());
		 v.setGen(t.getGen());
		 v.setDates(t.getDates());
		 v.setStatut(t.getStatut());
		em.merge(v);
	}
	  
	 @Transactional
	public void save(Ticket t) {
		 em.persist(t);
		
	}
	@Transactional
	public Ticket getbyvaleur(Integer t) {
		 List<Ticket> TicketListem=em.createQuery("SELECT u FROM  Ticket u where u.id = :valeur",Ticket.class).setParameter("valeur", t).getResultList();
	        
	        if (TicketListem.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return TicketListem.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}

}
