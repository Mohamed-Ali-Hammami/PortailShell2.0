package com.tn.shell.dao.lavage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Statut;

 

@Repository
public class MarqueDaoImpl implements MarqueDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Marque> getAll() {
		List<Marque> result = em.createQuery("SELECT a FROM Marque a  where a.statut = :statut", Marque.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Marque c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<Marque> result = em
				.createQuery("SELECT a FROM Marque a  where a.statut = :statut and a.id =(select MAX(b.id) from Marque b)", Marque.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet Marque n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Marque getMarquebyid(Integer id) {
		return em.find(Marque.class, id);
	}
	public Marque getMarquebyNom(String nom) {
		return em.find(Marque.class, nom);
	}
	@Transactional
	public void update(Marque c) {
		Marque a = em.find(Marque.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}

 
}
