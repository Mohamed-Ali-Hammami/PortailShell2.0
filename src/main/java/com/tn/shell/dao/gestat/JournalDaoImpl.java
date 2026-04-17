package com.tn.shell.dao.gestat;

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

import com.tn.shell.model.gestat.TransactionCredit;
import com.tn.shell.model.transport.*;
 

@Repository
public class JournalDaoImpl implements JournalDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(TransactionCredit TransactionCredit) {
		em.persist(TransactionCredit);

	}
	public TransactionCredit getjournalbytipe(String time,String d) {
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a where a.time = :statut and a.dates = :d and a.affecte = :aff", TransactionCredit.class)
				.setParameter("statut", time).setParameter("d", d).setParameter("aff",true ).getResultList();
		if(result.size()>0)
		return result.get(0);
		return null;
	}
	
	public TransactionCredit getmaxjournal(String d) {
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a  where   a.id=(select MAX(b.id) from TransactionCredit b where  b.article = :d)", TransactionCredit.class)
				.setParameter("d", d).getResultList();
		if(result.size()>0)
		return result.get(0);
		return null;
	}
	
	public List<TransactionCredit> getjournalbytarticle(String article,int id) {
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a where a.article = :statut and a.lignecommande.id = :d and a.lignecommande.statut = :status", TransactionCredit.class)
				.setParameter("statut", article).setParameter("d", id).setParameter("status",Statut.ACTIF).getResultList();
		if(result.size()>0)
		return result ;
		return null;
	}
	
	public List<TransactionCredit> getjournalbytarticle(int id) {
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a where   a.lignecommande.id = :d", TransactionCredit.class)
				 .setParameter("d", id).getResultList();
		if(result.size()>0)
		return result;
		return null;
	}
	@Transactional
	public List<TransactionCredit> getAll() {
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a   order by a.id Desc", TransactionCredit.class)
				 .getResultList();
		return result;
	}
	@Transactional
	public List<TransactionCredit> getJournalbydates(String dates){
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a where   a.dates = :d", TransactionCredit.class)
				 .setParameter("d", dates).getResultList();
		if(result.size()>0)return result; else return null;
	}
	 
	
	 

	@Transactional
	public void update(TransactionCredit TransactionCredit) {
		TransactionCredit p = em.find(TransactionCredit.class, TransactionCredit.getId());
     	 p=TransactionCredit;
		em.merge(p);
	}
	
	@Transactional
	public void update2(TransactionCredit TransactionCredit) {
		TransactionCredit p = em.find(TransactionCredit.class, TransactionCredit.getId());
     	 p.setAffecte(TransactionCredit.isAffecte());
		em.merge(p);
	}

	@Transactional
	public void delete(TransactionCredit TransactionCredit) {
		TransactionCredit p = em.find(TransactionCredit.class, TransactionCredit.getId());
		 
		em.merge(p);
	}

 
	public List<TransactionCredit> getJournalbetweendate(Date d1,Date d2){
		List<TransactionCredit> result = em
				.createQuery("SELECT  c FROM TransactionCredit c where  c.date BETWEEN :d1 and :d2", TransactionCredit.class)
				 
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		
		return result;
	}
	
	public List<TransactionCredit> getjournalNonAffecter(){
		List<TransactionCredit> result = em
				.createQuery("SELECT a FROM TransactionCredit a where   a.affecte = :aff", TransactionCredit.class)
				 .setParameter("aff",false ).getResultList();
		if(result.size()>0) {
		return result;
		}
		return null;
	}
}
