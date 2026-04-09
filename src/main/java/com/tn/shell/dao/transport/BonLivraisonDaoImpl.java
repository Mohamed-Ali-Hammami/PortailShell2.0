package com.tn.shell.dao.transport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import java.text.ParseException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.transport.*;

@Repository
public class BonLivraisonDaoImpl implements Bonlivraisondao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Bonlivraison c) {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		c.setDates(sf.format(c.getDate()));
		em.persist(c);

	}

	@Transactional
	public Bonlivraison getMaxbl() {
		List<Bonlivraison> result = em
				.createQuery("SELECT a FROM Bonlivraison a  where a.statut = :statut and a.numero=(select MAX(b.numero) from Bonlivraison b)",
						Bonlivraison.class)
				.setParameter("statut", Statut.ACTIF)

				.getResultList();
		if (result.size() > 0) {
			System.out.println("objet trouvé " + "\n\n\n");
			return result.get(0);
		} else {
			System.out.println("\n\nl  objet Pointageconge n exsite pas\n\n");
			return null;
		}
	}

	@Transactional
	public double getchiifreaffaireparclient(Integer code) {
		double chiffre = 0;

		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.client.code = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (BonlivraisonListem.size() > 0) {
			for (Bonlivraison b : BonlivraisonListem) {
				chiffre = chiffre + b.getMontant() + b.getTotaltva();
			}
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return chiffre;
	}
	
	@Transactional
	public List<Bonlivraison> getbonlivraisonbetween(Date d1,Date d2){
		List<Bonlivraison> result = em
				.createQuery("SELECT a FROM Bonlivraison a  where a.statut = :statut  and a.date BETWEEN :d1 and :d2",
						Bonlivraison.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("l  objet n exsite pas");
		}
		return result;
	}
	@Transactional
	public List<Chauffeur> getlistchauffeurbydate(Date d1,Date d2){
		List<Chauffeur> result = em
				.createQuery("SELECT a.chauffeur FROM Bonlivraison a  where a.statut = :statut  and a.date BETWEEN :d1 and :d2",
						Chauffeur.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("d1", d1)
				.setParameter("d2", d2)
				.getResultList();
		if (result.size() > 0) {
			System.out.println("l  objet n exsite pas");
		}
		return result;
	}
	@Transactional
	public double gettotaltransport(Vhecule v,Date d1,Date d2) {
		double chiffre = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<Bonlivraison> l=new ArrayList<Bonlivraison>();
		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.vhecule.id = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", v.getId()).setParameter("statut", Statut.ACTIF).getResultList();
          
		try {
			for(Bonlivraison f:BonlivraisonListem) {
			 
				Date d = dateFormat.parse(dateFormat.format(d1));
				Date d3 = dateFormat.parse(dateFormat.format(d2));
				 if (d.compareTo(dateFormat.parse(dateFormat.format(f.getDate())))<=0 &&
						 d3.compareTo(dateFormat.parse(dateFormat.format(f.getDate())))>=0 ) 
				{
			      l.add(f);
				 }}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if (BonlivraisonListem.size() > 0) {
			for (Bonlivraison b : BonlivraisonListem) {
				chiffre = chiffre + b.getTransport();
			}
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return chiffre;
	}

	@Transactional
	public Integer getnbBLparclient(Integer code) {

		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.client.code = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		return BonlivraisonListem.size();
	}

	@Transactional
	public double getquantitrparclient(Integer code) {
		double chiffre = 0;

		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.client.code = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (BonlivraisonListem.size() > 0) {
			// chiffre=BonlivraisonListem.get(0).;
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return 0;
	}

	@Transactional
	public void update(Bonlivraison b) {
		Bonlivraison bl = em.find(Bonlivraison.class, b.getNumero());
         bl =b;
		em.merge(bl);

	}

	@Transactional
	public List<Bonlivraison> getBLbyfacture(Facture f) {
		List<Bonlivraison> result = em
				.createQuery("SELECT b FROM Bonlivraison b  where b.statut = :statut  and b.facture.numero = :numero  ",
						Bonlivraison.class)
				.setParameter("statut", Statut.ACTIF).setParameter("numero", f.getNumero()).getResultList();
		return result;
	}

	@Transactional
	public List<Bonlivraison> getAll() {
		List<Bonlivraison> result = em
				.createQuery("SELECT b FROM Bonlivraison b  where b.statut = :statut  ", Bonlivraison.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void delete(Bonlivraison b) {
		Bonlivraison bl = em.find(Bonlivraison.class, b.getNumero());
		 
		em.remove(bl);

	}

	public Bonlivraison getBLbycodes(String code) {
		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.codes = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (BonlivraisonListem.size() > 0) {
			System.out.println("objet trouvé\n");
			return BonlivraisonListem.get(0);
		} else {
			System.out.println("l  objet n exsite pas");
			return null;
		}
	}

	public List<Bonlivraison> getBLbystatuts(Status s) {

		List<Bonlivraison> BonlivraisonListem = em.createQuery(
				"SELECT u FROM  Bonlivraison u where u.status = :status and u.statut = :statut order by u.client.nom Asc",
				Bonlivraison.class).setParameter("status", s).setParameter("statut", Statut.ACTIF).getResultList();

		if (BonlivraisonListem.size() > 0) {
			System.out.println("objet trouvé\n");
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return BonlivraisonListem;
	}
	public List<Bonlivraison>getAllventepardatearticle(Chauffeur a,String s){
		List<Bonlivraison> BonlivraisonListem = em.createQuery(
				"SELECT u FROM  Bonlivraison u where u.statut = :statut and u.dates = :status and u.chauffeur.id = :nom ",
				Bonlivraison.class).setParameter("statut", Statut.ACTIF).setParameter("status", s)
				.setParameter("nom", a.getId()).getResultList();

		if (BonlivraisonListem.size() > 0) {
			System.out.println("objet trouvé\n");
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return BonlivraisonListem;
	}
	public List<Bonlivraison> getBLbystatutsandclient(Status s, String nom) {

		List<Bonlivraison> BonlivraisonListem = em.createQuery(
				"SELECT u FROM  Bonlivraison u where u.status = :status and u.statut = :statut and u.client.nom = :nom ",
				Bonlivraison.class).setParameter("status", s).setParameter("statut", Statut.ACTIF)
				.setParameter("nom", nom).getResultList();

		if (BonlivraisonListem.size() > 0) {
			System.out.println("objet trouvé\n");
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return BonlivraisonListem;
	}

	public Bonlivraison getBLbycode(Integer code) {
		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.code = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (BonlivraisonListem.size() > 0) {
			System.out.println("objet trouvé\n");
			return BonlivraisonListem.get(0);
		} else {
			System.out.println("l  objet n exsite pas");
			return null;
		}
	}

	public Integer getmaxcode() {
		Query q = em.createQuery("SELECT MAX(b.code) FROM Bonlivraison b  where b.statut = :statut")
				.setParameter("statut", Statut.ACTIF);
		Integer result = (Integer) q.getSingleResult();
		return result;
	}
	public double sumdtransportv(Date d1,Date d2,int id) {
		Query q = em.createQuery ("SELECT SUM(u.transport) FROM Bonlivraison u where u.date BETWEEN :d1 and :d2 and u.statut = :statut and u.vhecule.id = :id "
				).setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF).setParameter("id", id);
		Double result = (Double) q.getSingleResult ();
		if(result !=null)
		return result;
		else return 0;
	}

	public List<Bonlivraison> getBLNonAffectee() {
		List<Bonlivraison> BonlivraisonListem = em.createQuery(
				"SELECT u FROM  Bonlivraison u where u.status = :status and u.statut = :statut and u.affectee = :affectee",
				Bonlivraison.class).setParameter("affectee", false).setParameter("statut", Statut.ACTIF)
				.getResultList();

		if (BonlivraisonListem.size() > 0) {
			System.out.println("objet trouvé\n");
		} else {
			System.out.println("l  objet n exsite pas");
		}
		return BonlivraisonListem;
	}
}


