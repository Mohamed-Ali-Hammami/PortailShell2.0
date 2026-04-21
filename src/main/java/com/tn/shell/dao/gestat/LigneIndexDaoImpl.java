package com.tn.shell.dao.gestat;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.shop.Produit;

@Repository
public class LigneIndexDaoImpl implements LigneindexDAO {

    @PersistenceContext
    private EntityManager em;

    // ===================== SAVE =====================
    @Override
    @Transactional
    public void save(Ligneindex c) {
        em.persist(c);
    }

    // ===================== GET =====================
    @Override
    @Transactional
    public List<Ligneindex> getLigneindexbycreditclient(Credit credit) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.credit.id = :id AND c.statut = :statut",
                Ligneindex.class)
                .setParameter("id", credit.getId())
                .setParameter("statut", Statut.ACTIF)
                .getResultList();
    }
    @Override
    @Transactional
    public List<Ligneindex> getAll() {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllbyProduit(Produit p) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.produit.id = :id",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", p.getId())
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllparposte(Caisse c) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.caisse.id = :caisse",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("caisse", c.getId())
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllventepardate(String d) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.dates = :date",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("date", d)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllventeparposte(String d, Poste poste) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.poste = :poste AND c.dates = :date",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("poste", poste)
                .setParameter("date", d)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllventeparposteNegatif(String d, Poste poste) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.poste = :poste AND c.dates = :date",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("poste", poste)
                .setParameter("date", d)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllventepardate(String d, Articlecarburant article) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.articlecarburant.id = :id AND c.dates = :date",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", article.getId())
                .setParameter("date", d)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Ligneindex> getAllventeentredate(Date d1, Date d2, Articlecarburant a) {
        return em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.articlecarburant.id = :id AND c.date BETWEEN :date1 AND :date2",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", a.getId())
                .setParameter("date1", d1)
                .setParameter("date2", d2)
                .getResultList();
    }

    // ===================== SINGLE =====================

    @Override
    @Transactional
    public Ligneindex getAllventepardateandpompeandposte(String date, Pompe p, Poste poste) {
        List<Ligneindex> result = em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.dates = :date AND c.pompe.id = :p AND c.poste = :poste",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("date", date)
                .setParameter("p", p.getId())
                .setParameter("poste", poste)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public Ligneindex getAllventepardateandpompeandposte2(Pompe p, Caisse c) {
        List<Ligneindex> result = em.createQuery(
                "SELECT c FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.id = :p AND c.caisse.id = :c",
                Ligneindex.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("p", p.getId())
                .setParameter("c", c.getId())
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public double getmaxcode(Pompe p, Caisse c) {
        Number result = em.createQuery(
                "SELECT MAX(c.id) FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.id = :p AND c.caisse.id = :c",
                Number.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("p", p.getId())
                .setParameter("c", c.getId())
                .getSingleResult();

        return result != null ? result.doubleValue() : 0;
    }
                                    
@Override
    @Transactional
    public Ligneindex getmaxcode() {
        Number maxId = em.createQuery(
                "SELECT MAX(b.id) FROM Ligneindex b WHERE b.statut = :statut", Number.class)
                .setParameter("statut", Statut.ACTIF)
                .getSingleResult();
        
        if (maxId == null) return null;
        
        return em.find(Ligneindex.class, maxId.intValue());
    }

    // ===================== AGGREGATION =====================

    @Override
    @Transactional
    public double getAllventepardatearticle(Articlecarburant a, Date d1, Date d2) {
        return em.createQuery(
                "SELECT COALESCE(SUM(c.mantant),0) FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.articlecarburant.id = :id AND c.date BETWEEN :date1 AND :date2",
                Double.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", a.getId())
                .setParameter("date1", d1)
                .setParameter("date2", d2)
                .getSingleResult();
    }

    @Override
    @Transactional
    public double getquantitebyarticledates(Articlecarburant a, String d) {
        return em.createQuery(
                "SELECT COALESCE(SUM(c.quantite),0) FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.articlecarburant.id = :id AND c.dates = :date",
                Double.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", a.getId())
                .setParameter("date", d)
                .getSingleResult();
    }

    @Override
    @Transactional
    public double getAllventepardatearticlequantite(Articlecarburant a, Date d1, Date d2) {
        return em.createQuery(
                "SELECT COALESCE(SUM(c.quantite),0) FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.articlecarburant.id = :id AND c.date BETWEEN :date1 AND :date2",
                Double.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", a.getId())
                .setParameter("date1", d1)
                .setParameter("date2", d2)
                .getSingleResult();
    }

    @Override
    @Transactional
    public double getAllventepardatearticlequantitepardate(Articlecarburant a, String d1) {
        return em.createQuery(
                "SELECT COALESCE(SUM(c.mantant),0) FROM Ligneindex c WHERE c.statut = :statut AND c.pompe.articlecarburant.id = :id AND c.dates = :date",
                Double.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", a.getId())
                .setParameter("date", d1)
                .getSingleResult();
    }

    @Override
    @Transactional
    public double getquantitebyarticle(Articlecarburant a, Caisse c) {
        return em.createQuery(
                "SELECT COALESCE(SUM(c.quantite),0) FROM Ligneindex c WHERE c.pompe.articlecarburant.nom = :nom AND c.statut = :statut AND c.caisse.id = :id",
                Double.class)
                .setParameter("nom", a.getNom())
                .setParameter("statut", Statut.ACTIF)
                .setParameter("id", c.getId())
                .getSingleResult();
    }

    // ===================== UPDATE / DELETE =====================

    @Override
    @Transactional
    public void update(Ligneindex c) {
        Ligneindex lc = em.find(Ligneindex.class, c.getId());
        lc.setPoste(c.getPoste());
        lc.setQuantite(c.getQuantite());
        lc.setIndexfermuture(c.getIndexfermuture());
        lc.setIndexouverture(c.getIndexouverture());
        lc.setDate(c.getDate());
        em.merge(lc);
    }

    @Override
    @Transactional
    public void delete(Ligneindex c) {
        Ligneindex lc = em.find(Ligneindex.class, c.getId());
        lc.setStatut(Statut.DEACTIF);
        em.merge(lc);
    }
}

