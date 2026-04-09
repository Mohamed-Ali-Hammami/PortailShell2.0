package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.transport.*;
@Repository
public class FactureDaoImpl implements FactureDAO {
	 @PersistenceContext
	 private EntityManager em;
	 @Transactional
	public void update(Facture facture) {
		em.merge(facture);
	}
	 @Transactional
	public void save(Facture facture) {
		em.persist(facture);
		
	}
	 @Transactional
	public List<Facture> getAll() {
		List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut order by a.numero Desc"
				+ "", Facture.class).setParameter("statut", Statut.ACTIF).getResultList();
	    return result;
	}
	 @Transactional
	 public Facture getMaxfacture() {
List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut and a.numero=(select MAX(b.numero) from Facture b)", Facture.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	     	System.out.println("objet trouv� "+"\n\n\n");
	         return result.get(0);}
	     else{
	     	System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
	         return null;} 
		 }
	 @Transactional
	 public List<Facture> getAllPasager(){
List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut order by a.numero Desc", Facture.class).setParameter("statut", Statut.ACTIF)
  .getResultList();
         return result; 
	 }
	 @Transactional
	 public List<Facture> getAllTransport(){
List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut order by a.numero Desc", Facture.class).setParameter("statut", Statut.ACTIF)
                 .getResultList();
         return result;  
	 }
	 @Transactional
	 public List<Facture> getfacturebetwenndate(Date d1,Date d2){
		 List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut and a.date BETWEEN :d1 and :d2 ORDER BY a.numero DESC ", Facture.class)
				 .setParameter("statut", Statut.ACTIF)				 
				 .setParameter("d1", d1).setParameter("d2", d2 )  .getResultList();
				 		 return result;   
	}
	@Transactional
	public void delete(Facture facture) {
		Facture c=em.find(Facture.class, facture.getNumero());
		 
		em.remove(c);
	}
	public List<Facture> getbydate(Date d1, Date d2) {
		 List<Facture> FactureachatListem=em.createQuery("SELECT u FROM  Facture u where u.date between :d1 and :d2 and u.statut = :statut",Facture.class).setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        return FactureachatListem;
	}
	public Facture getfacturebycode(String code) {
		 List<Facture> FactureachatListem=em.createQuery("SELECT u FROM  Facture u where u.codes = :code and u.statut = :statut",Facture.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FactureachatListem.size() > 0){
	        	System.out.println("\n\n\n\nfacture bydate trouv�\n\n\n\n");
	            return FactureachatListem.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}
	public Facture getBLbycodes(String code) {
List<Facture> BonlivraisonListem=em.createQuery("SELECT u FROM  Facture u where u.codes = :code and u.statut = :statut",Facture.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
        
        if (BonlivraisonListem.size() > 0){
        	System.out.println("objet trouv�\n");
            return BonlivraisonListem.get(0);}
        else{
        	System.out.println("l  objet n exsite pas");
            return null;} 
	}
	public Integer getmaxcode() {
		Query q =  em.createQuery("SELECT MAX(b.code) FROM Facture b  where b.statut = :statut").setParameter("statut", Statut.ACTIF);
	    Integer result=(Integer)q.getSingleResult();
		return result;
	}
	public Facture getBLbycode(Integer code) {
List<Facture> BonlivraisonListem=em.createQuery("SELECT u FROM  Facture u where u.code = :code and u.statut = :statut",Facture.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
        
        if (BonlivraisonListem.size() > 0){
        	System.out.println("objet trouv�\n");
            return BonlivraisonListem.get(0);}
        else{
        	System.out.println("l  objet n exsite pas");
            return null;} 
	}
	 

	 

}

