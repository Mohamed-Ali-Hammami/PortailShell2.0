package com.tn.shell.dao.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;
 

@Repository
public class VenteDaoImpl implements VenteDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Vente> getAll() {
		List<Vente> result = em.createQuery("SELECT a FROM Vente a  where a.statut = :statut", Vente.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Vente c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<Vente> result = em
				.createQuery("SELECT a FROM Vente a  where a.statut = :statut  and a.id =(select MAX(b.id) from Vente b)", Vente.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size()>0) {
			System.out.println("objet trouvé " +result.get(0).getId()+ "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet Vente n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Vente getVentebyid(Integer id) {
		return em.find(Vente.class, id);
	}
	@Transactional
	public void update(Vente c) {
		Vente a = em.find(Vente.class, c.getId());
		a.setStatut(Statut.DEACTIF);
		em.merge(a);

	}

}
