package com.tn.shell.dao.transport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class DepenseDaoImpl implements DepenseDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Depense depense) {
		em.persist(depense);

	}

	@Transactional
	public List<Depense> getAll() {
		List<Depense> result = em
				.createQuery("SELECT a FROM Depense a where a.statut = :statut  order by a.id Desc", Depense.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	@Transactional
	public Depense getdepensebyid(Integer id,String libelle,String lib,String dates) {
		List<Depense> result=new ArrayList<Depense>();
		 result = em
				.createQuery("SELECT  c FROM Depense c where c.statut = :statut and c.vhecule.id = :id   and c.libelle = :lib and c.dates = :dates", Depense.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", id)				
				.setParameter("lib", lib)
				 .setParameter("dates", dates)
				.getResultList();
		if(result.size()>0)
		return result.get(0);
		else return null;
	}
	@Transactional
	public List<Depense> getdepensebydates(String dates){
		List<Depense> result = em
				.createQuery("SELECT a FROM Depense a where a.statut = :statut  and a.dates = :d", Depense.class)
				.setParameter("statut", Statut.ACTIF).setParameter("d", dates).getResultList();
		if(result.size()>0)return result; else return new ArrayList<Depense>();
	}
	@Transactional
	public double getdepenseautrebyvehicule(Vhecule v,Date d1,Date d2) {
		double r = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<Depense> l=new ArrayList<Depense>();
		List<Depense> result = em
				.createQuery("SELECT  c FROM Depense c where c.statut = :statut and c.vhecule.id = :id and c.familledepense.libelle = :libelle", Depense.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", v.getId())
				.setParameter("libelle", "autre")
				.getResultList();
		try {
		for(Depense f:result) {
		 
			Date d = dateFormat.parse(dateFormat.format(d1));
			Date d3 = dateFormat.parse(dateFormat.format(d2));
			 if (d.compareTo(dateFormat.parse(dateFormat.format(f.getDate())))<=0 &&
					 d3.compareTo(dateFormat.parse(dateFormat.format(f.getDate())))>=0 ) 
			{
		      l.add(f);
			 }}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (l != null)
			for (Depense c : l)
				 r=c.getMontant();
		else r=0;
		return r;
	}
	
	@Transactional
	public double getdepensebyvehicule(Vhecule v,Date d1,Date d2) {
		double r = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<Depense> l=new ArrayList<Depense>();
		List<Depense> result = em
				.createQuery("SELECT  c FROM Depense c where c.statut = :statut and c.vhecule.id = :id and c.familledepense.libelle = :libelle", Depense.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", v.getId())
				.setParameter("libelle", "carburant")
				.getResultList();
		try {
			for(Depense f:result) {
			 
				Date d = dateFormat.parse(dateFormat.format(d1));
				Date d3 = dateFormat.parse(dateFormat.format(d2));
				 if (d.compareTo(dateFormat.parse(dateFormat.format(f.getDate())))<=0 &&
						 d3.compareTo(dateFormat.parse(dateFormat.format(f.getDate())))>=0 ) 
				{
			      l.add(f);
				 }}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (l != null)
				for (Depense c : l)
				 r=c.getMontant();
		else r=0;
		return r;
	}


	@Transactional
	public void update(Depense depense) {
		Depense p = em.find(Depense.class, depense.getId());
		p.setDate(depense.getDate());
		p.setLibelle(depense.getLibelle());
		p.setMontant(depense.getMontant());
		p.setVhecule(depense.getVhecule());

		em.merge(p);
	}

	@Transactional
	public void delete(Depense depense) {
		Depense p = em.find(Depense.class, depense.getId());
		p.setStatut(depense.getStatut());
		em.merge(p);
	}

	public double sumdepense(Date d1, Date d2) {
		Query q = em.createQuery ("SELECT SUM(u.montant) FROM Depense u where u.date BETWEEN :d1 and :d2 and u.statut = :statut  ",
				Depense.class).setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF);
		Double result = (Double) q.getSingleResult ();
		if(result !=null)
		return result;
		else return 0;
	 
	}

	public double sumdepensebyfamille(int famille, Date d1, Date d2) {
		Query q = em.createQuery ("SELECT SUM(u.montant) FROM Depense u where u.date BETWEEN :d1 and :d2 and u.statut = :statut and u.familledepense.id = :id ",
				Depense.class).setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF).setParameter("id", famille);
		Double result = (Double) q.getSingleResult ();
		
		return result;
	}
	
	public List<Depense> getdepensebetweendate(Date d1,Date d2){
		List<Depense> result = em
				.createQuery("SELECT  c FROM Depense c where c.statut = :statut and c.date BETWEEN :d1 and :d2", Depense.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		
		return result;
	}
}

