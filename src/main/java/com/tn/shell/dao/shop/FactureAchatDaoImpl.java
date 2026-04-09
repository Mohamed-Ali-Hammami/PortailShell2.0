package   com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.Factureachat;
import com.tn.shell.model.shop.Statut;
 
 
@Repository
public class FactureAchatDaoImpl implements FactureAchatDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Factureachat facture) {
		em.persist(facture);
		
	}

	 
	 @Transactional
	public List<Factureachat> getAll() {
		 List<Factureachat> result = em.createQuery("SELECT c FROM Factureachat c  where c.statut = :statut order by c.date Desc", Factureachat.class).setParameter("statut", Statut.ACTIF).getResultList();
		     
		 return result;
	}
	 
	 @Transactional
	 public Factureachat getMaxfacture() {
List<Factureachat> result = em.createQuery("SELECT a FROM Factureachat a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Factureachat.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	     	System.out.println("objet trouvé "+"\n\n\n");
	         return result.get(0);}
	     else{
	     	System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
	         return null;} 
		 }
	 
	 @Transactional
	public void update(Factureachat facture) {
		 Factureachat c=em.find(Factureachat.class, facture.getId());		  
		  
		 
		 c.setEcheance(facture.getEcheance());
		 c.setDate(facture.getDate());
		 c.setTotalht(facture.getTotalht());
		 c.setTotaltva(facture.getTotaltva());
		 c.setBanque(facture.getBanque());
		 c.setNumerocheck(facture.getNumerocheck());
		 c.setStatus(facture.getStatus());
		 c.setDatepayement(facture.getDatepayement());
		 c.setTypepayement(facture.getTypepayement());
		 em.merge(c);
		
	}
	 @Transactional
	public void delete(Factureachat facture) {
		 Factureachat c=em.find(Factureachat.class, facture.getId());		  
		 	 
		 c.setStatut(Statut.DEACTIF);
		em.merge(c);
		
	}
	 @Transactional
	public Factureachat getbycode(String code) {
		 List<Factureachat> FactureachatListem=em.createQuery("SELECT u FROM  Factureachat u where u.code = :code and  u.statut = :statut",Factureachat.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FactureachatListem.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return FactureachatListem.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}


	public List<Factureachat> getfacturebydate(Date d1, Date d2) {
		 List<Factureachat> FactureachatListem=em.createQuery("SELECT u FROM  Factureachat u where u.date between :d1 and :d2",Factureachat.class).setParameter("d1", d1).setParameter("d2", d2).getResultList();
	        
	        if (FactureachatListem.size() > 0){
	        	System.out.println("\n\n\n\nfacture bydate trouvé\n\n\n\n");
	            return FactureachatListem;}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}

}
