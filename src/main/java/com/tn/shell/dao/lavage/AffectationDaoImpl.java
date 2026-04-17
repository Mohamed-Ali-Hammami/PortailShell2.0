package com.tn.shell.dao.lavage;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.AffectationFiltre;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.shop.Statut;

 

@Repository
public class AffectationDaoImpl implements AffectationDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<AffectationFiltre> getAll() {
		List<AffectationFiltre> result = em.createQuery("SELECT a FROM AffectationFiltre a  where a.statut = :statut", AffectationFiltre.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(AffectationFiltre c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<AffectationFiltre> result = em
				.createQuery("SELECT a FROM AffectationFiltre a  where a.statut = :statut and a.id =(select MAX(b.id) from AffectationFiltre b)", AffectationFiltre.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			return result.get(0).getId();
		} else {
			return 0;
		}
	}
	@Transactional
	public AffectationFiltre getAffectationFiltrebyid(Integer id) {
		return em.find(AffectationFiltre.class, id);
	}
	@Transactional
	public void update(AffectationFiltre c) {
		AffectationFiltre a = em.find(AffectationFiltre.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}


	public List<AffectationFiltre> getAffectationFiltrebyModel(Model f) {
		if (f == null || f.getId() == null) {
			return new ArrayList<AffectationFiltre>();
		}
		List<AffectationFiltre> result = em.createQuery(
				"SELECT b FROM AffectationFiltre  b  where b.statut = :statut  and b.model.id = :numero order by b.id Desc",
				AffectationFiltre.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getId()).getResultList();
		return result;
	}
}
