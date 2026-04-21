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
	private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";
	@Transactional
	public void save(Depense depense) {
		em.persist(depense);

	}

	@Transactional
	public List<Depense> getAll() {
		try {
			List<Depense> result = em.createQuery("SELECT a FROM Depense a where a.statut = :statut  order by a.id Desc", Depense.class)
					.setParameter("statut", Statut.ACTIF).getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT d.id,d.date,d.dates,d.libelle,d.montant,d.statut,d.vheculeid,d.familledepenseid,"
						+ "v.matricule,f.libelle "
						+ "FROM depense d "
						+ "LEFT JOIN vhecule v ON v.id = d.vheculeid "
						+ "LEFT JOIN familledepensetransport f ON f.id = d.familledepenseid "
						+ "WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(d.id AS UNSIGNED) DESC")
				.getResultList();
		return mapRows(rows);
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
		try {
			List<Depense> result = em
					.createQuery("SELECT a FROM Depense a where a.statut = :statut  and a.dates = :d", Depense.class)
					.setParameter("statut", Statut.ACTIF).setParameter("d", dates).getResultList();
			if(result.size()>0)return result; else return new ArrayList<Depense>();
		} catch (RuntimeException ex) {
			return em.createNativeQuery(
					"SELECT * FROM depense WHERE " + ACTIVE_STATUS_SQL + " AND dates = :d ORDER BY id DESC",
					Depense.class)
					.setParameter("d", dates)
					.getResultList();
		}
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
		try {
			return em
					.createQuery("SELECT  c FROM Depense c where c.statut = :statut and c.date BETWEEN :d1 and :d2", Depense.class)
					.setParameter("statut", Statut.ACTIF)
					.setParameter("d1", d1)
					.setParameter("d2", d2)
					.getResultList();
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT d.id,d.date,d.dates,d.libelle,d.montant,d.statut,d.vheculeid,d.familledepenseid,"
							+ "v.matricule,f.libelle "
							+ "FROM depense d "
							+ "LEFT JOIN vhecule v ON v.id = d.vheculeid "
							+ "LEFT JOIN familledepensetransport f ON f.id = d.familledepenseid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " AND d.date BETWEEN :d1 AND :d2 "
							+ "ORDER BY CAST(d.id AS UNSIGNED) DESC")
					.setParameter("d1", d1)
					.setParameter("d2", d2)
					.getResultList();
			return mapRows(rows);
		}
	}

	private List<Depense> mapRows(List<Object[]> rows) {
		List<Depense> depenses = new ArrayList<Depense>();
		for (Object[] row : rows) {
			Depense depense = new Depense();
			depense.setId(TransportTsvMapper.asInteger(row[0]));
			depense.setDate(TransportTsvMapper.asDate(row[1]));
			depense.setDates(TransportTsvMapper.asString(row[2]));
			depense.setLibelle(TransportTsvMapper.asString(row[3]));
			depense.setMontant(TransportTsvMapper.asDouble(row[4]));
			depense.setStatut(TransportTsvMapper.asStatut(row[5]));
			Vhecule vhecule = new Vhecule();
			vhecule.setId(TransportTsvMapper.asInteger(row[6]));
			vhecule.setMatricule(TransportTsvMapper.asString(row[8]));
			depense.setVhecule(vhecule);
			Familledepensetransport famille = new Familledepensetransport();
			famille.setId(TransportTsvMapper.asInteger(row[7]));
			famille.setLibelle(TransportTsvMapper.asString(row[9]));
			depense.setFamilledepense(famille);
			depenses.add(depense);
		}
		return depenses;
	}
}

