package com.tn.shell.dao.lavage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.Imagelavage;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Produit;
import com.tn.shell.model.shop.Statut;

@Repository
public class ImageLavageDaoImpl implements ImagelavageDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Imagelavage> getAll() {
		List<Imagelavage> result = em.createQuery("SELECT a FROM Imagelavage a  where a.statut = :statut", Imagelavage.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void save(Imagelavage c) {
		em.persist(c);
	}
	 
	@Transactional
	public Integer getmaxcode() {
		List<Imagelavage> result = em
				.createQuery("SELECT a FROM Imagelavage a  where a.statut = :statut and a.id =(select MAX(b.id) from Imagelavage b)", Imagelavage.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0).getId();
		} else {
			System.out.println("\n\nl  objet Imagelavage n exsite pas\n\n");
			return 0;
		}
	}
	@Transactional
	public Imagelavage getImagelavagebyid(Integer id) {
		return em.find(Imagelavage.class, id);
	}
	@Transactional
	public void update(Imagelavage c) {
		Imagelavage a = em.find(Imagelavage.class, c.getId());
		 
		em.merge(a);

	}

	@Transactional
	public List<Imagelavage> getImagelavagebyMarque(Marque f) {
		List<Imagelavage> result = em.createQuery(
				"SELECT b FROM Imagelavage  b  where b.statut = :statut  and b.marque.id = :numero order by b.id Desc",
				Imagelavage.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getId()).getResultList();
		return result;
	}
	@Transactional
	public List<Imagelavage> getImagelavagebyArticle(Produit f){
		List<Imagelavage> result = em.createQuery(
				"SELECT b FROM Imagelavage  b  where b.statut = :statut  and b.fournisseur.code = :numero order by b.id Desc",
				Imagelavage.class).setParameter("statut", Statut.ACTIF).setParameter("numero", f.getCode()).getResultList();
		return result;
	}
}
