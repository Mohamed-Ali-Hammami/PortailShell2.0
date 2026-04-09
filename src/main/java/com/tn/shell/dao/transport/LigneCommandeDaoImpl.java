package com.tn.shell.dao.transport;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.transport.*;

 
@Repository
public class LigneCommandeDaoImpl implements LigneCommandedao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignecommande c) {
		em.persist(c);
		
	}
	 @Transactional
	 public List<Lignecommande> getLcbyBL(Bonlivraison bl){
		 List<Lignecommande> result = em.createQuery("SELECT c FROM Lignecommande c where c.statut = :statut and c.bl.numero = :numero and c.bl.statut = :statut2", Lignecommande.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("numero", bl.getNumero())
				 .setParameter("statut2", Statut.ACTIF)
				  
				 .getResultList();
		 return result;
	 }
	 @Transactional
	 public Lignecommande getLignecommandebyproduit(Integer p ,Facture f) {
		 List<Lignecommande> result = em.createQuery("SELECT c FROM Lignecommande c where c.statut = :statut "
		 		+ "and c.produit.id = :id and c.facture.numero = :numero", Lignecommande.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("id", p ) 
				 .setParameter("numero", f.getNumero()).getResultList();
		 
		 if (result.size() > 0){
	        	System.out.println("objet trouvé "+result.get(0).getId()+"\n\n\n");
	            return result.get(0);}
	        else{
	        	System.out.println("\n\nl  objet lignecoamanse n exsite pas\n\n");
	            return null;}   
		 
		   
	       
	 }
	 @Transactional
	 public List<Lignecommande> getLcbyf(Facture bl){
		 List<Lignecommande> result = em.createQuery("SELECT c FROM Lignecommande c where c.statut = :statut and c.bl.numero = :numero", Lignecommande.class).setParameter("statut", Statut.ACTIF).setParameter("numero", bl.getBl().getNumero()).getResultList();
		 return result;
	 }
	 @Transactional	 
	 public List<Lignecommande> getAll(){
		 List<Lignecommande> result = em.createQuery("SELECT c FROM Lignecommande c where c.statut = :statut", Lignecommande.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	public void update(Lignecommande c) {
		Lignecommande lc=em.find(Lignecommande.class, c.getId());
		 lc.setDates(c.getDates());
		 lc.setDate(c.getDate());
		lc.setTauxtva(c.getTauxtva());
		em.merge(lc);
		
	}
	 
	 @Transactional
		public void delete(Lignecommande c) {
			Lignecommande lc=em.find(Lignecommande.class, c.getId());
			//lc.setStatut(Statut.DEACTIF);			 
			em.remove(lc);
			
		}
	 
	 public List<Lignecommande>getAllventepardatearticle(Produit a,String s){
			List<Lignecommande> LignecommandeListem = em.createQuery(
					"SELECT u FROM  Lignecommande u where u.statut = :statut and u.dates = :status and u.produit.id = :nom ",
					Lignecommande.class).setParameter("statut", Statut.ACTIF).setParameter("status", s)
					.setParameter("nom", a.getId()).getResultList();

			if (LignecommandeListem.size() > 0) {
				System.out.println("objet trouvé\n");
				return LignecommandeListem;
			} else {
				System.out.println("l  objet n exsite pas");
				return null;
			}
		}
	 
	 public List<Lignecommande>getAllventepardate(String s){
		 List<Lignecommande> LignecommandeListem = em.createQuery(
					"SELECT u FROM  Lignecommande u where u.statut = :statut and u.dates = :status  ",
					Lignecommande.class).setParameter("statut", Statut.ACTIF).setParameter("status", s)
					.getResultList();

			if (LignecommandeListem.size() > 0) {
				System.out.println("objet trouvé\n");
				return LignecommandeListem;
			} else {
				System.out.println("l  objet n exsite pas");
				return null;
			}
	 }
	public double sumdtransport(Date d1,Date d2) {
		Query q = em.createQuery ("SELECT SUM(u.transport) FROM Lignecommande u where u.date BETWEEN :d1 and :d2 and u.statut = :statut  ",
				Lignecommande.class).setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF);
		Double result = (Double) q.getSingleResult ();
		return result;
	}
	
	public double sumdtransportv(Date d1,Date d2,int id) {
		Query q = em.createQuery ("SELECT SUM(u.transport) FROM Lignecommande u where u.date BETWEEN :d1 and :d2 and u.statut = :statut and u.bl.vhecule.id = :id ",
				Lignecommande.class).setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF).setParameter("id", id);
		Double result = (Double) q.getSingleResult ();
		if(result !=null)
		return result;
		else return 0;
	}
	 
}
