package com.tn.shell.dao.gestat;

import java.math.BigDecimal;
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
public class DepenseChequeDaoImpl implements DepenseChequeDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(DepenseCheque depense) {
		em.persist(depense);

	}
	
	public double getdepensebetweendateandfamille2(Date d1, Date d2,int fonction){
		 Query q =  em
				.createQuery("SELECT SUM(a.montant) FROM DepenseCheque a where a.statut = :statut and a.familledepense.id = :typeDepense and a.date BETWEEN :d1 and :d2 and a.montant>0"
						).setParameter("statut", Statut.ACTIF).setParameter("typeDepense", fonction).setParameter("d1", d1).setParameter("d2", d2)
				 ;
		 try {
				BigDecimal result = (BigDecimal) q.getSingleResult();

				return result.doubleValue();
			} catch (Exception e) {
				return 0;
			}

	}
	
	public double  getDepenseBylibelle(String fonction,Date d1,Date d2) {
		 Query q=em.createQuery(" SELECT SUM(a.montant)  from DepenseCheque a  where a.statut = :statut  and a.libelle like  '%"+fonction+"%'   and a.date BETWEEN :d1 and :d2"
				  ).setParameter("statut", Statut.ACTIF).setParameter("d1", d1).setParameter("d2", d2); 
		 try {
				BigDecimal result = (BigDecimal) q.getSingleResult();
                System.out.println(result);
				return result.intValue();
			} catch (Exception e) {
				System.out.println("\n\n\n\n exception " + e.getMessage());
				return 0;
			}
	}
	
	@Transactional
	public BigDecimal getsummontantbydate( Date d1,Date d2) {
		Query q = em.createQuery(
				"SELECT SUM(c.montant) FROM DepenseCheque  c where c.statut = :statut   and c.date BETWEEN :date1 and :date2  "
				).setParameter("statut", Statut.ACTIF)
						.setParameter("date1", d1).setParameter("date2",d2);
				 
		try {
			BigDecimal result =   (BigDecimal) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}
	@Transactional
	public double getsummontantbydateandfamille( Date d1,Date d2,int f) {
		Query q = em.createQuery(
				"SELECT SUM(c.montant) FROM DepenseCheque  c where c.statut = :statut   and c.date BETWEEN :date1 and :date2 and c.familledepense.id = :f "
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
	public List<DepenseCheque> getAll() {
		List<DepenseCheque> result = em
				.createQuery("SELECT a FROM DepenseCheque a where a.statut = :statut  order by a.id Desc",
						DepenseCheque.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	@Transactional
	public List<DepenseCheque> getdepensebetweendate(Date d1,Date d2){
		List<DepenseCheque> result = em
				.createQuery("SELECT a FROM DepenseCheque a where a.statut = :statut and a.date between :d1 and :d2 ",
						DepenseCheque.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		if(result.size()>0)
		return result;
		return null;
	}
	@Transactional
	public DepenseCheque getmaxdepense() {
		List<DepenseCheque> result = em.createQuery("SELECT a FROM DepenseCheque a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", DepenseCheque.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	     	System.out.println("objet trouvé "+"\n\n\n");
	         return result.get(0);}
	     else{
	     	System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
	         return null;} 
	}

	@Transactional
	public void update(DepenseCheque depense) {
		DepenseCheque p = em.find(DepenseCheque.class, depense.getId());
		 p=depense; 
		em.merge(p);
	}

	@Transactional
	public List<DepenseCheque> getdepensebyCaisse(Caisse c) {
		List<DepenseCheque> result = new ArrayList<DepenseCheque>();
		result = em
				.createQuery("SELECT a FROM DepenseCheque a where a.statut = :statut   and a.caisse.id = :caiise and a.montant != 0 order by a.familledepense.id ",
						DepenseCheque.class)
				.setParameter("statut", Statut.ACTIF)			
				.setParameter("caiise", c.getId()).
				getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}

	@Transactional
	public List<DepenseCheque> getdepensebydate(String c) {
		List<DepenseCheque> result = new ArrayList<DepenseCheque>();
		result = em
				.createQuery("SELECT a FROM DepenseCheque a where a.statut = :statut   and a.dates = :caiise  order by a.familledepense.id",
						DepenseCheque.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caiise", c).getResultList();

		if (result.size() > 0)
			return result;
		else
			return result;
	}
	
	@Transactional
	public double getdepensebydates(String c) {
	/*	Query q = em.createQuery ("SELECT SUM(u.montant) FROM DepenseCheque  c where c.statut = :statut and a.dates = :caiise",
				DepenseCheque.class).setParameter("statut", Statut.ACTIF).setParameter("caiise", c);
		Double result = (Double) q.getSingleResult ();
		if(result!=null)
		return result;*/
		return 0;
		
	 
	}

	@Transactional
	public void delete(DepenseCheque depense) {
		DepenseCheque p = em.find(DepenseCheque.class, depense.getId());
		p.setStatut(depense.getStatut());
		em.merge(p);
	}

	public DepenseCheque getdepensebyid(DepenseCheque d) {
		List<DepenseCheque> result = new ArrayList<DepenseCheque>();
		result = em
				.createQuery("SELECT a FROM DepenseCheque a where a.statut = :statut   and a.id = :caiise",
						DepenseCheque.class)
				.setParameter("statut", Statut.ACTIF).setParameter("caiise", d.getId()).getResultList();

		if (result.size() > 0)
			return result.get(0);
		else
			return null;
	}
}
