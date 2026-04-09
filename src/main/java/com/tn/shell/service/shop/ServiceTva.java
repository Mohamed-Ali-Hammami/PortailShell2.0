package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;

 

@Service("ServiceTva")
public class ServiceTva {
	@Autowired
	TvaDAO tvaDAO;

	public List<Tva> getAll() {
		return tvaDAO.getAll();
	}

	public void update(Tva t) {
		tvaDAO.update(t);
	}
	
	public void save(Tva t){
		tvaDAO.save(t);
	}
	public Tva getbyvaleur(Integer t){
		return tvaDAO.getbyvaleur(t);
	}
	 public List<Integer> getAllvaleur(){
		 return tvaDAO.getAllvaleur();
	 }
	 public Tva getbyid(Integer t){
			return tvaDAO.getbyid(t);
		}
}
