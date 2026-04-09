package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 
@Service("ServiceParamettre")
public class ServiceParamettre {
	@Autowired
	ParamettreDAO paramettreDAO;
	public List<Paramettre> getAll(){
		return paramettreDAO.getAll();
	}
	  
	  
	 public void update(Paramettre paramettre) {
		 paramettreDAO.update(paramettre);
	 }
	 
	 public void save(Paramettre paramettre){
		 paramettreDAO.save(paramettre);
	 }
}
