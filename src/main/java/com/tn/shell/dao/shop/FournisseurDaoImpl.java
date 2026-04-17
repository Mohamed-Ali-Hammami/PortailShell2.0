package   com.tn.shell.dao.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;

 
 

@Repository
public class FournisseurDaoImpl implements FournisseurDAO {
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Fournisseur fournisseur) {
		em.persist(fournisseur);
		
	}
	 @Transactional
	 public List<String> getAllnom(){
		 List<String> l=new ArrayList<String>();
		 List<Fournisseur> result = em.createQuery("SELECT c  FROM Fournisseur c where c.statut = :statut ", Fournisseur.class)
					.setParameter("statut", Statut.ACTIF) 
					.getResultList();
		if(result!=null) for(Fournisseur c:result) l.add(c.getNom());
		    return l  ;
	 }
@Transactional
	public Fournisseur getbyname(String name) {
		 List<Fournisseur> FournisseurListem=em.createQuery("SELECT u FROM  Fournisseur u where u.nom = :fournisseurname and u.statut=:statut",Fournisseur.class).setParameter("fournisseurname", name).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FournisseurListem.size() > 0){
	            return FournisseurListem.get(0);}
	        else{
	            return null;}   
	}
@Transactional
public Fournisseur getbyid(Integer name) {
	 List<Fournisseur> FournisseurListem=em.createQuery("SELECT u FROM  Fournisseur u where u.code = :fournisseurname and u.statut=:statut",Fournisseur.class).setParameter("fournisseurname", name).setParameter("statut", Statut.ACTIF).getResultList();
     
     if (FournisseurListem.size() > 0){
         return FournisseurListem.get(0);}
     else{
         return null;}   
}
@Transactional
public Fournisseur getbymf(String name) {
	 List<Fournisseur> FournisseurListem=em.createQuery("SELECT u FROM  Fournisseur u where u.matriculefiscal = :fournisseurname and u.statut=:statut",Fournisseur.class).setParameter("fournisseurname", name).setParameter("statut", Statut.ACTIF).getResultList();
        
        if (FournisseurListem.size() > 0){
            return FournisseurListem.get(0);}
        else{
            return null;}   
}
@Transactional
	public List<Fournisseur> getAll() {
		List<Fournisseur> result = em.createQuery("SELECT a FROM Fournisseur a  where a.statut = :statut", Fournisseur.class).setParameter("statut", Statut.ACTIF).getResultList();
	    return result;
	}
@Transactional
public void update(Fournisseur fournisseur) {
	Fournisseur c=em.find(Fournisseur.class, fournisseur.getCode());
	 c.setNom(fournisseur.getNom());
	 c.setMatriculfisacl(fournisseur.getMatriculfisacl());
	 c.setAddress(fournisseur.getAddress());
	 
	em.merge(c);
}
@Transactional
public void delete(Fournisseur fournisseur) {
	Fournisseur c=em.find(Fournisseur.class, fournisseur.getCode());
	c.setStatut(fournisseur.getStatut());
	em.merge(c);	
}
 

}
