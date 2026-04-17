package   com.tn.shell.dao.gestat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.gestat.*;

@Repository
public class ArticleCarburantDaoImpl implements ArticleCarburantDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Articlecarburant Articlecarburant) {
        em.persist(Articlecarburant);
    }

    @Transactional
    public List<Articlecarburant> getAllQtenegatif() {
        List<Articlecarburant> result = em.createQuery("SELECT c FROM  Articlecarburant c where c.quantitestock < c.qtemin and c.statut = :statut", Articlecarburant.class)
                .setParameter("statut", Statut.ACTIF)
                .getResultList();
        if (result.isEmpty()) {
        }
        return result;
    }

    @Transactional
    public Articlecarburant Findbycode(Integer nom) {
        List<Articlecarburant> result = em.createQuery("SELECT c FROM  Articlecarburant c where c.id = :nom and c.statut = :statut", Articlecarburant.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("nom", nom)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public Articlecarburant Findbydes(String des) {
        List<Articlecarburant> result = em.createQuery("SELECT c FROM  Articlecarburant c where c.nom = :nom and c.statut = :statut", Articlecarburant.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("nom", des)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Transactional
    public List<Articlecarburant> getAllbyfamille(String des) {
        List<Articlecarburant> result = em.createQuery("SELECT c FROM  Articlecarburant c where c.famille.nom = :nom and c.statut = :statut", Articlecarburant.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("nom", des)
                .getResultList();
        if (result.isEmpty()) {
        }
        return result;
    }

    @Transactional
    public List<Articlecarburant> getAll() {
        return em.createQuery("SELECT a FROM Articlecarburant a  order by a.id Asc", Articlecarburant.class)
                .getResultList();
    }

    @Transactional
    public List<Articlecarburant> getAll2() {
        return em.createQuery("SELECT a FROM Articlecarburant a order by a.Articlecarburantfini.designation Asc", Articlecarburant.class)
                .getResultList();
    }

    @Transactional
    public void update(Articlecarburant a) {
        Articlecarburant p = em.find(Articlecarburant.class, a.getId());
        p.setNom(a.getNom());
        p.setMontant(a.getMontant());
        p.setVente(a.getVente());
        p.setAchat(a.getAchat());
        p.setQuantite(a.getQuantite());
        p.setTvaachat(a.getTvaachat());
        p.setTvavente(a.getTvavente());
        em.merge(p);
    }

    public Articlecarburant getArticlecarburantbydesignation(String designation) {
        List<Articlecarburant> fis = em
                .createQuery(
                        "SELECT p FROM  Articlecarburant p   where p.Articlecarburantfini.designation= :desig",
                        Articlecarburant.class).setParameter("desig", designation).getResultList();

        if (fis.isEmpty()) {
            return null;
        }
        return fis.get(0);
    }
}
