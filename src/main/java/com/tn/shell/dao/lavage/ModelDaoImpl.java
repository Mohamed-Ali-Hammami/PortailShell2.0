package com.tn.shell.dao.lavage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.shop.*;

@Repository
public class ModelDaoImpl implements ModelDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Model> getAll() {
		List<Model> result = em.createQuery("SELECT a FROM Model a  where a.statut = :statut", Model.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Model c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<Model> result = em
				.createQuery("SELECT a FROM Model a  where a.statut = :statut and a.id =(select MAX(b.id) from Model b)", Model.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet Model n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Model getModelbyid(Integer id) {
		return em.find(Model.class, id);
	}
	@Transactional
	public void update(Model c) {
		Model a = em.find(Model.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}

	@Transactional
	public List<Model> getModelbyMarque(Marque f) {
		List<Model> result = em.createQuery(
				"SELECT b FROM Model  b  where b.statut = :statut  and b.marque.id = :numero order by b.id Desc",
				Model.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getId()).getResultList();
		return result;
	}
	@Transactional
	public List<Model> getModelbyArticle(Produit f){
		List<Model> result = em.createQuery(
				"SELECT b FROM Model  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				Model.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
}
