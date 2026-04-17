package com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

 import com.tn.shell.model.shop.*;
 

@Repository
public class LigneVenteDaoImpl implements LigneVenteDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Lignevente c) {
		em.persist(c);

	}
	@Transactional
	public Lignevente getlignebyid(Integer id) {
	return em.find(Lignevente.class, id);
	}
	 
	
	@Transactional
	 public List<Lignevente> getAllventeparDateandp(String d,Produit p){
		 
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.produit.id = :id and c.dates= :dates",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId())
				.setParameter("dates", d)
				.getResultList();
if(result.size()>0)
		return result; 
else return null;
	 }
	
	
	public double getAllventeparDateandproduit(String d,Produit p) {
		Query q = em.createQuery ("SELECT SUM(c.quantite) FROM Lignevente c where c.statut = :statut and c.produit.id = :id and c.dates = :dates")
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId())
				.setParameter("dates", d);
		try {
			Double result = (Double) q.getSingleResult ();
			
				return result;}
				catch(Exception e) {return 0;}
	}
	

	public double getAllventeparDateandproduit2(Date d1,Date d2,Produit p) {
		Query q = em.createQuery ("SELECT SUM(c.quantite) FROM Lignevente c where c.statut = :statut and c.produit.id = :id and c.date BETWEEN :dates and :d2")
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId())
				.setParameter("dates", d1).setParameter("d2", d2);
		try {
			Double result = (Double) q.getSingleResult ();
			
				return result;}
				catch(Exception e) {return 0;}
	}
	 
	@Transactional
	public List<Lignevente> getbetwendates(Date d1,Date d2,Produit p){
List<Lignevente> result = em.createQuery("SELECT a FROM Lignevente a  where a.statut = :statut and   a.produit.id = :p and a.date BETWEEN :d1 and :d2 ", Lignevente.class)
				
				.setParameter("statut", Statut.ACTIF).setParameter("p",p.getId()).setParameter("d1", d1).setParameter("d2", d2).getResultList();
		if (result.size() > 0) {
		return 
				
				result;}
		else {
			
			return null;}
	}
	 public double getmontantbetwendate(Date d1,Date d2){
		 Query q = em.createQuery(
					"SELECT SUM(c.totalttc) FROM  Lignevente c where c.statut = :statut   and c.date BETWEEN :date1 and :date2  "
					).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d1).setParameter("date2",d2);
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 
	 public double getTotalTCtbytDate(String d2){
		 Query q = em.createQuery(
					"SELECT SUM(c.totalttc) FROM  Lignevente c where c.statut = :statut   and c.dates = :date1"
					).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d2) ;
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 
	 
	 public double getQuantiteParFamillebetwendate(Date d1,Date d2,Famillearticle f ){
		 Query q = em.createQuery(
					"SELECT SUM(c.quantite) FROM  Lignevente c where c.statut = :statut   and c.date BETWEEN :date1 and :date2 AND c.produit.id IN ( SELECT  p.id FROM Produit p WHERE p.famille.code = :f) "  
					   
					).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d1).setParameter("date2",d2) .setParameter("f",f.getCode());
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 
	 public double getTotalttcParFamillebetwendate(Date d1,Date d2,Famillearticle f ){
		 Query q = em.createQuery(
					"SELECT SUM(c.totalttc) FROM  Lignevente c where c.statut = :statut   and c.date BETWEEN :date1 and :date2 AND c.produit.id IN ( SELECT  p.id FROM Produit p WHERE p.famille.code = :f) "  
					   
					).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d1).setParameter("date2",d2) .setParameter("f",f.getCode());
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 
	 public double getProfilBrutParFamillebetwendate(Date d1,Date d2,Famillearticle f ){
		 Query q = em.createQuery(
				 
				 "SELECT SUM(((v.prix+(v.prix*v.tva/100)) - ( p.achat + ( p.achat * p.tva /100 ) ) ) * v.quantite)    FROM Produit p, Lignevente v WHERE v.produit.id = p.id and v.statut = :statut   and v.date BETWEEN :date1 and :date2 AND v.produit.id IN ( SELECT  p.id FROM Produit p WHERE p.famille.code = :f) "
					).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d1).setParameter("date2",d2) .setParameter("f",f.getCode());
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 
	 
	 public double getProfilBrutParFamillebetwendate2(Date d1,Date d2,int f ){
		 Query q = em.createQuery(
				 
				 "SELECT SUM(((v.prix+(v.prix*v.tva/100)) - ( p.achat + ( p.achat * p.tva /100 ) ) ) * v.quantite)    FROM Produit p, Lignevente v WHERE v.produit.id = p.id and v.statut = :statut   and v.date BETWEEN :date1 and :date2 AND v.produit.id IN ( SELECT  p.id FROM Produit p WHERE p.famille.code = :f) "
					).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d1).setParameter("date2",d2) .setParameter("f",f);
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 public double getmargebetwendate(Date d1,Date d2){
		 Query q = em.createQuery( 
					"SELECT SUM((p.vente - ( p.achat + ( p.achat * p.tva /100 ) ) ) * v.quantite)    FROM Produit p, Lignevente v WHERE v.produit.id = p.id and v.statut = :statut   and v.date BETWEEN :date1 and :date2  "
					
				 
				 ).setParameter("statut", Statut.ACTIF) 
							.setParameter("date1", d1).setParameter("date2",d2);
					 
			try {
				Double result = (Double) q.getSingleResult();

				return result;
			} catch (Exception e) {
				return 0;
			}
	 }
	 
	 
	 public List<Lignevente> getbetwendate(Date d1,Date d2){
		 List<Lignevente> result = em.createQuery("SELECT a FROM Lignevente a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2 ", Lignevente.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).getResultList();
			if (result.size() > 0) {
			return 
					
					result;}
			else {
				
				return null;}
	 }
 

	 

	 
	 public Lignevente getmaxlignevente() {
		 List<Lignevente> result = em
					.createQuery("SELECT a FROM Lignevente a  where a.statut = :statut and a.id  =(select MAX(b.id) from Lignevente b where b.generation = :gen)", Lignevente.class)
					.setParameter("statut", Statut.ACTIF)
					.setParameter("gen", TypeGeneration.Cloture)
					.getResultList();
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
	 }
	@Transactional
	public List<Lignevente> getAllbyticket(Ticket ticket) {
		 
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.ticket.id = :id",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", ticket.getId()).getResultList();

		return result;
	}
	@Transactional
	public List<Lignevente> getAllbyProduit(Produit p){
		 
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.produit.id = :id",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", p.getId()).getResultList();

		return result;
	}
	
	@Transactional
	 public List<Lignevente> getAllventeparposteandDate(String d ){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :date and c.generation = :gen order by c.produit.codeshop",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("gen", TypeGeneration.Cloture).getResultList();
		
		return result;
	 }
	@Transactional
	public List<Lignevente> getAllventeparposte(String d) {
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut   and c.generation = :gen",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("gen", TypeGeneration.NonSauver).getResultList();
		return result;
	}
	@Transactional
	public List<Lignevente> getAllventeparposteandDate(String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut   and c.generation = :gen and c.dates = :d",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("gen", TypeGeneration.Sauver)
				.setParameter("d", d)
				.getResultList();
		
		return result;
	}
	@Transactional
	 public List<Lignevente> getAllventeparDate(String d){
		 List<Lignevente> result = em
					.createQuery("SELECT c FROM Lignevente c where c.statut = :statut   and c.generation = :gen and c.dates = :d",
							Lignevente.class)
					.setParameter("statut", Statut.ACTIF).setParameter("gen", TypeGeneration.Cloture)
					.setParameter("d", d)
					.getResultList();
			
			return result;
	 }
	  
	@Transactional
	public List<Lignevente> getAllventeparposteandDate3(String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :date and c.generation = :gen order by c.produit.codeshop",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("gen", TypeGeneration.Sauver).getResultList();
		
		return result;
	}
	
	@Transactional
	public List<Lignevente> getAllventeparposteandDate33(String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :date and c.poste = :p and c.generation = :gen order by c.produit.codeshop",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d)
				 .setParameter("p", poste)
				.setParameter("gen", TypeGeneration.Cloture).getResultList();
		
		return result;
	}
	@Transactional
	public List<Lignevente> getAllventeparparfamille(String f ,String d ){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :date and c.generation = :gen and c.produit.nom = :f ",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("gen", TypeGeneration.Cloture)
				.setParameter("f", f)
				.getResultList();
		
		return result;
	}
	
	@Transactional
	public List<Lignevente> getAllventeparparfamille2(String f ,String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :dates and c.generation = :gen  and c.produit.nom = :f and c.poste = :poste",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("dates", d)
			//	.setParameter("gen1", TypeGeneration.Cloture)
				.setParameter("gen", TypeGeneration.Sauver)
				.setParameter("f", f)
				.setParameter("poste", poste)
				.getResultList();
		if(result.size()>0)System.out.println("" +result.size());
		else System.out.println("erreur de chargement");
		return result;
	}
	
	
	@Transactional
	public List<Lignevente> getAllventeparparfamille3(String f ,String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :dates   and c.produit.id = :f and c.poste = :poste",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("dates", d)
				 
				.setParameter("f", f)
				.setParameter("poste", poste)
				.getResultList();
		if(result.size()>0)System.out.println("" +result.size());
		else System.out.println("erreur de chargement");
		return result;
	}
	
	
	@Transactional
	public List<Lignevente> getAllventeparposteandDate2(String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut  and c.poste = :poste and c.dates = :date and c.generation = :gen order by c.produit.famille.code ",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste)
				.setParameter("date", d).setParameter("gen", TypeGeneration.Sauver).getResultList();
		
		return result;
	}
	
	@Transactional
	public List<Lignevente> getAllventeparposteandDate33(String f ,String d ,Poste poste){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.dates = :dates and c.generation = :gen  and c.produit.nom = :f and c.poste = :poste",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF)
				.setParameter("dates", d)
			//	.setParameter("gen1", TypeGeneration.Cloture)
				.setParameter("gen", TypeGeneration.Cloture)
				.setParameter("f", f)
				.setParameter("poste", poste)
				.getResultList();
		if(result.size()>0)System.out.println("" +result.size());
		else System.out.println("erreur de chargement");
		return result;
	}
	@Transactional
	public List<Lignevente> getAllventeparposteNegatif(String d, Poste poste) {
		List<Lignevente> result = em.createQuery(
				"SELECT c FROM Lignevente c where c.statut = :statut and c.poste = :poste and c.quantite < :qte and c.dates = :date and c.generation = :gen",
				Lignevente.class).setParameter("statut", Statut.ACTIF).setParameter("poste", poste).setParameter("date", d).setParameter("gen", TypeGeneration.Sauver)
				.setParameter("qte", 0).getResultList();
		return result;
	}

	@Transactional
	public List<Lignevente> getAll() {
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut", Lignevente.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void update(Lignevente c) {
		Lignevente lc = em.find(Lignevente.class, c.getId());
		lc.setPoste(c.getPoste());
		lc.setGeneration(c.getGeneration());
		lc.setQuantite(c.getQuantite());
		lc.setProduit(c.getProduit());
		lc.setDate(c.getDate());
		lc.setDates(c.getDates());
		em.merge(lc);

	}

	@Transactional
	public void delete(Lignevente c) {
		Lignevente lc = em.find(Lignevente.class, c.getId());
		lc.setStatut(Statut.DEACTIF);
		em.merge(lc);

	}
	public List<Lignevente> getAllbyNumticket(Integer ticket){
		List<Lignevente> result = em
				.createQuery("SELECT c FROM Lignevente c where c.statut = :statut and c.ticket.id = :id",
						Lignevente.class)
				.setParameter("statut", Statut.ACTIF).setParameter("id", ticket).getResultList();

		return result;
	}
	

}
