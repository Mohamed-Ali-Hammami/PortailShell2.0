package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 

@Service("ServiceFamilleaticle")
public class ServiceFamilleaticle {
	@Autowired
	FamillearticleDAO famillearticleDAO;

	public void save(Famillearticle typearticle) {
		famillearticleDAO.save(typearticle);
	} 
	public  List<Famillearticle> getAll(){
		return famillearticleDAO.getAll();
	} 
	public  List<String> getAllnom(){
		return famillearticleDAO.getAllnom();
	}
	public Famillearticle findbyDesignation(String designation) {
		return famillearticleDAO.findbyDesignation(designation);
	} 
	public void update(Famillearticle typearticle) {
		famillearticleDAO.update(typearticle);
	} 
	public void delete(Famillearticle typearticle) {
		famillearticleDAO.delete(typearticle);
		
	}
	 
	public List<Famillearticle> getAll2(){
		return famillearticleDAO.getAll2();
				
	}
}
