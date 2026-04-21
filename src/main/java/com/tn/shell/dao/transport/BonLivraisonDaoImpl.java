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
	private static final String ACTIVE_STATUS_SQL = "(b.statut is null or trim(b.statut) = '' or lower(trim(b.statut)) = 'actif')";
	@Transactional
	public void save(Bonlivraison c) {
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		c.setDates(sf.format(c.getDate()));
		em.persist(c);

	}

	@Transactional
	public Bonlivraison getMaxbl() {
		List<Bonlivraison> result;
		try {
			result = em
					.createQuery("SELECT a FROM Bonlivraison a  where a.statut = :statut and a.numero=(select MAX(b.numero) from Bonlivraison b)",
							Bonlivraison.class)
					.setParameter("statut", Statut.ACTIF)
					.getResultList();
		} catch (RuntimeException ex) {
			result = em.createNativeQuery(
					"SELECT * FROM bonlivraison b WHERE " + ACTIVE_STATUS_SQL + " ORDER BY b.numero DESC LIMIT 1",
					Bonlivraison.class)
					.getResultList();
		}
		if (result.size() > 0) {
			return result.get(0);
		} else {
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
			}
		
		if (l.size() > 0) {
			for (Bonlivraison b : l) {
				chiffre = chiffre + b.getTransport();
			}
		} else {
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
		if (f == null || f.getBl() == null || f.getBl().getNumero() == null) {
			return new ArrayList<Bonlivraison>();
		}
		List<Bonlivraison> result = em
				.createQuery("SELECT b FROM Bonlivraison b where b.statut = :statut and b.numero = :numero",
						Bonlivraison.class)
				.setParameter("statut", Statut.ACTIF).setParameter("numero", f.getBl().getNumero()).getResultList();
		return result;
	}

	@Transactional
	public List<Bonlivraison> getAll() {
		try {
			return em
					.createQuery("SELECT b FROM Bonlivraison b  where b.statut = :statut  ", Bonlivraison.class)
					.setParameter("statut", Statut.ACTIF).getResultList();
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT b.numero,b.code,b.codes,b.date,b.heure,b.montant,b.statut,b.totaltva,b.transport,"
							+ "b.chauffeurid,b.clientid,b.vheculeid,b.dates,b.status,c.nom,v.matricule,h.nompenom "
							+ "FROM bonlivraison b "
							+ "LEFT JOIN client c ON c.code = b.clientid "
							+ "LEFT JOIN vhecule v ON v.id = b.vheculeid "
							+ "LEFT JOIN chauffeur h ON h.id = b.chauffeurid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(b.numero AS UNSIGNED) DESC")
					.getResultList();
			return mapRows(rows);
		}
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
			return BonlivraisonListem.get(0);
		} else {
			return null;
		}
	}

	public List<Bonlivraison> getBLbystatuts(Status s) {
		List<Bonlivraison> BonlivraisonListem;
		try {
			BonlivraisonListem = em.createQuery(
					"SELECT u FROM  Bonlivraison u where u.status = :status and u.statut = :statut order by u.client.nom Asc",
					Bonlivraison.class).setParameter("status", s).setParameter("statut", Statut.ACTIF).getResultList();
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT b.numero,b.code,b.codes,b.date,b.heure,b.montant,b.statut,b.totaltva,b.transport,"
							+ "b.chauffeurid,b.clientid,b.vheculeid,b.dates,b.status,c.nom,v.matricule,h.nompenom "
							+ "FROM bonlivraison b "
							+ "LEFT JOIN client c ON c.code = b.clientid "
							+ "LEFT JOIN vhecule v ON v.id = b.vheculeid "
							+ "LEFT JOIN chauffeur h ON h.id = b.chauffeurid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " AND " + statusFilterSql(s)
							+ " ORDER BY CAST(b.numero AS UNSIGNED) DESC")
					.getResultList();
			BonlivraisonListem = mapRows(rows);
		}

		if (BonlivraisonListem.size() > 0) {
		} else {
		}
		return BonlivraisonListem;
	}
	public List<Bonlivraison>getAllventepardatearticle(Chauffeur a,String s){
		List<Bonlivraison> BonlivraisonListem = em.createQuery(
				"SELECT u FROM  Bonlivraison u where u.statut = :statut and u.dates = :status and u.chauffeur.id = :nom ",
				Bonlivraison.class).setParameter("statut", Statut.ACTIF).setParameter("status", s)
				.setParameter("nom", a.getId()).getResultList();

		if (BonlivraisonListem.size() > 0) {
		} else {
		}
		return BonlivraisonListem;
	}
	public List<Bonlivraison> getBLbystatutsandclient(Status s, String nom) {

		List<Bonlivraison> BonlivraisonListem = em.createQuery(
				"SELECT u FROM  Bonlivraison u where u.status = :status and u.statut = :statut and u.client.nom = :nom ",
				Bonlivraison.class).setParameter("status", s).setParameter("statut", Statut.ACTIF)
				.setParameter("nom", nom).getResultList();

		if (BonlivraisonListem.size() > 0) {
		} else {
		}
		return BonlivraisonListem;
	}

	public Bonlivraison getBLbycode(Integer code) {
		List<Bonlivraison> BonlivraisonListem = em
				.createQuery("SELECT u FROM  Bonlivraison u where u.code = :code and u.statut = :statut",
						Bonlivraison.class)
				.setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();

		if (BonlivraisonListem.size() > 0) {
			return BonlivraisonListem.get(0);
		} else {
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
		List<Bonlivraison> BonlivraisonListem;
		try {
			BonlivraisonListem = em.createQuery(
					"SELECT u FROM  Bonlivraison u where u.status = :status and u.statut = :statut and u.affectee = :affectee",
					Bonlivraison.class).setParameter("status", Status.NonFacturee).setParameter("affectee", false).setParameter("statut", Statut.ACTIF)
					.getResultList();
		} catch (RuntimeException ex) {
			BonlivraisonListem = em.createNativeQuery(
					"SELECT * FROM bonlivraison b WHERE " + ACTIVE_STATUS_SQL
							+ " AND " + statusFilterSql(Status.NonFacturee)
							+ " AND (b.affectee = 0 OR b.affectee = '0' OR lower(trim(COALESCE(b.affectee,''))) = 'false') ORDER BY b.numero DESC",
					Bonlivraison.class)
					.getResultList();
		}

		if (BonlivraisonListem.size() > 0) {
		} else {
		}
		return BonlivraisonListem;
	}

	private List<Bonlivraison> mapRows(List<Object[]> rows) {
		List<Bonlivraison> result = new ArrayList<Bonlivraison>();
		for (Object[] row : rows) {
			Bonlivraison bl = new Bonlivraison();
			bl.setNumero(TransportTsvMapper.asInteger(row[0]));
			bl.setCode(TransportTsvMapper.asInteger(row[1]));
			bl.setCodes(TransportTsvMapper.asString(row[2]));
			bl.setDate(TransportTsvMapper.asDate(row[3]));
			bl.setHeure(TransportTsvMapper.asString(row[4]));
			bl.setMontant(TransportTsvMapper.asDouble(row[5]));
			bl.setStatut(TransportTsvMapper.asStatut(row[6]));
			bl.setTotaltva(TransportTsvMapper.asDouble(row[7]));
			bl.setTransport(TransportTsvMapper.asDouble(row[8]));
			bl.setStatus(TransportTsvMapper.asStatus(row[13]));

			Client client = new Client();
			client.setCode(TransportTsvMapper.asInteger(row[10]));
			client.setNom(TransportTsvMapper.asString(row[14]));
			bl.setClient(client);

			Vhecule vhecule = new Vhecule();
			vhecule.setId(TransportTsvMapper.asInteger(row[11]));
			vhecule.setMatricule(TransportTsvMapper.asString(row[15]));
			bl.setVhecule(vhecule.getId() == null ? null : vhecule);

			Chauffeur chauffeur = new Chauffeur();
			chauffeur.setId(TransportTsvMapper.asInteger(row[9]));
			chauffeur.setNompenom(TransportTsvMapper.asString(row[16]));
			bl.setChauffeur(chauffeur.getId() == null ? null : chauffeur);
			result.add(bl);
		}
		return result;
	}

	private String statusFilterSql(Status s) {
		if (s == null) {
			return "1=1";
		}
		if (Status.NonFacturee.equals(s)) {
			return "(b.status is null or trim(b.status) = '' or lower(trim(b.status)) in ('nonfacturee','nonfacture'))";
		}
		if (Status.Facturee.equals(s)) {
			return "lower(trim(b.status)) in ('facturee','facture')";
		}
		if (Status.Payee.equals(s)) {
			return "lower(trim(b.status)) in ('payee','paye')";
		}
		if (Status.NonPayee.equals(s)) {
			return "lower(trim(b.status)) in ('nonpayee','nonpaye')";
		}
		return "1=1";
	}
}


