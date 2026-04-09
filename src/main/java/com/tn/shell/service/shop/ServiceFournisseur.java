package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;

 

@Service("ServiceFournisseur")
public class ServiceFournisseur {
	@Autowired
	FournisseurDAO fournisseurDAO;
	
	public Fournisseur getbymf(String name) {
		return fournisseurDAO.getbymf(name);
	}
	 public List<String> getAllnom(){
			return fournisseurDAO.getAllnom();
	 }
	 public Fournisseur getbyid(Integer name) {
		 return fournisseurDAO.getbyid(name);
	 }
	public void save(Fournisseur fournisseur){
		fournisseurDAO.save(fournisseur);
	}
	public Fournisseur getbyname(String name){
		return fournisseurDAO.getbyname(name);
	}
	public List<Fournisseur> getAll(){
		return fournisseurDAO.getAll();
	}
	public void update(Fournisseur fournisseur){
		fournisseurDAO.update(fournisseur);
	}
	public void delete(Fournisseur fournisseur){
		fournisseurDAO.delete(fournisseur);
	}
}
