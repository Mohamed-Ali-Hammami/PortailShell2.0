package com.tn.shell.dao.gestat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;

@Repository
public class RetourcuveDaoImpl implements RetourcuveDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Retourcuve> getAll() {
		List<Retourcuve> result = em.createQuery("SELECT a FROM Retourcuve a  where a.statut = :statut", Retourcuve.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Retourcuve c) {
		em.persist(c);
	}
	 
	@Transactional
	public List<Retourcuve> getRetourcuvebyCaisse(Caisse c){
		List<Retourcuve> result = em.createQuery("SELECT a FROM Retourcuve a  where a.statut = :statut and a.caisse.id = :id and a.montant != 0 ", Retourcuve.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", c.getId()) .getResultList();
		 if(result.size()>0)
			 return result;
		 else return null;
	 
	
 }
	@Transactional
	public Retourcuve getretpourbypompe(Pompe p,Caisse c) {
		List<Retourcuve> result = em.createQuery("SELECT a FROM Retourcuve a  where a.statut = :statut and a.pompe.id = :status and a.caisse.id = :status2 ", Retourcuve.class)
				.setParameter("statut", Statut.ACTIF).setParameter("status", p.getId()).setParameter("status2",c.getId()) .getResultList();
		 if(result.size()>0)
			 return result.get(0);
		 else return null;
	}
	@Transactional
	public List<Retourcuve> getRetourcuvebydate(String c){
		List<Retourcuve> result = em.createQuery("SELECT a FROM Retourcuve a  where a.statut = :statut and a.dates = :status and a.montant !=0 ", Retourcuve.class)
				.setParameter("statut", Statut.ACTIF).setParameter("status", c) .getResultList();
		 if(result.size()>0)
			 return result;
		 else return null;
	}
	
	public long getquantitebyarticledates(Articlecarburant a,String d) {
		Query q = em.createQuery(
				"SELECT SUM(c.quantite) FROM Retourcuve  c where c.statut = :statut and c.pompe.articlecarburant.id = :id and c.dates = :date1"
				 ).setParameter("statut", Statut.ACTIF).setParameter("id", a.getId())
				.setParameter("date1", d);
		 try {
		Long result = (Long) q.getSingleResult();
			if(result!=null)
			return result;
			return 0;
			
	 	} catch (Exception e) {
			return 0;
		} 
	}
	@Transactional
	public Integer getmaxcode() {
		List<Retourcuve> result = em
				.createQuery("SELECT a FROM Retourcuve a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Retourcuve.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public Retourcuve getRetourcuvebyid(Integer id) {
		List<Retourcuve> result = em
				.createQuery("SELECT a FROM Retourcuve a  where a.statut = :statut  and a.id = :id", Retourcuve.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("id",id)
				.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	@Transactional
	public void update(Retourcuve c) {
		Retourcuve a = em.find(Retourcuve.class, c.getId());
	 a.setMontant(c.getMontant());
	 a.setPompe(c.getPompe());
	 a.setDates(c.getDates());
	 a.setQuantite(c.getQuantite());
		em.merge(a);

	}

  
}
