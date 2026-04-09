package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;

@Repository
public class AchatcarburantDaoImpl implements AchatcarburantDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Achatcarburant> getAll() {
		List<Achatcarburant> result = em.createQuery("SELECT a FROM Achatcarburant a  where a.statut = :statut order by a.date Asc", Achatcarburant.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Achatcarburant c) {
		em.persist(c);
	}
	@Transactional
	public List<Achatcarburant> getAchatbystatusfacture(Status status,Fournisseur f){
		List<Achatcarburant> result = em.createQuery("SELECT a FROM Achatcarburant a  where a.statut = :statut and a.factureachat.status = :status  and a.fournisseur.nom = :nom", Achatcarburant.class)
				.setParameter("statut", Statut.ACTIF).setParameter("status", status).setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	@Transactional
	public List<Achatcarburant> getAchatbyDate(String dates){
		List<Achatcarburant> result =new ArrayList<Achatcarburant>();
			 result = em.createQuery("SELECT a FROM Achatcarburant a  where a.statut = :statut and a.dates = :dates", Achatcarburant.class)
					.setParameter("statut", Statut.ACTIF).setParameter("dates", dates).getResultList();
			if(result.size()>0)
			return result;
			else return result;
	}
	@Transactional
	public List<Achatcarburant> getAchatbyfacture(Fournisseur f){
		List<Achatcarburant> result = em.createQuery("SELECT a FROM Achatcarburant a  where a.statut = :statut   and a.fournisseur.nom = :nom", Achatcarburant.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", f.getNom()).getResultList();
		return result;
	}
	@Transactional
	public Integer getmaxcode() {
		List<Achatcarburant> result = em
				.createQuery("SELECT a FROM Achatcarburant a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Achatcarburant.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet achat n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Achatcarburant getAchatbyid(Integer id) {
		return em.find(Achatcarburant.class, id);
	}
	@Transactional
	public void update(Achatcarburant c) {
		Achatcarburant a = em.find(Achatcarburant.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}

	@Transactional
	public List<Achatcarburant> getArticlebyfacture(Factureachatcarburant f) {
		List<Achatcarburant> result = em.createQuery(
				"SELECT b FROM Achatcarburant  b  where b.statut = :statut  and b.factureachat.code = :numero order by b.id Desc",
				Achatcarburant.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
	@Transactional
	public List<Achatcarburant> getArticlebyfournisseur(Fournisseur f){
		List<Achatcarburant> result = em.createQuery(
				"SELECT b FROM Achatcarburant  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				Achatcarburant.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}

	public double getTotalAchatbyDate(Date date) {
		Query q = em.createQuery(
				"SELECT SUM(c.montant) FROM Achatcarburant  c where c.statut = :statut   and c.date = :date1"
				).setParameter("statut", Statut.ACTIF)
						.setParameter("date1", date);
				 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e) {
			return 0;
		}
	}
}
