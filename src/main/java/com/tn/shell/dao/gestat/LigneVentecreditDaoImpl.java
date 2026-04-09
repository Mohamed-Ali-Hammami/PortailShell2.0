package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;

 

 
public class LigneVentecreditDaoImpl implements LigneVentecreditDAO {

	/*@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Ligneventecredit c) {
		em.persist(c);

	}
	 
	@Transactional
	public double getSommeht(Date d, Poste poste) {
		double res = 0;
		List result = em
				.createQuery("SELECT SUM(montantht) FROM Ligneventecredit c where c.statut = :statut and c.poste = :poste",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).getResultList();
		for (Object p : result)
			res = res + Double.parseDouble(p + "");
		return res;
	}
	 

	@Transactional
	public double getSommetva(Date d, Poste poste) {
		double res = 0;
		List result = em
				.createQuery("SELECT SUM(totaltva) FROM Ligneventecredit c where c.statut = :statut and c.poste = :poste",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).getResultList();
		for (Object p : result)
			res = res + Double.parseDouble(p + "");
		return res;
	}

	@Transactional
	public double getSommettc(Date d, Poste poste) {
		double res = 0;
		List result = em
				.createQuery("SELECT SUM(totalttc) FROM Ligneventecredit c where c.statut = :statut and c.poste = :poste",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).getResultList();
		for (Object p : result)
			res = res + Double.parseDouble(p + "");
		return res;
	}

	@Transactional
	public List<Ligneventecredit> getAllcreditbyclientanddate(Clientgestat client){
		List<Ligneventecredit> result = em
				.createQuery("SELECT c FROM Ligneventecredit c where c.statut = :statut and c.credit.client.code = :client",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("client", client.getCode()).getResultList();
		if(result.size()>0) {
			return result;
		}
		else return result;
	}
	@Transactional
	public List<Ligneventecredit> getAllcreditbyclientanddates(Clientgestat client,String date){
		List<Ligneventecredit> result = em
				.createQuery("SELECT c FROM Ligneventecredit c where c.statut = :statut and c.credit.client.code = :client and c.dates = :date",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("client", client.getCode())
				.setParameter("date", date)
				.getResultList();
		if(result.size()>0) {
			System.out.println("getAllcreditbyclientanddates" +result.size());
			return result;
			
		}
		else { 
			System.out.println("getAllcreditbyclientanddates null");
			return null;
 		}
	}
	@Transactional
	public List<Ligneventecredit> getAllbyProduit(Produit p){
		double res = 0;
		List result = em
				.createQuery("SELECT c FROM Ligneventecredit c where c.statut = :statut and c.produit.id = :id",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId()).getResultList();

		return result;
	}
	 
	@Transactional
	public List<Ligneventecredit> getAllventeparposte(Date d, Poste poste) {
		List<Ligneventecredit> result = em
				.createQuery("SELECT c FROM Ligneventecredit c where c.statut = :statut and c.poste = :poste",
						Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).getResultList();
		return result;
	}

	@Transactional
	public List<Ligneventecredit> getAllventeparposteNegatif(Date d, Poste poste) {
		List<Ligneventecredit> result = em.createQuery(
				"SELECT c FROM Ligneventecredit c where c.statut = :statut and c.poste = :poste and c.quantite < :qte",
				Ligneventecredit.class).setParameter("statut", Statut.ACTIF).setParameter("poste", poste)
				.setParameter("qte", 0).getResultList();
		return result;
	}
	@Transactional
	public List<Ligneventecredit>  getligneventebycreditclient(Credit credit){
		List<Ligneventecredit> result = em.createQuery(
				"SELECT c FROM Ligneventecredit c where c.statut = :statut and c.creditclient.id = :poste",
				Ligneventecredit.class).setParameter("statut", Statut.ACTIF).setParameter("poste", credit.getId())
				.getResultList();
		
		if(result.size()>0)
		return result;
		else return null;
	}
	@Transactional
	public List<Ligneventecredit> getAll() {
		List<Ligneventecredit> result = em
				.createQuery("SELECT c FROM Ligneventecredit c where c.statut = :statut", Ligneventecredit.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void update(Ligneventecredit c) {
		Ligneventecredit lc = em.find(Ligneventecredit.class, c.getId());
	 
		 
		lc.setQuantite(c.getQuantite());
		 
		lc.setDate(c.getDate());
		em.merge(lc);

	}

	@Transactional
	public void delete(Ligneventecredit c) {
		Ligneventecredit lc = em.find(Ligneventecredit.class, c.getId());
		lc.setStatut(Statut.DEACTIF);
		em.merge(lc);

	}
*/
}
