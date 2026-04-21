package com.tn.shell.dao.transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.transport.*;
@Repository
public class FactureDaoImpl implements FactureDAO {
	@PersistenceContext
	 private EntityManager em;
	 private static final String ACTIVE_STATUS_SQL = "(f.statut is null or trim(f.statut) = '' or lower(trim(f.statut)) = 'actif')";
	@Transactional
	public void update(Facture facture) {
		em.merge(facture);
	}
	 @Transactional
	public void save(Facture facture) {
		em.persist(facture);
		
	}
	 @Transactional
	public List<Facture> getAll() {
		try {
			List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut order by a.numero Desc"
					+ "", Facture.class).setParameter("statut", Statut.ACTIF).getResultList();
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (RuntimeException ex) {
		}
		List<Object[]> rows = em.createNativeQuery(
				"SELECT f.numero AS f_numero,f.code AS f_code,f.codes AS f_codes,f.date AS f_date,"
						+ "f.totalht AS f_totalht,f.totalttc AS f_totalttc,f.totaltva AS f_totaltva,"
						+ "f.timbres AS f_timbres,f.status AS f_status,f.statut AS f_statut,"
						+ "f.bonlivraisonid AS f_bonlivraisonid,b.code AS bl_code,b.codes AS bl_codes,"
						+ "b.date AS bl_date,b.clientid AS bl_clientid,c.nom AS c_nom "
						+ "FROM facture f "
						+ "LEFT JOIN bonlivraison b ON b.numero = f.bonlivraisonid "
						+ "LEFT JOIN client c ON c.code = b.clientid "
						+ "WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(f.numero AS UNSIGNED) DESC")
				.getResultList();
		return mapRows(rows);
	}
	 @Transactional
	 public Facture getMaxfacture() {
		try {
			List<Facture> result = em.createQuery(
					"SELECT a FROM Facture a  where a.statut = :statut and a.numero=(select MAX(b.numero) from Facture b)",
					Facture.class)
					.setParameter("statut", Statut.ACTIF)
					.getResultList();
			if (result.size() > 0) {
				return result.get(0);
			}
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT f.numero AS f_numero,f.code AS f_code,f.codes AS f_codes,f.date AS f_date,"
							+ "f.totalht AS f_totalht,f.totalttc AS f_totalttc,f.totaltva AS f_totaltva,"
							+ "f.timbres AS f_timbres,f.status AS f_status,f.statut AS f_statut,"
							+ "f.bonlivraisonid AS f_bonlivraisonid,b.code AS bl_code,b.codes AS bl_codes,"
							+ "b.date AS bl_date,b.clientid AS bl_clientid,c.nom AS c_nom "
							+ "FROM facture f "
							+ "LEFT JOIN bonlivraison b ON b.numero = f.bonlivraisonid "
							+ "LEFT JOIN client c ON c.code = b.clientid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " ORDER BY CAST(f.numero AS UNSIGNED) DESC LIMIT 1")
					.getResultList();
			List<Facture> mapped = mapRows(rows);
			if (!mapped.isEmpty()) {
				return mapped.get(0);
			}
		}
		return null;
		 }
	 @Transactional
	 public List<Facture> getAllPasager(){
		 return getAll();
	 }
	 @Transactional
	 public List<Facture> getAllTransport(){
		 return getAll();
	 }
	 @Transactional
	 public List<Facture> getfacturebetwenndate(Date d1,Date d2){
		 try {
			 List<Facture> result = em.createQuery("SELECT a FROM Facture a  where a.statut = :statut and a.date BETWEEN :d1 and :d2 ORDER BY a.numero DESC ", Facture.class)
					 .setParameter("statut", Statut.ACTIF)
					 .setParameter("d1", d1).setParameter("d2", d2).getResultList();
			 if (result != null && !result.isEmpty()) {
				 return result;
			 }
		 } catch (RuntimeException ex) {
		 }
		 List<Object[]> rows = em.createNativeQuery(
				 "SELECT f.numero AS f_numero,f.code AS f_code,f.codes AS f_codes,f.date AS f_date,"
						 + "f.totalht AS f_totalht,f.totalttc AS f_totalttc,f.totaltva AS f_totaltva,"
						 + "f.timbres AS f_timbres,f.status AS f_status,f.statut AS f_statut,"
						 + "f.bonlivraisonid AS f_bonlivraisonid,b.code AS bl_code,b.codes AS bl_codes,"
						 + "b.date AS bl_date,b.clientid AS bl_clientid,c.nom AS c_nom "
						 + "FROM facture f "
						 + "LEFT JOIN bonlivraison b ON b.numero = f.bonlivraisonid "
						 + "LEFT JOIN client c ON c.code = b.clientid "
						 + "WHERE " + ACTIVE_STATUS_SQL + " AND f.date BETWEEN :d1 AND :d2 "
						 + "ORDER BY CAST(f.numero AS UNSIGNED) DESC")
				 .setParameter("d1", d1)
				 .setParameter("d2", d2)
				 .getResultList();
		 return mapRows(rows);
	}
	@Transactional
	public void delete(Facture facture) {
		Facture c=em.find(Facture.class, facture.getNumero());
		 
		em.remove(c);
	}
	public List<Facture> getbydate(Date d1, Date d2) {
		try {
			return em.createQuery("SELECT u FROM  Facture u where u.date between :d1 and :d2 and u.statut = :statut", Facture.class)
					.setParameter("d1", d1).setParameter("d2", d2).setParameter("statut", Statut.ACTIF).getResultList();
		} catch (RuntimeException ex) {
			List<Object[]> rows = em.createNativeQuery(
					"SELECT f.numero AS f_numero,f.code AS f_code,f.codes AS f_codes,f.date AS f_date,"
							+ "f.totalht AS f_totalht,f.totalttc AS f_totalttc,f.totaltva AS f_totaltva,"
							+ "f.timbres AS f_timbres,f.status AS f_status,f.statut AS f_statut,"
							+ "f.bonlivraisonid AS f_bonlivraisonid,b.code AS bl_code,b.codes AS bl_codes,"
							+ "b.date AS bl_date,b.clientid AS bl_clientid,c.nom AS c_nom "
							+ "FROM facture f "
							+ "LEFT JOIN bonlivraison b ON b.numero = f.bonlivraisonid "
							+ "LEFT JOIN client c ON c.code = b.clientid "
							+ "WHERE " + ACTIVE_STATUS_SQL + " AND f.date BETWEEN :d1 AND :d2 "
							+ "ORDER BY CAST(f.numero AS UNSIGNED) DESC")
					.setParameter("d1", d1)
					.setParameter("d2", d2)
					.getResultList();
			return mapRows(rows);
		}
	}
	public Facture getfacturebycode(String code) {
		 List<Facture> FactureachatListem=em.createQuery("SELECT u FROM  Facture u where u.codes = :code and u.statut = :statut",Facture.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
	        
	        if (FactureachatListem.size() > 0){
	            return FactureachatListem.get(0);}
	        else{
	            return null;}
	}
	public Facture getBLbycodes(String code) {
List<Facture> BonlivraisonListem=em.createQuery("SELECT u FROM  Facture u where u.codes = :code and u.statut = :statut",Facture.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
        
        if (BonlivraisonListem.size() > 0){
            return BonlivraisonListem.get(0);}
        else{
            return null;} 
	}
	public Integer getmaxcode() {
		Query q =  em.createQuery("SELECT MAX(b.code) FROM Facture b  where b.statut = :statut").setParameter("statut", Statut.ACTIF);
	    Integer result=(Integer)q.getSingleResult();
		return result;
	}
	public Facture getBLbycode(Integer code) {
List<Facture> BonlivraisonListem=em.createQuery("SELECT u FROM  Facture u where u.code = :code and u.statut = :statut",Facture.class).setParameter("code", code).setParameter("statut", Statut.ACTIF).getResultList();
        
        if (BonlivraisonListem.size() > 0){
            return BonlivraisonListem.get(0);}
        else{
            return null;} 
	}
	 

	 
	 
	private List<Facture> mapRows(List<Object[]> rows) {
		List<Facture> factures = new ArrayList<Facture>();
		for (Object[] row : rows) {
			Facture facture = new Facture();
			facture.setNumero(TransportTsvMapper.asInteger(row[0]));
			facture.setCode(TransportTsvMapper.asInteger(row[1]));
			facture.setCodes(TransportTsvMapper.asString(row[2]));
			facture.setDate(TransportTsvMapper.asDate(row[3]));
			facture.setTotalht(TransportTsvMapper.asDouble(row[4]));
			facture.setTotalttc(TransportTsvMapper.asDouble(row[5]));
			facture.setTotaltva(TransportTsvMapper.asDouble(row[6]));
			facture.setTimbres(TransportTsvMapper.asDouble(row[7]));
			facture.setStatus(TransportTsvMapper.asStatus(row[8]));
			facture.setStatut(TransportTsvMapper.asStatut(row[9]));

			Bonlivraison bl = new Bonlivraison();
			bl.setNumero(TransportTsvMapper.asInteger(row[10]));
			bl.setCode(TransportTsvMapper.asInteger(row[11]));
			bl.setCodes(TransportTsvMapper.asString(row[12]));
			bl.setDate(TransportTsvMapper.asDate(row[13]));

			Client client = new Client();
			client.setCode(TransportTsvMapper.asInteger(row[14]));
			client.setNom(TransportTsvMapper.asString(row[15]));
			bl.setClient(client);
			facture.setBl(bl);
			factures.add(facture);
		}
		return factures;
	}
}
