package   com.tn.shell.dao.gestat;
 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;
 
 

@Repository
public class BoncreditDaoImpl implements BoncreditDAO {
	/*@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Boncredit produit) {
		em.persist(produit);

	}
	@Transactional
	public List<Boncredit> getAllQtenegatif(){
		 List<Boncredit> VheculeListem=em.createQuery("SELECT c FROM  Boncredit c where c.quantitestock < c.qtemin and c.statut = :statut",Boncredit.class).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (VheculeListem.size() > 0){
	            return VheculeListem;}
	        else{
	            return null;}  
	}

	@Transactional
	public Boncredit Findbycode(Integer nom) {
		 List<Boncredit> VheculeListem=em.createQuery("SELECT c FROM  Boncredit c where c.id = :nom and c.statut = :statut",Boncredit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
	        
	        if (VheculeListem.size() > 0){
	            return VheculeListem.get(0);}
	        else{
	            return null;}   
	}
	
	public Boncredit Findbydes(String des) {
		List<Boncredit> VheculeListem=em.createQuery("SELECT c FROM  Boncredit c where c.nom = :nom and c.statut = :statut",Boncredit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", des).getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem.get(0);}
        else{
            return null;}   
	}
	@Transactional
	public List<Boncredit> getAllbyfamille(String des){
List<Boncredit> VheculeListem=em.createQuery("SELECT c FROM  Boncredit c where c.famille.nom = :nom and c.statut = :statut",Boncredit.class).setParameter("statut", Statut.ACTIF).setParameter("nom", des).getResultList();
        
        if (VheculeListem.size() > 0){
            return VheculeListem;}
        else{
            return null;}  
	}
	@Transactional
	public List<Boncredit> getAll() {
		List<Boncredit> result = em.createQuery("SELECT a FROM Boncredit a",
				Boncredit.class).getResultList();
		return result;
	}
	  
	@Transactional
	public List<Boncredit> getAll2() {
		List<Boncredit> result = em.createQuery("SELECT a FROM Produit a order by a.produitfini.designation Asc",
				Boncredit.class).getResultList();
		return result;
	}
	@Transactional
	public void update(Boncredit produit) {
		Boncredit p = em.find(Boncredit.class, produit.getId());
		 
	 em.merge(p); 
	}

	public Boncredit getProduitbydesignation(String designation) {
		List<Boncredit> fis = em
				.createQuery(
						"SELECT p FROM  Boncredit p   where p.produitfini.designation= :desig",
						Boncredit.class).setParameter("desig", designation).getResultList();

		if (fis.size()>0) {

			return fis.get(0);

		} else {
			return null;
		}

	}*/

}
