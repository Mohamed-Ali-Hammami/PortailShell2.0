package com.tn.shell.dao.gestat;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

@Repository
public class CaisseDaoImpl implements CaisseDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Caisse> getAll() {
		List<Caisse> result = em.createQuery("SELECT a FROM Caisse a  where a.statut = :statut", Caisse.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Caisse c) {
		em.persist(c);
	}
	@Transactional
	public List<Caisse> getCaissebystatusfacture(Status status,Fournisseur f){
		List<Caisse> result = em.createQuery("SELECT a FROM Caisse a  where a.statut = :statut and a.factureCaisse.status = :status  and a.fournisseur.nom = :nom", Caisse.class)
				.setParameter("statut", Statut.ACTIF).setParameter("status", status).setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	@Transactional
	public List<Caisse> getbetwendates(Date d1,Date d2){
List<Caisse> result = em.createQuery("SELECT a FROM Caisse a  where a.statut = :statut and a.date BETWEEN :d1 and :d2 ", Caisse.class)
				
				.setParameter("statut", Statut.ACTIF).setParameter("d1", d1).setParameter("d2", d2).getResultList();
		if (result.size() > 0) {
		return 
				
				result;}
		else {
			
			return null;}
	}
	@Transactional
	public Caisse getCaissebydate(String date,Poste poste) {
		List<Caisse> result = em.createQuery("SELECT a FROM Caisse a  where a.statut = :statut and a.dates = :date and a.poste = :poste", Caisse.class)
				
				.setParameter("statut", Statut.ACTIF).setParameter("date", date).setParameter("poste", poste).getResultList();
		if (result.size() > 0) {
		return 
				
				result.get(0);}
		else {
			
			return null;}
	}
	public Caisse getCaissebydates(String date) {
List<Caisse> result = em.createQuery("SELECT a FROM Caisse a  where a.statut = :statut and a.dates = :date ", Caisse.class)
				
				.setParameter("statut", Statut.ACTIF).setParameter("date", date).getResultList();
		if (result.size() > 0) {
		return 
				
				result.get(0);}
		else return null;
	}
	 
	@Transactional
	public List<Caisse> getCaissebyfacture(Fournisseur f){
		List<Caisse> result = em.createQuery("SELECT a FROM Caisse a  where a.statut = :statut   and a.fournisseur.nom = :nom", Caisse.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	@Transactional
	public Caisse getmaxcode() {
		List<Caisse> result = em
				.createQuery("SELECT a FROM Caisse a  where a.statut = :statut  and a.id =(select MAX(b.id) from Caisse b)", Caisse.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	@Transactional
	public Caisse getCaissebyid(Integer id) {
		return em.find(Caisse.class, id);
	}
	@Transactional
	public void update(Caisse c) {
		Caisse caisse = em.find(Caisse.class, c.getId());
	//	DecimalFormat df = new DecimalFormat("0.000");
		 //caisse.setTotalventesmagasin(c.getTotalventesmagasin());
		 caisse.setTotalachat(c.getTotalachat());
		// caisse.setTotalbonstation(c.getTotalbonstation());		
		 caisse.setTotalcheque(c.getTotalcheque());
		 caisse.setTotalcredit(c.getTotalcredit());
		 caisse.setCartebanquaire(c.getCartebanquaire());
		 caisse.setTotalcreditanterieur(c.getTotalcreditanterieur());	 
		 caisse.setTotaldepense(c.getTotaldepense());
		 caisse.setTotalespece(c.getTotalespece());
		 //caisse.setTotallavage(c.getTotallavage());
		 caisse.setTotalmanque(c.getTotalmanque());
		 //caisse.setTotalventeslubrifiant(c.getTotalventeslubrifiant());
		 //caisse.setTotalventesfiltre(c.getTotalventesfiltre());
		 //caisse.setTotalventesaccessoire(c.getTotalventesaccessoire());
		 caisse.setPompiste1(c.getPompiste1());
		 caisse.setPompiste2(c.getPompiste2());
		 caisse.setTotalventecarburant(c.getTotalventecarburant());
		 caisse.setRetourcuve(c.getRetourcuve());			 
		 caisse.setPoste(c.getPoste());
		 caisse.setRemarques(c.getRemarques());
		 caisse.setCarteshell(c.getCarteshell());	
		/* double tc=0; double tcs=0;
		 if(caisse.getTotalcreditanterieur()!=0)
		  tc=caisse.getTotalventecarburant()+caisse.getTotalcreditanterieur();
		 else  
			 tc=caisse.getTotalventecarburant();
		 
		  tcs=caisse.getTotalachat()+caisse.getTotalcheque()
		 +caisse.getTotalcredit()+caisse.getCartebanquaire()+caisse.getTotaldepense()
		 +caisse.getTotalmanque()+ caisse.getRetourcuve()+caisse.getCarteshell();		  */
		 caisse.setTotalcaisse(c.getTotalcaisse());	
		 caisse.setObservation(c.getObservation());		 
		  caisse.setRemarques(c.getRemarques()); 
		em.merge(caisse);

	}

 
	@Transactional
	public List<Caisse> getArticlebyfournisseur(Fournisseur f){
		List<Caisse> result = em.createQuery(
				"SELECT b FROM Caisse  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				Caisse.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
}
