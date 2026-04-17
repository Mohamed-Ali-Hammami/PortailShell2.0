package com.tn.shell.dao.paie;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tn.shell.model.paie.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public List<Employee> getAll() {
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut order by a.code asc", Employee.class)
				.setParameter("statut", Statut.ACTIF).getResultList();
		return result;
	}
	
	public List<Employee> getEmployeefincontrat(Date   date ){
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.datecontrat <= :statu  ", Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("statu", date).getResultList();
		return result;
	}
	
	@Transactional
	public List<Employee> getAll5() {
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.status != :statu order by a.code asc", Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("statu", Status.ParVoiture).getResultList();
		return result;
	}
	@Transactional
	 public List<Employee> getEmployeeByFonction(String nom) {
		 List<Employee> result = em.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.fonction = :nom", Employee.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("nom",nom).getResultList();
		 if (result.size() > 0){
	            return result;}
	        else{
	            return null;}  
		 
	 }
	@Transactional
	public List<Employee> getEmployeeByCategorie(Categorie c){
		List<Employee> result = em.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.contrat.id = :nom", Employee.class).setParameter("statut", Statut.ACTIF)
				 .setParameter("nom",c.getId()).getResultList();
		 if (result.size() > 0){
	            return result;}
	        else{
	            return null;} 
	}
	@Transactional
	public Employee getEmployeeByNom(String nom) {
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.nom = :nom", Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Employee getEmployeeById(Integer nom) {
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.matricule = :nom",
						Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public Employee getEmployeeBycode(Integer nom) {
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut and a.code = :nom", Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nom", nom).getResultList();
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public List<Employee> getEmployeeparnature(String nature) {
		List<Employee> result = em
				.createQuery(
						"SELECT a FROM Employee a  where a.statut = :statut and a.nature = :nature order by a.code asc",
						Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nature", nature).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}
	@Transactional
	public List<Employee> getEmployeeparfonction(String nature) {
		List<Employee> result = em
				.createQuery(
						"SELECT a FROM Employee a  where a.statut = :statut and a.fonction = :nature",
						Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("nature", nature).getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Transactional
	public List<Employee> getEmployeeparetat(Statut statut) {
		List<Employee> result = em
				.createQuery("SELECT a FROM Employee a  where a.statut = :statut order by a.code asc", Employee.class)
				.setParameter("statut", statut).getResultList();
		return result;
	}

	@Transactional
	public List<Employee> getEmployeeparstats(Status status) {
		List<Employee> result = em
				.createQuery(
						"SELECT a FROM Employee a  where a.statut = :statut and a.status = :status order by a.code asc",
						Employee.class)
				.setParameter("statut", Statut.ACTIF).setParameter("status", status).getResultList();
		return result;
	}

	@Transactional
	public void save(Employee c) {
		em.persist(c);
	}

	@Transactional
	public void update(Employee c) {
		Employee a = em.find(Employee.class, c.getMatricule());
		 a=c;
		em.merge(a);

	}

	@Transactional
	public void detele(Employee c) {
		Employee a = em.find(Employee.class, c.getMatricule());
		a.setStatut(c.getStatut());
		em.merge(a);

	}

}
