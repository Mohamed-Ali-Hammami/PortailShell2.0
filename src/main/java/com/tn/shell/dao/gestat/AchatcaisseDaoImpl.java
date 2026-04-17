package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

@Repository
public class AchatcaisseDaoImpl implements AchatcaisseDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Achatcaisse> getAll() {
		List<Achatcaisse> result = em.createQuery("SELECT a FROM Achatcaisse a  where a.statut = :statut", Achatcaisse.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Achatcaisse c) {
		em.persist(c);
	}
	@Transactional
	public List<Achatcaisse> getAchatcaissebystatusfacture(Status status,Fournisseur f){
		List<Achatcaisse> result = em.createQuery("SELECT a FROM Achatcaisse a  where a.statut = :statut and a.factureAchatcaisse.status = :status  and a.fournisseur.nom = :nom", Achatcaisse.class)
				.setParameter("statut", Statut.ACTIF).setParameter("status", status).setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	
	@Transactional
	public List<Achatcaisse> getachatyCaisse(Caisse c){
		List<Achatcaisse> result=new ArrayList<Achatcaisse>();
	  result = em.createQuery("SELECT a FROM Achatcaisse a where a.statut = :statut   and a.caisse.id = :caiise and a.montant !=0 ", Achatcaisse.class)
				.setParameter("statut", Statut.ACTIF) 
				.setParameter("caiise",c.getId()) 
				.getResultList();
		
		if(result.size()>0)
	    return result;
		else return result;
	}
	
	@Transactional
	public List<Achatcaisse> getachatydate(String c){
		List<Achatcaisse> result=new ArrayList<Achatcaisse>();
	  result = em.createQuery("SELECT a FROM Achatcaisse a where a.statut = :statut   and a.dates = :caiise", Achatcaisse.class)
				.setParameter("statut", Statut.ACTIF) 
				.setParameter("caiise",c) 
				.getResultList();
		
		if(result.size()>0)
	    return result;
		else return result;
	}

	@Transactional
	public List<Achatcaisse> getAchatcaissebyfacture(Fournisseur f){
		List<Achatcaisse> result = em.createQuery("SELECT a FROM Achatcaisse a  where a.statut = :statut   and a.fournisseur.nom = :nom", Achatcaisse.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	@Transactional
	public Integer getmaxcode() {
		List<Achatcaisse> result = em
				.createQuery("SELECT a FROM Achatcaisse a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Achatcaisse.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public Achatcaisse getAchatcaissebyid(Integer id) {
		List<Achatcaisse> result=new ArrayList<Achatcaisse>();
		  result = em.createQuery("SELECT a FROM Achatcaisse a where a.statut = :statut   and a.id = :caiise", Achatcaisse.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("caiise",id) 
					.getResultList();
			
			if(result.size()>0)
		    return result.get(0);
			else return null;
		 
	}
	@Transactional
	public void update(Achatcaisse c) {
		Achatcaisse a = em.find(Achatcaisse.class, c.getId());
		a.setMontant(c.getMontant());
		a.setLibelle(c.getLibelle());
		a.setDates(c.getDates());
		a.setDate(c.getDate());
		em.merge(a);

	}
 
	@Transactional
	public List<Achatcaisse> getArticlebyfournisseur(Fournisseur f){
		List<Achatcaisse> result = em.createQuery(
				"SELECT b FROM Achatcaisse  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				Achatcaisse.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
}
