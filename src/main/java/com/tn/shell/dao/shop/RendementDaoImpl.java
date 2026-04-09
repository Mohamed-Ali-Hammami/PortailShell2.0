package com.tn.shell.dao.shop;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.*;
import com.tn.shell.model.transport.Chauffeur;
 

@Repository
public class RendementDaoImpl implements rendementDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Rendement c) {
		em.persist(c);

	}
	 
	public double getnbvbetwendates(String d1, String d2,TypeLavage typelavage) {
		System.out.println("\n\n\n\n d1 " + d1);
		System.out.println("\n\n\n\n d2 " + d2);
		Query q = em.createNativeQuery(
				"SELECT SUM(a.nbvoiture+a.nbsemi)  from Rendement a  where a.statut ='ACTIF' and a.typelavage =  '"+typelavage+  "' and a.date BETWEEN '"
						+ d1 + "' and '" + d2 + " 23:00:00' "); 
		try {
			double result = (Double) q.getSingleResult();
			System.out.println("\n\n\n\n result " + result);
			return result ;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}
	public double getRecettebetwendates(String d1, String d2,TypeLavage typelavage) {
	 
		Query q = em.createNativeQuery(
				"SELECT SUM(a.montantv+a.montants)  from Rendement a  where a.statut ='ACTIF'  and a.typelavage =  '"+typelavage+ "'    and a.date BETWEEN '"
						+ d1 + "' and '" + d2 + " 23:00:00'" ); 
		try {
			double result = (Double) q.getSingleResult();
			System.out.println("\n\n\n\n result " + result);
			return result ;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}
	
	public double getMontantvbetwendate(String d1, String d2, Employee e) {
		System.out.println("\n\n\n\n d1 " + d1);
		System.out.println("\n\n\n\n d2 " + d2);
		Query q = em.createNativeQuery(
				"SELECT SUM(CAST(a.parouvrier AS decimal(3,2)))  from Rendement a  where a.statut ='ACTIF'   and a.dates BETWEEN '"
						+ d1 + "' and '" + d2 + "'  and a.employeeid =" + e.getMatricule()); 
		try {
			BigDecimal result = (BigDecimal) q.getSingleResult();
			System.out.println("\n\n\n\n result " + result);
			return result.doubleValue();
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}

	public double getnbvbetwendate(String d1, String d2, Employee e) { 
		Query q = em.createNativeQuery(
				"SELECT SUM(a.nbvoiture+a.nbsemi) from Rendement a  where a.statut ='ACTIF'   and a.dates BETWEEN '"
						+ d1 + "' and '" + d2 + "'  and a.employeeid =" + e.getMatricule()); 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}
	
	@Transactional
	public List<Rendement> getAllbymatricule(String d) {
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.matricule = :date",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).getResultList();

		return result;
	}

	

		public double getnbvBydate(String d1, Employee e) { 	Query q = em.createNativeQuery(
				"SELECT SUM(a.nbvoiture+a.nbsemi) from Rendement a  where a.statut ='ACTIF'   and a.dates = '"
						+ d1 +  "'  and a.employeeid =" + e.getMatricule()); 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}
		
		public double getmontantvBydate(String d1, Employee e) { 	Query q = em.createNativeQuery(
				"SELECT SUM(a.montantv+montants) from Rendement a  where a.statut ='ACTIF'   and a.dates = '"
						+ d1 +  "'  and a.employeeid =" + e.getMatricule()); 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}

		
		public double getnbvBydate2(String d1,Poste poste,TypeLavage typelavage) { 	Query q = em.createNativeQuery(
				"SELECT SUM(a.nbvoiture+a.nbsemi) from Rendement a  where a.statut ='ACTIF'   and a.dates = '"
						+ d1 +  "' and a.poste = '" +poste+"'and a.typelavage = '"+typelavage+"'"); 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}
		
		public double getmontantvBydate2(String d1,Poste poste,TypeLavage typelavage) { 	Query q = em.createNativeQuery(
				"SELECT SUM(a.montantv+montants) from Rendement a  where a.statut ='ACTIF'   and a.dates = '"
						+ d1+  "' and a.poste = '" +poste+"' and a.typelavage = '"+typelavage+"'"); 
		try {
			Double result = (Double) q.getSingleResult();

			return result;
		} catch (Exception e2) {
			System.out.println("\n\n\n\n exception " + e2.getMessage());
			return 0;
		}
	}

	public List<Rendement> getbetwendatesAndtypelavageAndPoste(Date d1,Date d2,TypeLavage typelavage ,Poste poste){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.typelavage = :typelavage and a.poste=:poste and a.nbvoiture >= 0", Rendement.class)
				 .setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("typelavage", typelavage).setParameter("poste", poste).getResultList();
		if (result.size() > 0) {
			System.out.println("objet caisse trouvé " + "\n\n\n");
		return 
				
				result;}
		else {
			
			System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
			return null;}
	}
	
	public List<Rendement> getbetwendatesAndtypelavage(Date d1,Date d2,TypeLavage typelavage){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.typelavage = :typelavage and a.nbvoiture >= 0", Rendement.class)
					 .setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("typelavage", typelavage).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	}
	
	 public List<Rendement> getbetwendate(Date d1,Date d2,Employee e){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2  and a.employee.matricule = :employee and a.nbvoiture >= 0", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("employee", e.getMatricule()).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	 }
	 public List<Rendement> getbetwendates(Date d1,Date d2,TypeLavage typelavage,Statuss statuss){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.typelavage = :typelavage and a.nbvoiture >= 0 and a.statuss = :statuss", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("typelavage", typelavage).setParameter("statuss", statuss).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	 }
	 
	 public List<Rendement> getbetwendatesAndPoste(Date d1,Date d2,Poste poste,TypeLavage typelavage,Statuss statuss){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.poste = :poste  and a.typelavage = :typelavage and a.nbvoiture >= 0 and a.statuss = :statuss", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("poste", poste).setParameter("typelavage", typelavage).setParameter("statuss", statuss).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;} 
	 }
	 
	 public List<Rendement> getRendementPoste(TypeLavage typelavage,Statuss statuss){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut  and a.typelavage = :typelavage and a.nbvoiture >= 0 and a.statuss = :statuss", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF).setParameter("typelavage", typelavage).setParameter("statuss", statuss).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;} 
	 }
	 public List<Rendement> getbetwendateandemployee(Date d1,Date d2,Employee e){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.employee.matricule = :mat and a.nbvoiture >= 0", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("mat", e.getMatricule()).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	 }
	@Transactional
	public double getSommeht(Date d, Poste poste) {
		double res = 0;
		List result = em
				.createQuery("SELECT SUM(montantht) FROM Rendement c where c.statut = :statut and c.poste = :poste and c.nbvoiture >= 0",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).getResultList();
		for (Object p : result)
			res = res + Double.parseDouble(p + "");
		return res;
	}
	 

	@Transactional
	public double getSommetva(Date d, Poste poste) {
		double res = 0;
		List result = em
				.createQuery("SELECT SUM(totaltva) FROM Rendement c where c.statut = :statut and c.poste = :poste",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste).getResultList();
		for (Object p : result)
			res = res + Double.parseDouble(p + "");
		return res;
	}

	 
	 public Rendement getmaxRendement() {
		 List<Rendement> result = em
					.createQuery("SELECT a FROM Rendement a  where a.statut = :statut and a.generation = :generation and a.nbvoiture >= 0 ORDER BY a.id DESC LIMIT 1 ", Rendement.class)
					.setParameter("statut", Statut.ACTIF)
					.setParameter("generation", TypeGeneration.Cloture)
					.getResultList();
			if (result.size() > 0) {
				System.out.println("objet trouvé " + "\n\n\n");
				return result.get(0);
			} else {
				System.out.println("\n\nl  objet Rendement n exsite pas\n\n");
				return null;
			}
	 }
	 
	 
	
	@Transactional
	 public List<Rendement> getAllventeparposteandDate(String d ){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.dates = :date and c.generation = :gen order by c.produit.codeshop and c.nbvoiture >0",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("gen", TypeGeneration.Cloture).getResultList();
		
		return result;
	 }
	
	@Transactional
	 public List<Rendement> getAllventeparDateandmployee(String d,Employee e){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.dates = :date and c.employee.matricule = :employee and c.nbvoiture >0 ",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("employee", e.getMatricule()).getResultList();
		
		return result;
	 }
	@Transactional
	 public List<Rendement> getAllventeparDateandemployee(String d,Employee e){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.dates = :date and c.employee.matricule = :employee and c.nbvoiture >0 ",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("employee", e.getMatricule()).getResultList();
		
		return result;
	 }
	@Transactional
	public List<Rendement> getAllventeparposte(String d) {
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut   and c.generation = :gen",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("gen", TypeGeneration.NonSauver).getResultList();
		return result;
	}
	@Transactional
	public List<Rendement> getAllventeparposteandDate(String d ,Poste poste){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut   and c.generation = :gen and c.dates = :d and c.nbvoiture >= 0",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("gen", TypeGeneration.Sauver)
				.setParameter("d", d)
				.getResultList();
		
		return result;
	}
	@Transactional
	 public List<Rendement> getAllventeparDate(String d){
		 List<Rendement> result = em
					.createQuery("SELECT c FROM Rendement c where c.statut = :statut   and c.dates = :d ",
							Rendement.class)
					.setParameter("statut", Statut.ACTIF) 
					.setParameter("d", d)
					.getResultList();
			
			return result;
	 }
	  
	@Transactional
	public List<Rendement> getAllventeparposteandDate3(String d ,Poste poste){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.dates = :date and c.generation = :gen order by c.produit.codeshop and c.nbvoiture >= 0",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d).setParameter("gen", TypeGeneration.Sauver).getResultList();
		
		return result;
	}
	
	@Transactional
	public List<Rendement> getAllventeparposteandDate33(String d ,Poste poste){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.dates = :date and c.poste = :p and c.generation = :gen order by c.produit.codeshop and c.nbvoiture >= 0",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("date", d)
				 .setParameter("p", poste)
				.setParameter("gen", TypeGeneration.Cloture).getResultList();
		
		return result;
	}
	  
	@Transactional
	public List<Rendement> getAllventeparposteandDate2(String d ,Poste poste){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut  and c.poste = :poste and c.dates = :date and c.generation = :gen order by c.produit.famille.code and c.nbvoiture >= 0 ",
						Rendement.class)
				.setParameter("statut", Statut.ACTIF).setParameter("poste", poste)
				.setParameter("date", d).setParameter("gen", TypeGeneration.Sauver).getResultList();
		
		return result;
	}
	
	@Transactional
	public List<Rendement> getAllventeparposteandDate33(String f ,String d ,Poste poste){
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut and c.dates = :dates and c.generation = :gen  and c.produit.nom = :f and c.poste = :poste",
						Rendement.class)
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
	public List<Rendement> getAll() {
		List<Rendement> result = em
				.createQuery("SELECT c FROM Rendement c where c.statut = :statut", Rendement.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}

	@Transactional
	public void update(Rendement c) {
		Rendement lc = em.find(Rendement.class, c.getId());
		lc=c;
		em.merge(lc);

	}
	
	public Rendement findByid(Integer id) {
		Rendement lc = em.find(Rendement.class, id);
		return lc;
	}
	@Transactional
	public List<Rendement> getAllbyStatuss(Statuss statuss,TypeLavage typelavage){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.statuss = :statuss  and a.typelavage = :typelavage and a.nbvoiture >= 0", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("statuss", statuss) .setParameter("typelavage", typelavage).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	}

	@Transactional
	public void delete(Rendement c) {
		Rendement lc = em.find(Rendement.class, c.getId());
		lc.setStatut(Statut.DEACTIF);
		em.merge(lc);

	}
	
	public List<Rendement> getAllventeparDateAndEmployeeAndposte(String d, Employee e,Poste poste){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.dates = :d1   and a.employee.matricule = :employee and a.poste = :poste and a.nbvoiture >= 0 and a.statuss = :statuss", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d).setParameter("employee", e.getMatricule()).setParameter("poste", poste).setParameter("statuss", Statuss.Cloture).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	}
	
	public List<Rendement> getAllventeparDateAndServiceAndposte(String d, Produit e,Poste poste){
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.dates = :d1     and a.lignevente.produit.id = :produit and a.poste = :poste and (a.nbvoiture >= 0) and a.statuss = :statuss", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d).setParameter("produit", e.getId()).setParameter("poste", poste).setParameter("statuss", Statuss.Cloture).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 					
					result;}
			else {				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	}
	
	public List<Produit>   getProduitbetwendatesAndPoste(Date d1, Date d2, Poste poste, TypeLavage typelavage){
		List<Produit> result=null;
		
		if(typelavage.equals(TypeLavage.Lavage) && poste !=null) {
		 result = em.createQuery("SELECT  p FROM Produit  p where   p.famille.code = 10 and p.id IN (SELECT  a.lignevente.produit.id  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.poste = :poste and a.nbvoiture >= 0 ) ", Produit.class)
				
				.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("poste", poste).getResultList();
		 }
		  if(typelavage.equals(TypeLavage.Lavage) && poste ==null) {
			result = em.createQuery("SELECT  p FROM Produit  p where   p.famille.code = 10 and p.id IN (SELECT  a.lignevente.produit.id  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.nbvoiture >= 0 ) ", Produit.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).getResultList();
			
		}
	     if(typelavage.equals(TypeLavage.Vidange) && poste !=null) {
			 result = em.createQuery("SELECT  p FROM Produit  p where   p.famille.code = 11 and p.id IN (SELECT  a.lignevente.produit.id  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.poste = :poste and a.nbvoiture >= 0 ) ", Produit.class)
			 .setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("poste", poste).getResultList();
				}
		  if(typelavage.equals(TypeLavage.Vidange) && poste ==null) {
               result = em.createQuery("SELECT  p FROM Produit  p where   p.famille.code = 11 and p.id IN (SELECT  a.lignevente.produit.id  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2  and a.nbvoiture >= 0 ) ", Produit.class)
				 .setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).getResultList();
		}
		if (result.size() > 0) {
			System.out.println("objet caisse trouvé " + "\n\n\n");
		return 				
				result;}
		else {
			
			System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
			return null;} 
	}
	 
	
	public List<Employee>   getEmployeebetwendatesAndPoste(Date d1, Date d2, Poste poste, TypeLavage typelavage){
		List<Employee> result=null;
		
		if(typelavage.equals(TypeLavage.Lavage) && poste !=null) {
		 result = em.createQuery("SELECT p FROM Employee  p where p.fonction = :fonction and p.matricule  IN (SELECT  a.employee.matricule  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.poste = :poste and a.nbvoiture >= 0 ) ", Employee.class)
				
				 .setParameter("fonction", "LAVEUR").setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("poste", poste).getResultList();
		 }
		  if(typelavage.equals(TypeLavage.Lavage) && poste ==null) {
			result = em.createQuery("SELECT p FROM Employee  p where p.fonction = :fonction and   p.matricule  IN (SELECT  a.employee.matricule  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2  and a.nbvoiture >= 0) ", Employee.class)
					
					 .setParameter("fonction", "LAVEUR").setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).getResultList();
			
		}
	     if(typelavage.equals(TypeLavage.Vidange) && poste !=null) {
			 result = em.createQuery("SELECT p FROM Employee  p where p.fonction = :fonction and  p.matricule  IN (SELECT  a.employee.matricule   FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.poste = :poste and a.nbvoiture >= 0) ", Employee.class)
					 .setParameter("fonction", "VIDANGEUR") .setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).setParameter("poste", poste).getResultList();
				}
		  if(typelavage.equals(TypeLavage.Vidange) && poste ==null) {
               result = em.createQuery("SELECT p FROM Employee  p where p.fonction = :fonction and   p.matricule  IN (SELECT  a.employee.matricule  FROM Rendement a   where a.statut = :statut   and a.date BETWEEN :d1 and :d2 and a.nbvoiture >= 0  ) ", Employee.class)
            		   .setParameter("fonction", "VIDANGEUR") .setParameter("statut", Statut.ACTIF) .setParameter("d1", d1).setParameter("d2", d2).getResultList();
		}
		if (result.size() > 0) {
			System.out.println("objet caisse trouvé " + "\n\n\n");
		return 				
				result;}
		else {
			
			System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
			return null;} 
	}
	
	 
	public Rendement findByStatusAndGeneration(TypeLavage typelavage)  {
		 Rendement result = em.createQuery("SELECT a FROM Rendement a  where a.statut = 'ACTIF' and a.id  =(select MAX(b.id ) from Rendement b where b.statuss = 'Cloture' and b.typelavage = :typelavage)", Rendement.class)
					 	.setParameter("typelavage", typelavage)  .getSingleResult();
			if (result!=null) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;
				
			}
	}
	 
	public List<Rendement> getAllventeparDate3(String d,Employee e) {
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.dates = :d1   and a.employee.matricule = :employee and a.nbvoiture >= 0 and a.statuss = :statuss", Rendement.class)
					 	.setParameter("statut", Statut.ACTIF) .setParameter("d1", d).setParameter("employee", e.getMatricule()).setParameter("statuss", Statuss.Cloture).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;
				
			}
	}
	public List<String> totalheureParlaveur(String d,Employee e) {
		Query q = em.createQuery ("SELECT u.duree FROM Rendement u where u.dates = :d1   and u.statut = :statut   and u.employee.matricule = :employee and u.duree != null and u.nbvoiture >= 0 "
				 ).setParameter("d1", d).setParameter("statut", Statut.ACTIF).setParameter("employee", e.getMatricule());
		 List<String>  result =  q.getResultList();
		if(result !=null)
		return result;
		else return null;
	}
	
	public List<String> totalheureParlaveurAndposte(String d,Employee e,Poste poste){
		Query q = em.createQuery ("SELECT u.duree FROM Rendement u where u.dates = :d1   and u.statut = :statut   and u.employee.matricule = :employee and u.duree != null and u.poste = :poste and u.nbvoiture >= 0"
				 ).setParameter("d1", d).setParameter("statut", Statut.ACTIF).setParameter("employee", e.getMatricule()).setParameter("poste", poste);
		 List<String>  result =  q.getResultList();
		if(result !=null)
		return result;
		else return null;
	}
	public List<String> totalheureParServiceaNDpOSTE(String d,Produit e,Poste poste){
		Query q = em.createQuery ("SELECT u.duree FROM Rendement u where u.dates = :d1   and u.statut = :statut   and u.lignevente.produit.id = :produit and u.duree != null  and u.poste = :poste and u.nbvoiture >= 0"
				 ).setParameter("d1", d).setParameter("statut", Statut.ACTIF).setParameter("produit", e.getId()).setParameter("poste", poste);
		 List<String>  result =  q.getResultList();
		if(result !=null)
		return result;
		else return null;
	}
	 
	
	public List<String> totalheureParService(String d,Produit e) {
		Query q = em.createQuery ("SELECT u.duree FROM Rendement u where u.dates = :d1   and u.statut = :statut   and u.lignevente.produit.id = :produit and u.duree != null and u.nbvoiture >= 0"
				 ).setParameter("d1", d).setParameter("statut", Statut.ACTIF).setParameter("produit", e.getId());
		 List<String>  result =  q.getResultList();
		if(result !=null)
		return result;
		else return null;
	}
	public List<Rendement> getAllventeparDate3AndProduit(String d, Produit e) {
		 List<Rendement> result = em.createQuery("SELECT a FROM Rendement a  where a.statut = :statut   and a.dates = :d1   and a.lignevente.produit.id = :produit and a.nbvoiture >= 0 and a.statuss = :statuss", Rendement.class)
					
					.setParameter("statut", Statut.ACTIF) .setParameter("d1", d).setParameter("produit", e.getId()).setParameter("statuss", Statuss.Cloture).getResultList();
			if (result.size() > 0) {
				System.out.println("objet caisse trouvé " + "\n\n\n");
			return 
					
					result;}
			else {
				
				System.out.println("pas de ligne de vente avec ces critères" +" "+ "\n\n\n");
				return null;}
	}

 



	 

}
