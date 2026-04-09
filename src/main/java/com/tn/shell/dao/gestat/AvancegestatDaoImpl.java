package com.tn.shell.dao.gestat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.gestat.*;
import com.tn.shell.model.paie.Employee;

@Repository
public class AvancegestatDaoImpl implements AvancegestatDAO {

    @PersistenceContext
    private EntityManager em;

    public double getAvance(String fonction, Date d1, Date d2) {
        System.out.println("\n\n\n avances" + d1 + " " + d2);
        Query q = em.createQuery("SELECT SUM(a.montant_avance ) FROM Avancegestat a  where a.statut = :statut and a.date between :d1 and :d2  and a.employee.fonction = :f and a.employee.status = :status ")
                .setParameter("statut", Statut.ACTIF)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .setParameter("f", fonction)
                .setParameter("status", com.tn.shell.model.paie.Status.ParVoiture);
        try {
            double result = (Double) q.getSingleResult();
            System.out.println("\n\n\n avances result" + result);
            return result;
        } catch (Exception e) {
            System.out.println("\n\n\n avances exception" + e.getMessage());
            return 0;
        }
    }

    @Transactional
    public List<Avancegestat> getAll() {
        List<Avancegestat> result = em.createQuery("SELECT a FROM Avancegestat a  where a.statut = :statut", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .getResultList();
        if (result.isEmpty()) {
            System.out.println(" \n\n\n\n l  objet n exsite pas   \n\n\n\n");
        }
        return result;
    }

    @Transactional
    public List<Avancegestat> getAvancebyCaisse(Caisse c) {
        return em.createQuery("SELECT a FROM Avancegestat a where a.statut = :statut   and a.caisse.id = :caiise and a.montant_avance != 0 order by a.employee.matricule", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("caiise", c.getId())
                .getResultList();
    }

    public List<Avancegestat> getAvancebDate(String date) {
        return em.createQuery("SELECT a FROM Avancegestat a where a.statut = :statut  and a.montant_avance != 0  and a.dates= :caiise order by a.employee.matricule", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("caiise", date)
                .getResultList();
    }

    public List<Avancegestat> getAvancebDateandemployee(String date, Employee e) {
        return em.createQuery("SELECT a FROM Avancegestat a where a.statut = :statut   and a.dates= :caiise and a.employee.matricule = :mat ", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("caiise", date)
                .setParameter("mat", e.getMatricule())
                .getResultList();
    }

    @Transactional
    public List<Avancegestat> getAvancesByEmployeebetweendate(Employee e, Date date1, Date date2) {
        return em.createQuery("SELECT a FROM Avancegestat a  where a.statut = :statut and a.employee.matricule = :matricule and a.date between  :d1 and :d2", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("matricule", e.getMatricule())
                .setParameter("d1", date1)
                .setParameter("d2", date2)
                .getResultList();
    }

    @Transactional
    public List<Avancegestat> getAvancesBybetweendate(Date date1, Date date2) {
        return em.createQuery("SELECT a FROM Avancegestat a  where a.statut = :statut  and  a.date between  :d1 and :d2", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("d1", date1)
                .setParameter("d2", date2)
                .getResultList();
    }

    @Transactional
    public List<Avancegestat> getAvancesByEmployee(Employee e, Integer annee, Integer mois) {
        return em.createQuery("SELECT a FROM Avancegestat a  where a.statut = :statut and a.employee.matricule = :matricule and a.annee = :annee and a.mois = :mois", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("matricule", e.getMatricule())
                .setParameter("annee", annee)
                .setParameter("mois", mois)
                .getResultList();
    }

    @Transactional
    public void save(Avancegestat c) {
        em.persist(c);
    }

    @Transactional
    public void update(Avancegestat c) {
        Avancegestat a = em.find(Avancegestat.class, c.getId());
        a.setAnnee(c.getAnnee());
        a.setMontant_avance(c.getMontant_avance());
        a.setMois(c.getMois());
        a.setEmployee(c.getEmployee());
        a.setStatut(c.getStatut());
        em.merge(a);
    }

    @Transactional
    public void detele(Avancegestat c) {
        Avancegestat a = em.find(Avancegestat.class, c.getId());
        a.setStatut(c.getStatut());
        em.merge(a);
    }

    @Transactional
    public Avancegestat getavancebyid(Integer id) {
        List<Avancegestat> result = em.createQuery("SELECT a FROM Avancegestat a where a.statut = :statut   and a.id = :caiise", Avancegestat.class)
                .setParameter("statut", Statut.ACTIF)
                .setParameter("caiise", id)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<Avancegestat> getAvancebDate(Date date1, Date date2, Employee e) {
        List<Avancegestat> result = em.createQuery("SELECT u FROM  Avancegestat u where u.date between :d1 and :d2 and u.employee.matricule = :mat and u.montant_avance !=0", Avancegestat.class)
                .setParameter("d1", date1)
                .setParameter("d2", date2)
                .setParameter("mat", e.getMatricule())
                .getResultList();
        if (result.isEmpty()) {
            System.out.println("l  objet n exsite pas");
        }
        return result;
    }
}
