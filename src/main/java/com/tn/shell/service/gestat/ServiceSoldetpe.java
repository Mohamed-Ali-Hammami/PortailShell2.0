package com.tn.shell.service.gestat;

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tn.shell.dao.gestat.*;
import com.tn.shell.model.gestat.*; 
 

 

@Service("ServiceSoldetpe")
public class ServiceSoldetpe {
	@Autowired
	SoldetpeDAO soldetpeDAO;
	
	public List<Soldetpe> getAll(){
		return soldetpeDAO.getAll();
	}
	 
	public void save(Soldetpe c){
		soldetpeDAO.save(c);
	}
	
	public void update(Soldetpe c){
		soldetpeDAO.update(c);
	}
	 
	 
	public Soldetpe getmaxcode() {		 
		return soldetpeDAO.getmaxcode();
	}
	 
	 
}
