package com.tn.shell.dao.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tn.shell.model.paie.*;
@Repository
public class CategorieDaoImpl implements CategorieDAO {

	 @PersistenceContext
	 private EntityManager em;
	 
	 @Transactional
	public void save(Categorie annee) {
		  
	em.persist(annee);
		
	}
	 @Transactional
	public List<Categorie> getAll() {
		List<Categorie> result = em.createQuery("SELECT a FROM Categorie a ", Categorie.class).getResultList();
	    return result;
	}
	public  List<Categorie> findbyDesignation(TypeCat designation) {
		 List<Categorie> AnneeListem=em.createQuery("SELECT u FROM  Categorie u where u.cat.cat = :designation group by u.cat.id",Categorie.class).setParameter("designation", designation).getResultList();
	        
	        if (AnneeListem.size() > 0){
	            return AnneeListem;}
	        else{
	            return null;}   
	}
	@Transactional
	 public Categorie findbydegreeandcat(Integer cat,Integer degree) {
		 List<Categorie> AnneeListem=em.createQuery("SELECT u FROM  Categorie u where u.cat.valeur = :valeur1 and u.degree.valeur = :valeur2",Categorie.class).
				 setParameter("valeur1", cat).
				 setParameter("valeur2", degree).
				 getResultList();
	        
	        if (AnneeListem.size() > 0){
	            return AnneeListem.get(0);}
	        else{
	            return null;}  
	 }
	 
	 @Transactional
	public List<Categorie> getcategoriebydegre(Cat d){
		 List<Categorie> AnneeListem=em.createQuery("SELECT u FROM  Categorie u where u.cat.id = :id",Categorie.class).setParameter("id", d.getId()).getResultList();
	        
	        if (AnneeListem.size() > 0){
	            return AnneeListem;}
	        else{
	            return null;}   
	}
	 @Transactional
	 public List<Categorie> getcategoriebycat(Integer d){
		 List<Categorie> AnneeListem=em.createQuery("SELECT u FROM  Categorie u where u.cat.id = :id",Categorie.class).setParameter("id", d).getResultList();
	        
	        if (AnneeListem.size() > 0){
	            return AnneeListem;}
	        else{
	            return null;}   
	 }
	@Transactional
	public void update(Categorie c) {
		Categorie a=em.find(Categorie.class, c.getId());
	     a.setCat(c.getCat());
		 a.setDate(c.getDate());
		 a.setDegree(c.getDegree());
		 a.setPrix_heur(c.getPrix_heur());
		 em.merge(a);
		 

		
	}
	public Categorie findbyid(Integer id) {
		Categorie a=em.find(Categorie.class, id);
		return a;
	}
	 

}
