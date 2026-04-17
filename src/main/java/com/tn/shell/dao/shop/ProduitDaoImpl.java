package   com.tn.shell.dao.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;
 

@Repository
public class ProduitDaoImpl implements ProduitDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Produit produit) {
		em.persist(produit);

	}
	@Transactional
	public List<Produit> getAllQtenegatif(){
		// List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where (c.quantitedepot <= c.qtemin or c.quantitestock <= c.qtemin ) and c.statut = :statut    and c.famille.code != :f1 and c.famille.code != :f2 and c.famille.code != :f3  order by c.codeshop",Produit.class)
		 List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where   c.statut = :statut    and c.codeshop !=0 and c.famille.code != :f1 and c.famille.code != :f2 and c.famille.code != :f3    and c.famille.code != :f4  and c.qtemin >= c.quantitedepot + c.quantitestock order by c.codeshop ",Produit.class)	
		.setParameter("statut", Statut.ACTIF)
				 .setParameter("f1", 7)
				 .setParameter("f2", 9)
				 .setParameter("f3", 10)
				 .setParameter("f4", 11)
				 .getResultList();
	        
	        if (VheculeListem.size() > 0){
	            return VheculeListem;}
	        else{
	              return new ArrayList<Produit>();
	        }
	}
	 
	@Transactional
	public Produit Findbycode(Integer nom) {
		 List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.id = :nom and c.statut = :statut",Produit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (VheculeListem.size() > 0){
	            return VheculeListem.get(0);}
	        else{
	            return null;}   
	}
	
	@Transactional
	public Produit Findbycodeshop(Integer nom) {
		 List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.codeshop = :nom and c.statut = :statut",Produit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (VheculeListem.size() > 0){
	            return VheculeListem.get(0);}
	        else{
	            return null;}   
	}
	
	public Produit Findbydes(String des) {
		List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.nom = :nom and c.statut = :statut",Produit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", des).getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem.get(0);}
        else{
            return null;}   
	}
	
	public Produit Findbycodes(String des) {
		List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.code = :nom and c.statut = :statut",Produit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", des).getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem.get(0);}
        else{
            return null;}   
	}
	@Transactional
	public List<Produit> getAllbyfamille(String des){
List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.famille.nom = :nom and c.statut  = :statut and c.quantitedepot >0 order by c.id Asc ",Produit.class).setParameter("statut", Statut.ACTIF)
.setParameter("nom", des) 
.getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem;}
        else{
            return new ArrayList<Produit>();}  
	}
	
	@Transactional
	public List<Produit> getAllbyfamilles(String des){
List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.famille.nom = :nom and c.statut  = :statut and c.quantitestock >0 order by c.id Asc ",Produit.class).setParameter("statut", Statut.ACTIF)
.setParameter("nom", des) 
.getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem;}
        else{
            return new ArrayList<Produit>();}  
	}
	
	@Transactional
	public List<Produit> getAllbyfamilles0(String des){
List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.famille.nom = :nom and c.statut  = :statut and c.codeshop > 0  order by c.codeshop Asc ",Produit.class).setParameter("statut", Statut.ACTIF)
.setParameter("nom", des) 
.getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem;}
        else{
            return new ArrayList<Produit>();}  
	}
	
	
	
	@Transactional
	public List<Produit> getAllbyfamille2(String des){
List<Produit> VheculeListem=em.createQuery("SELECT c FROM  Produit c where c.famille.nom = :nom and c.statut  = :statut  order by c.codeshop Asc ",Produit.class).setParameter("statut", Statut.ACTIF)
.setParameter("nom", des) 
.getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem;}
        else{
            return new ArrayList<Produit>();}  
	}
	@Transactional
	public List<Produit> getAll() {
		Query q  = em.createQuery("SELECT a FROM Produit  a where a.statut = :statut and a.codeshop > :p order by a.codeshop "
				 ).setParameter("statut", Statut.ACTIF).setParameter("p",0);
		try {
			List<Produit>  result = q.getResultList();
			
				return result;}
				catch(Exception e) {return new ArrayList<Produit>();}
	 
	}
	
	@Transactional
	public List<Produit> getAlls() {
		List<Produit> result = em.createQuery("SELECT a FROM Produit  a where a.statut = :statut and a.codeshop > :p and a.famille.code != :f1 and a.famille.code != :f2 and a.famille.code != :f3 order by a.codeshop ",
				Produit.class).setParameter("statut", Statut.ACTIF).setParameter("p",0)
				.setParameter("f1",9)
				.setParameter("f2",10)
				.setParameter("f3",11)
				 .getResultList();
		return result;
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Produit> result = em.createQuery("SELECT c  FROM Produit c where c.statut = :statut ", Produit.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Produit c:result) l.add(c.getNom());
		    return l  ;
	 }
	@Transactional
	public List<Produit> getAll2() {
		List<Produit> result = em.createQuery("SELECT a FROM Produit a order by a.produitfini.designation Asc",
				Produit.class).getResultList();
		return result;
	}
	@Transactional
	public void update(Produit produit) {
		Produit p = em.find(Produit.class, produit.getId());
		 p=produit;
	 em.merge(p); 
	}

	public Produit getProduitbydesignation(String designation) {
		List<Produit> fis = em
				.createQuery(
						"SELECT p FROM  Produit p   where p.produitfini.designation= :desig",
						Produit.class).setParameter("desig", designation).getResultList();

		if (fis.size()>0) {

			return fis.get(0);

		} else {
			return null;
		}

	}
	
	public Produit getProduitbyCodeAbare(String designation) {
		List<Produit> fis = em
				.createQuery(
						"SELECT p FROM  Produit p   where p.code = :desig",
						Produit.class).setParameter("desig", "%"+designation+"%").getResultList();

		if (fis.size()>0) {

			return fis.get(0);

		} else {
			return null;
		}
	}

}
