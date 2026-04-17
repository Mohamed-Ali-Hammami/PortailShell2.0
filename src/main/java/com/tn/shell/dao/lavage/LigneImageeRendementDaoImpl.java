package com.tn.shell.dao.lavage;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.LigneImagerendement;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Statut;

@Repository
public class LigneImageeRendementDaoImpl implements LigneImageeRendementDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<LigneImagerendement> getAll() {
		List<LigneImagerendement> result = em.createQuery("SELECT a FROM LigneImagerendement a  where a.statut = :statut", LigneImagerendement.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(LigneImagerendement c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<LigneImagerendement> result = em
				.createQuery("SELECT a FROM LigneImagerendement a  where a.statut = :statut and a.id =(select MAX(b.id) from LigneImagerendement b)", LigneImagerendement.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public LigneImagerendement getLigneImagerendementbyid(Integer id) {
		return em.find(LigneImagerendement.class, id);
	}
	@Transactional
	public void update(LigneImagerendement c) {
		LigneImagerendement a = em.find(LigneImagerendement.class, c.getId());
		 
		em.merge(a);

	}

	@Transactional
	public List<LigneImagerendement> getLigneImagerendementbyMarque(Marque f) {
		if (f == null || f.getId() == null) {
			return new ArrayList<LigneImagerendement>();
		}
		List<LigneImagerendement> result = em.createQuery(
				"SELECT b FROM LigneImagerendement  b  where b.statut = :statut  and b.marque.id = :numero order by b.id Desc",
				LigneImagerendement.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getId()).getResultList();
		return result;
	}
	@Transactional
	public List<LigneImagerendement> getLigneImagerendementbyArticle(Produit f){
		if (f == null || f.getCode() == null) {
			return new ArrayList<LigneImagerendement>();
		}
		List<LigneImagerendement> result = em.createQuery(
				"SELECT b FROM LigneImagerendement  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				LigneImagerendement.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}

	public LigneImagerendement findByRendementIdAndPosition(Integer id, String position) {
		try {
		LigneImagerendement result = em.createQuery(
				"SELECT b FROM LigneImagerendement  b  where b.rendement.id = :id  and b.position= :position ",
				LigneImagerendement.class).setParameter("id", id).setParameter("position", position).getSingleResult();
		 
		if (result!=null) {
			return result;
		} else {
			return null;
		}
		}
		catch(Exception ee) {
			return null;
				}
	}
}
