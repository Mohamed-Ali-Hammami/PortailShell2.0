package com.tn.shell.dao.lavage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.AffectationHuile;
import com.tn.shell.model.lavage.Model;
import com.tn.shell.model.shop.Statut;

 

@Repository
public class AffectationHuileDaoImpl implements AffectationHuileDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<AffectationHuile> getAll() {
		List<AffectationHuile> result = em.createQuery("SELECT a FROM AffectationHuile a  where a.statut = :statut", AffectationHuile.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(AffectationHuile c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<AffectationHuile> result = em
				.createQuery("SELECT a FROM AffectationHuile a  where a.statut = :statut and a.id =(select MAX(b.id) from AffectationHuile b)", AffectationHuile.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet AffectationHuile n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public AffectationHuile getAffectationHuilebyid(Integer id) {
		return em.find(AffectationHuile.class, id);
	}
	@Transactional
	public void update(AffectationHuile c) {
		AffectationHuile a = em.find(AffectationHuile.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}

 
	public List<AffectationHuile> getAffectationHuilebyModel(Model f) {
		List<AffectationHuile> result = em.createQuery(
				"SELECT b FROM AffectationHuile  b  where b.statut = :statut  and b.model.id = :numero order by b.id Desc",
				AffectationHuile.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getId()).getResultList();
		return result;
	}
}
