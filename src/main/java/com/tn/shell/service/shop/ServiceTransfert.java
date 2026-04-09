package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 

@Service("ServiceTransfert")
public class ServiceTransfert {
	@Autowired
	TransfertDAO transfertDAO;

	public List<Transfert> getAll() {
		return transfertDAO.getAll();
	}

	public void update(Transfert t) {
		transfertDAO.update(t);
	}
	
	public void save(Transfert t){
		transfertDAO.save(t);
	}
	   
	 
}
