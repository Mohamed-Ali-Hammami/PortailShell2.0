package com.tn.shell.dao.banque;
 
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.banque.Compte;
import com.tn.shell.model.banque.Enumcheque;
import com.tn.shell.model.banque.Transaction;
import com.tn.shell.model.gestat.Statut;
 

@Repository
public class TransactionDaoImpl implements TransactionDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Transaction Transaction) {
		em.persist(Transaction);

	}

	@Transactional
	public List<Transaction> getAll(Compte compte) {
		List<Transaction> result = em.createQuery("SELECT c FROM Transaction c where c.statut = :statut and c.compte.id = :compte", Transaction.class)
				.setParameter("statut", Statut.ACTIF).setParameter("compte",compte.getId()).getResultList();
		return result;
	}

	@Transactional
	public List<Transaction> Findbynom(String nom) {
		List<Transaction> TransactionListem = em
				.createQuery("SELECT c FROM  Transaction c where c.nom = :nom and c.statut = :statut ", Transaction.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();

		if (TransactionListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return TransactionListem;
		} else {
			System.out.println("\n\nl  objet Transaction n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public Transaction Findbycode(String reference) {
		List<Transaction> TransactionListem = em
				.createQuery("SELECT c FROM  Transaction c where c.reference = :nom and c.statut = :statut", Transaction.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", reference).getResultList();

		if (TransactionListem.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return TransactionListem.get(0);
		} else {
			System.out.println("\n\nl  objet Transaction n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public void update(Transaction d) {
		Transaction c = em.find(Transaction.class, d.getId());
		c = d;

		em.merge(c);
	}

	@Transactional
	public void delete(Transaction transaction) {
		Transaction c = em.find(Transaction.class, transaction.getId());

		c.setStatut(Statut.DEACTIF);
		em.merge(c);

	}

	public List<Transaction> findbyDate(Date date1, Date date2,Compte compte) {
	
		List<Transaction> result = em.createQuery("SELECT c FROM Transaction c where c.statut = :statut and  c.date between :date1 and :date2 and c.compte.id = :compte", Transaction.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date1", date1).setParameter("dte2", date2).setParameter("compte",compte.getId()).getResultList();
		return result;
	}

	public List<Transaction> findbyMonth(int monht,Compte compte) {
         System.out.println("\n\n\nmont "+monht);
		List<Transaction> result = em.createQuery("SELECT c FROM Transaction c where c.statut = :statut and month(c.date) =:m and c.compte.id = :compte", Transaction.class)
				.setParameter("statut", Statut.ACTIF).setParameter("m", monht).setParameter("compte",compte.getId()).getResultList();
		return result;
	}

	public List<Transaction> findByEnumarationCeque(Enumcheque cheque,Compte compte) {
		List<Transaction> result = em.createQuery("SELECT c FROM Transaction c where c.statut = :statut and c.etatcheque = :etatcheque and c.compte.id = :compte", Transaction.class)
				.setParameter("statut", Statut.ACTIF)	.setParameter("etatcheque",cheque).setParameter("compte",compte.getId()).getResultList();
		return result;
	}

}
