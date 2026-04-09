package com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.shop.*;

@Repository
public class LigneAlimentationDaoImpl implements LigneAlimentaiondao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Lignealimentation c) {
		em.persist(c);

	}
	@Transactional
	public List<Lignealimentation> getLignebyachat(Achat achat){
		List<Lignealimentation> result = em.createQuery(
				"SELECT c FROM Lignealimentation c where c.statut = :statut and c.achat.id = :id ",
				Lignealimentation.class).setParameter("statut", Statut.ACTIF).setParameter("id", achat.getId())
			 

				.getResultList();
		if (result.size() > 0)
			return result;
		else
			return null;
	}
 
	
	public double getlisttransferbydateandproduit(String d, Produit p) {
		Query q = em.createQuery ("SELECT SUM(c.quantite) FROM Lignealimentation c where c.statut = :statut and c.produit.id = :id and c.dates = :dates")
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId())
				.setParameter("dates", d);
		try {
		Double result = (Double) q.getSingleResult ();
		
			return result;}
			catch(Exception e) {return 0;}
	}
	public double getlisttransferbydateandproduit2(Date d,Date d2, Produit p) {
		Query q = em.createQuery ("SELECT SUM(c.quantite) FROM Lignealimentation c where c.statut = :statut and c.produit.id = :id and c.date  BETWEEN :dates and :d2")
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId())
				.setParameter("dates", d).setParameter("d2", d2);
		try {
		Double result = (Double) q.getSingleResult ();
		
			return result;}
			catch(Exception e) {return 0;}
	}
	@Transactional
	public List<Lignealimentation> getbetwendates(Date d1,Date d2,Produit p){
List<Lignealimentation> result = em.createQuery("SELECT a FROM Lignealimentation a  where a.statut = :statut and   a.produit.id = :p and a.date BETWEEN :d1 and :d2 ", Lignealimentation.class)
				
				.setParameter("statut", Statut.ACTIF).setParameter("p",p.getId()).setParameter("d1", d1).setParameter("d2", d2).getResultList();
		if (result.size() > 0) {
			System.out.println("objet caisse trouvé " + "\n\n\n");
		return 
				
				result;}
		else {
			
			System.out.println("pas de ligne d achat  avec ces critères" +" "+ "\n\n\n");
			return null;}
	}
	@Transactional
	public double getchiffreAffaireAchat(Famillearticle e) {
		double res = 0;
		List result = em.createQuery(
				"SELECT SUM(totalttc) FROM Lignealimentation c where c.statut = :statut and c.produit.famille.id = :produit",
				Lignealimentation.class).setParameter("statut", Statut.ACTIF).setParameter("produit", e.getCode())
				.getResultList();
		for (Object p : result)

			res = res + Double.parseDouble(p + "");
		return res;
	}

	@Transactional
	public double gettvaAchat(Famillearticle e) {
		double res = 0;
		List result = em.createQuery(
				"SELECT SUM(totaltva) FROM Lignealimentation c where c.statut = :statut and c.produit.famille.id = :produit",
				Lignealimentation.class).setParameter("statut", Statut.ACTIF).setParameter("produit", e.getCode())
				.getResultList();
		for (Object p : result)

			res = res + Double.parseDouble(p + "");
		return res;
	};

	@Transactional
	public List<Lignealimentation> getAll() {
		List<Lignealimentation> result = em
				.createQuery("SELECT c FROM Lignealimentation c where c.statut = :statut", Lignealimentation.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void update(Lignealimentation c) {
		Lignealimentation lc = em.find(Lignealimentation.class, c.getId());

		lc.setQuantite(c.getQuantite());
		lc.setProduit(c.getProduit());
		lc.setDate(c.getDate());
		lc.setDates(c.getDates());
		em.merge(lc);

	}

	@Transactional
	public void delete(Lignealimentation c) {
		Lignealimentation lc = em.find(Lignealimentation.class, c.getId());
		lc.setStatut(Statut.DEACTIF);
		em.merge(lc);

	}

}
