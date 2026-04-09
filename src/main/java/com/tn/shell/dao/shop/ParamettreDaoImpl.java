package   com.tn.shell.dao.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.shop.*;

@Repository
public class ParamettreDaoImpl implements ParamettreDAO{

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	 public List<Paramettre> getAll(){
		 List<Paramettre> result = em.createQuery("SELECT p FROM Paramettre p  ", Paramettre.class).getResultList();
		    return result;
	 }
	 @Transactional
	 public void update(Paramettre paramettre){
		 Paramettre p=em.find(Paramettre.class, paramettre.getId());
		 
		 p.setTimbre(paramettre.getTimbre());
		 em.merge(p);
	 }
	 @Transactional
	 public void save(Paramettre paramettre){
		 em.persist(paramettre);
	 }
}
