package   com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;
@Repository
public class LigneAlimentationcarDaoImpl implements LigneAlimentaioncardao {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Lignealimentationcar c) {
		em.persist(c);
		
	}
	 @Transactional
	 public List<Lignealimentationcar> getLignebyachat(Achatcarburant c){
		 List<Lignealimentationcar> result = em.createQuery("SELECT c FROM Lignealimentationcar c where c.statut = :statut and c.achat.id = :achat ", Lignealimentationcar.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("achat", c.getId())				  
				 .getResultList();
		 return result; 
	 }
	 @Transactional	 
	 public List<Lignealimentationcar> getAll(){
		 List<Lignealimentationcar> result = em.createQuery(
				 "SELECT c FROM Lignealimentationcar c where c.statut = :statut order by c.date desc, c.id desc",
				 Lignealimentationcar.class)
				 .setParameter("statut", Statut.ACTIF)
				 .getResultList();
		 return result;
	 }
	/* @Transactional	 
	 public double getlastprisAchat(Date date1,Date date2,Articlecarburant a,double actualprice) {
		double prixachat = em.createQuery("SELECT c FROM Lignealimentationcar c where c.statut = :statut and c.articlecarburant.id = :produit and c.date BETWEEN :d1 and :d2 and c.")
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("produit", a.getId()).
				 
				  setParameter("d1", date1).
				  setParameter("d2", date2).
				 getSingleResult();
		 return 0;
	 }*/
	 @Transactional	 
	 public List<Lignealimentationcar> getLignebyproduit(Lignealimentationcar l){
		 List<Lignealimentationcar> result = em.createQuery("SELECT c FROM Lignealimentationcar c where c.statut = :statut and c.produit.id = :produir", Lignealimentationcar.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("produir", l.getArticlecarburant().getId()).getResultList();
		 return result;
	 }
	 @Transactional
	 public List<Lignealimentationcar>  getAllventepardatearticle(String s, Articlecarburant a){
		 List<Lignealimentationcar> result = em.createQuery("SELECT c FROM Lignealimentationcar c where c.statut = :statut and c.articlecarburant.id = :produit and c.dates = :s", Lignealimentationcar.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("produit", a.getId())
				 .setParameter("s", s)
				 .getResultList();
		 return result; 
	 }
	 @Transactional
	public void update(Lignealimentationcar c) {
		 Lignealimentationcar lc=em.find(Lignealimentationcar.class, c.getId());
		 
		lc.setQuantite(c.getQuantite());
		lc.setArticlecarburant(c.getArticlecarburant());
		 lc.setDate(c.getDate());
		em.merge(lc);
		
	}
	 
	 @Transactional
		public void delete(Lignealimentationcar c) {
		 Lignealimentationcar lc=em.find(Lignealimentationcar.class, c.getId());
			lc.setStatut(Statut.DEACTIF);			 
			em.merge(lc);
			
		}
	public double getprixbydate(Date d1, Date d2, Articlecarburant a) {
		List<Lignealimentationcar> result =new ArrayList<Lignealimentationcar>();
		 result =   em.createQuery("SELECT  c FROM Lignealimentationcar c where c.statut = :statut and c.articlecarburant.id = :article and date between :d1 and :d2",Lignealimentationcar.class)
				 .setParameter("statut", Statut.ACTIF)
				 .setParameter("article", a.getId())	
				 .setParameter("d1", d1)	
				 .setParameter("d2", d2)	
				 .getResultList();
		 if( result.size()>0)
		 return result.get(0).getPrix(); 
		 else return 0;
				 
	}
	 

}
