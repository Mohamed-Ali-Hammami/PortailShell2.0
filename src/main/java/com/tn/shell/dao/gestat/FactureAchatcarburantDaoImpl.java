package   com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Fournisseur;
 
@Repository
public class FactureAchatcarburantDaoImpl implements FactureAchatcarburantDAO {
	@PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Factureachatcarburant facture) {
		em.persist(facture);
		
	}

	 
	 @Transactional
	public List<Factureachatcarburant> getAll() {
		 List<Factureachatcarburant> result = em.createQuery("SELECT c FROM Factureachatcarburant c  where c.statut = :statut order by c.date Desc", Factureachatcarburant.class).setParameter("statut", Statut.ACTIF).getResultList();
		     
		 return result;
	}
	 
	  
	 @Transactional
	 public Factureachatcarburant getMaxfacture() {
List<Factureachatcarburant> result = em.createQuery("SELECT a FROM Factureachatcarburant a  where a.statut = :statut ORDER BY a.id DESC LIMIT 1", Factureachatcarburant.class).setParameter("statut", Statut.ACTIF)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	     	System.out.println("objet trouvé "+"\n\n\n");
	         return result.get(0);}
	     else{
	     	System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
	         return null;} 
		 }
	 
	 @Transactional
	 public List<Factureachatcarburant> getfacturebyStatus(Status s){
List<Factureachatcarburant> result = em.createQuery("SELECT a FROM Factureachatcarburant a  where a.statut = :statut  and a.status = :s", Factureachatcarburant.class)
.setParameter("statut", Statut.ACTIF).setParameter("s", s)
				 
				 
				 .getResultList();
		 if (result.size() > 0){
	     	System.out.println("objet trouvé "+"\n\n\n");
	         return result;}
	     else{
	     	System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
	         return null;} 
	 }
	 @Transactional
	 public List<Factureachatcarburant> getfacturebyfour(Fournisseur four){
		 List<Factureachatcarburant> result = em.createQuery("SELECT a FROM Factureachatcarburant f ,Achat a  where f.id = a.factureachat.id  and a.statut = :s1  and f.statut = :s2 and f.fournisseur.code = :s", Factureachatcarburant.class)
				 .setParameter("s1", Statut.ACTIF)
				 .setParameter("s2", Statut.ACTIF)
				 .setParameter("s", four)
				 				 
				 				 
				 				 .getResultList();
				 		 if (result.size() > 0){
				 	     	System.out.println("objet trouvé "+"\n\n\n");
				 	         return result;}
				 	     else{
				 	     	System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
				 	         return null;} 
	 }
	 @Transactional
	public void update(Factureachatcarburant facture) {
		 Factureachatcarburant c=em.find(Factureachatcarburant.class, facture.getId());		  
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
	public void delete(Factureachatcarburant facture) {
		 Factureachatcarburant c=em.find(Factureachatcarburant.class, facture.getId());		  
		 	 
		 c.setStatut(Statut.DEACTIF);
		em.merge(c);
		
	}
	 @Transactional
	public Factureachatcarburant getbycode(String code) {
		 List<Factureachatcarburant> FactureachatListem=em.createQuery("SELECT u FROM  Factureachatcarburant u where u.code = :code and  u.statut = :statut",Factureachatcarburant.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FactureachatListem.size() > 0){
	        	System.out.println("objet trouvé\n");
	            return FactureachatListem.get(0);}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}


	public List<Factureachatcarburant> getfacturebydate(Date d1, Date d2) {
		 List<Factureachatcarburant> FactureachatListem=em.createQuery("SELECT u FROM  Factureachat u where u.date between :d1 and :d2",Factureachatcarburant.class).setParameter("d1", d1).setParameter("d2", d2).getResultList();
	        
	        if (FactureachatListem.size() > 0){
	        	System.out.println("\n\n\n\nfacture bydate trouvé\n\n\n\n");
	            return FactureachatListem;}
	        else{
	        	System.out.println("l  objet n exsite pas");
	            return null;}
	}

}
