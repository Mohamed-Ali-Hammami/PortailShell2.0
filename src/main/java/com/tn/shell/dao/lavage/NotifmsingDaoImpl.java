package com.tn.shell.dao.lavage;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.NotifSmsing;
import com.tn.shell.model.shop.Statut;

 

@Repository
public class NotifmsingDaoImpl implements NotifSmsingDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<NotifSmsing> getAll() {
		List<NotifSmsing> result = em.createQuery("SELECT a FROM NotifSmsing a  where a.statut = :statut", NotifSmsing.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(NotifSmsing c) {
		em.persist(c);
	}
	 
	@Transactional
	public BigInteger getmaxcode() {
		List<NotifSmsing> result = em
				.createQuery("SELECT a FROM NotifSmsing a  where    a.idNotif_smsing =(select MAX(b.idNotif_smsing) from NotifSmsing b)", NotifSmsing.class)
				 

				.getResultList();
	 
			return result.get(0).getIdNotifmsing();
		 
	}
	@Transactional
	public NotifSmsing getNotifSmsingbyid(Integer id) {
		return em.find(NotifSmsing.class, id);
	}
	public NotifSmsing getNotifSmsingbyNom(String nom) {
		return em.find(NotifSmsing.class, nom);
	}
	@Transactional
	public void update(NotifSmsing c) {
		NotifSmsing a = em.find(NotifSmsing.class, c.getIdNotifmsing());
		 a=c;
		em.merge(a);

	}

 
}
