package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.TransactionDepense;
import com.tn.shell.model.transport.Statut;
 

@Repository
public class JournalDepenseDaoImpl implements JournalDepenseDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(TransactionDepense TransactionDepense) {
		em.persist(TransactionDepense);

	}
	public TransactionDepense getjournalbytipe(String time,String d) {
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a where a.time = :statut and a.dates = :d and a.affecte = :aff", TransactionDepense.class)
				.setParameter("statut", time).setParameter("d", d).setParameter("aff",true ).getResultList();
		if(result.size()>0)
		return result.get(0);
		return null;
	}
	
	public TransactionDepense getmaxjournal(String d) {
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a  where   a.id=(select MAX(b.id) from TransactionDepense b where  b.vhecule = :d and b.kilometrage != 'groupe')",
						TransactionDepense.class)
				.setParameter("d", d) .getResultList();
		if(result.size()>0) {
			System.out.println("objet trouver ");
		return result.get(0);
		}
		else {
			System.out.println("\n\n\n\n objet n existe pas  \n\n ");
		return null;
		}
	}
	
	public List<TransactionDepense> getjournalbytarticle(String article,int id) {
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a where a.article = :statut and a.lignecommande.id = :d and a.lignecommande.statut = :status", TransactionDepense.class)
				.setParameter("statut", article).setParameter("d", id).setParameter("status",Statut.ACTIF).getResultList();
		if(result.size()>0)
		return result ;
		return null;
	}
	
	public List<TransactionDepense> getjournalbytarticle(int id) {
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a where   a.lignecommande.id = :d", TransactionDepense.class)
				 .setParameter("d", id).getResultList();
		if(result.size()>0)
		return result;
		return null;
	}
	@Transactional
	public List<TransactionDepense> getAll() {
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a   order by a.id Desc", TransactionDepense.class)
				 .getResultList();
		return result;
	}
	@Transactional
	public List<TransactionDepense> getJournalbydates(String dates){
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a where   a.dates = :d", TransactionDepense.class)
				 .setParameter("d", dates).getResultList();
		if(result.size()>0)return result; else return null;
	}
	 
	
	 

	@Transactional
	public void update(TransactionDepense TransactionDepense) {
		TransactionDepense p = em.find(TransactionDepense.class, TransactionDepense.getId());
     	 p=TransactionDepense;
		em.merge(p);
	}
	
	@Transactional
	public void update2(TransactionDepense TransactionDepense) {
		TransactionDepense p = em.find(TransactionDepense.class, TransactionDepense.getId());
     	 p.setAffecte(TransactionDepense.isAffecte());
		em.merge(p);
	}

	@Transactional
	public void delete(TransactionDepense TransactionDepense) {
		TransactionDepense p = em.find(TransactionDepense.class, TransactionDepense.getId());
		 
		em.merge(p);
	}

 
	public List<TransactionDepense> getJournalbetweendate(Date d1,Date d2){
		List<TransactionDepense> result = em
				.createQuery("SELECT  c FROM TransactionDepense c where  c.date BETWEEN :d1 and :d2", TransactionDepense.class)
				 
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		
		return result;
	}
	
	public List<TransactionDepense> getjournalNonAffecter(){
		List<TransactionDepense> result = em
				.createQuery("SELECT a FROM TransactionDepense a where   a.affecte = :aff", TransactionDepense.class)
				 .setParameter("aff",false ).getResultList();
		if(result.size()>0) {
			System.out.println("\n\n\n resultat journal " +result.size());
		return result;
		}
		return null;
	}
}
