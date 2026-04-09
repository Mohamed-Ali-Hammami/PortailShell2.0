package   com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.shop.*;
@Repository
public class LigneTransfertDaoImpl implements LigneTransfertdao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignetransert c) {
		em.persist(c);
		
	}
	 
	 @Transactional	 
	 public List<Lignetransert> getAll(){
		 List<Lignetransert> result = em.createQuery("SELECT c FROM Lignetransert c where c.statut = :statut", Lignetransert.class).setParameter("statut", Statut.ACTIF).getResultList();
		 return result;
	 }
	 @Transactional
	 public List<Lignetransert> getAllbydate(String d){
		 List<Lignetransert> result = em.createQuery("SELECT c FROM Lignetransert c where c.statut = :statut and c.dates = :date  ", Lignetransert.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("date", d)
				 //.setParameter("qte", 0)
				 .getResultList();
		if(result.size()>0)
		 return result;
		else return null;
	 }
	 
	 public List<Lignetransert> getlisttransferbydateandproduit(String d,Produit p){
		 List<Lignetransert> result = em.createQuery("SELECT c FROM Lignetransert c where c.statut = :statut and c.dates = :date and c.produit.id = :id ", Lignetransert.class)
				 .setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("id", p.getId())
			 
				 .getResultList();
		if(result.size()>0)
		 return result;
		else return null;
	 }
	 
	 @Transactional
	 public List<Lignetransert> getAllbydatemoins(String d){
		 List<Lignetransert> result = em.createQuery("SELECT c FROM Lignetransert c where c.statut = :statut and c.dates = :date  ", Lignetransert.class)
				 .setParameter("statut", Statut.ACTIF).setParameter("date", d)
				// .setParameter("qte", 0)
				 .getResultList();
		if(result.size()>0)
		 return result;
		else return null;
	 }
	 @Transactional
	public void update(Lignetransert c) {
		 Lignetransert lc=em.find(Lignetransert.class, c.getId());
		 
		lc.setQuantite(c.getQuantite());
		lc.setProduit(c.getProduit());
		 lc.setDate(c.getDate());
		em.merge(lc);
		
	}
	 
	 @Transactional
		public void delete(Lignetransert c) {
		 Lignetransert lc=em.find(Lignetransert.class, c.getId());
			lc.setStatut(Statut.DEACTIF);			 
			em.merge(lc);
			
		}
	 

}
