package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;

@Repository
public class DepensegestatDaoImpl implements DepensegestatDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Depensegestat depense) {
		em.persist(depense);

	}
	@Transactional
	public double getsummontantbydate( Date d1,Date d2) {
		Query q = em.createQuery(
				"SELECT SUM(c.montant) FROM Depensegestat  c where c.statut = :statut   and c.date BETWEEN :date1 and :date2  "
				).setParameter("statut", Statut.ACTIF)
						.setParameter("date1", d1).setParameter("date2",d2);
				 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional
	public List<Depensegestat> getdepensebetweendateAndFamille(Date d1,Date d2,int f){
		List<Depensegestat> result = em
				.createQuery("SELECT a FROM Depensegestat a where a.statut = :statut and a.date between :d1 and :d2 and a.familledepense.id = :f ",
						Depensegestat.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1", d1)
				.setParameter("d2", d2).setParameter("f",f)
				.getResultList();
		return result;
	}
	@Transactional
	public double getsummontantbydateandfamille( Date d1,Date d2,int f) {
		Query q = em.createQuery(
				"SELECT SUM(c.montant) FROM Depensegestat  c where c.statut = :statut   and c.date BETWEEN :date1 and :date2 and c.familledepense.id = :f "
				).setParameter("statut", Statut.ACTIF)
						.setParameter("date1", d1).setParameter("date2",d2).setParameter("f",f);
				 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional
	public List<Depensegestat> getAll() {
		List<Depensegestat> result = em
				.createQuery("SELECT a FROM Depensegestat a where a.statut = :statut  order by a.id Desc",
						Depensegestat.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	@Transactional
	public List<Depensegestat> getdepensebetweendate(Date d1,Date d2){
		List<Depensegestat> result = em
				.createQuery("SELECT a FROM Depensegestat a where a.statut = :statut and a.date between :d1 and :d2 ",
						Depensegestat.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		return result;
	}
	@Transactional
	public Depensegestat getmaxdepense() {
		List<Depensegestat> result = em.createQuery("SELECT a FROM Depensegestat a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Depensegestat.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	         return result.get(0);}
	     else{
	         return null;} 
	}

	@Transactional
	public void update(Depensegestat depense) {
		Depensegestat p = em.find(Depensegestat.class, depense.getId());
		p.setDate(depense.getDate());
		p.setLibelle(depense.getLibelle());
		p.setMontant(depense.getMontant());	 
		p.setFamilledepense(depense.getFamilledepense());

		em.merge(p);
	}

	@Transactional
	public List<Depensegestat> getdepensebyCaisse(Caisse c) {
		List<Depensegestat> result = new ArrayList<Depensegestat>();
		result = em
				.createQuery("SELECT a FROM Depensegestat a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0 order by a.familledepense.id ",
						Depensegestat.class)
				.setParameter("statut", Statut.ACTIF)			
				.setParameter("caiise", c.getId()).
				getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}

	@Transactional
	public List<Depensegestat> getdepensebydate(String c) {
		List<Depensegestat> result = new ArrayList<Depensegestat>();
		result = em
				.createQuery("SELECT a FROM Depensegestat a where a.statut = :statut   and a.dates = :caiise  order by a.familledepense.id",
						Depensegestat.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caiise", c).getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}
	
	@Transactional
	public double getdepensebydates(String c) {
	/*	Query q = em.createQuery ("SELECT SUM(u.montant) FROM Depensegestat  c where c.statut = :statut and a.dates = :caiise",
				Depensegestat.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", c);
		Double result = (Double) q.getSingleResult ();
		if(result!=null)
		return result;*/
		return 0;
		
	 
	}

	@Transactional
	public void delete(Depensegestat depense) {
		Depensegestat p = em.find(Depensegestat.class, depense.getId());
		p.setStatut(depense.getStatut());
		em.merge(p);
	}

	public Depensegestat getdepensebyid(Depensegestat d) {
		List<Depensegestat> result = new ArrayList<Depensegestat>();
		result = em
				.createQuery("SELECT a FROM Depensegestat a where a.statut = :statut   and a.id = :caiise",
						Depensegestat.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caiise", d.getId()).getResultList();

		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}
}
