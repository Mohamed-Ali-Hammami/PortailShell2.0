package com.tn.shell.dao.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.tn.shell.model.shop.*;

@Repository
public class AchatDaoImpl implements AchatDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Achat> getAll() {
		List<Achat> result = em.createQuery("SELECT a FROM Achat a  where a.statut = :statut", Achat.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Achat c) {
		em.persist(c);
	}
	@Transactional
	public List<Achat> getAchatbystatusfacture(Status status,Fournisseur f){
		List<Achat> result = em.createQuery("SELECT a FROM Achat a  where a.statut = :statut and a.factureachat.status = :status  and a.fournisseur.nom = :nom", Achat.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("status", status == null ? null : status.toDatabaseValue())
				.setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	@Transactional
	public Integer getmaxcode() {
		List<Achat> result = em
				.createQuery("SELECT a FROM Achat a  where a.statut = :statut and a.id =(select MAX(b.id) from Achat b)", Achat.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public Achat getAchatbyid(Integer id) {
		return em.find(Achat.class, id);
	}
	@Transactional
	public void update(Achat c) {
		Achat a = em.find(Achat.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}

	@Transactional
	public List<Achat> getArticlebyfacture(Factureachat f) {
		List<Achat> result = em.createQuery(
				"SELECT b FROM Achat  b  where b.statut = :statut  and b.factureachat.code = :numero order by b.id Desc",
				Achat.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
	@Transactional
	public List<Achat> getArticlebyfournisseur(Fournisseur f){
		List<Achat> result = em.createQuery(
				"SELECT b FROM Achat  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				Achat.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
}
