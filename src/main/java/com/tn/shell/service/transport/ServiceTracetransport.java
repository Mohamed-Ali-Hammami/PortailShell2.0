package com.tn.shell.service.transport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.TracetransportDAO;
import com.tn.shell.model.transport.Tracetransport;
 
@Service("ServiceTracetransport")
public class ServiceTracetransport {
	@Autowired
	TracetransportDAO traceDAO;
	public void save(Tracetransport trace){
		traceDAO.save(trace);
	}
	public List<Tracetransport> getAll(){
		return traceDAO.getAll();
	}
}
