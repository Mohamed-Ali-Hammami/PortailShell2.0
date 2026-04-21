package com.tn.shell.service.transport;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.transport.TracetransportDAO;
import com.tn.shell.model.transport.Tracetransport;
 
@Service("ServiceTracetransport")
public class ServiceTracetransport {
	private static final Logger LOGGER = Logger.getLogger(ServiceTracetransport.class.getName());
	@Autowired
	TracetransportDAO traceDAO;
	public void save(Tracetransport trace){
		try {
			traceDAO.save(trace);
		} catch (RuntimeException ex) {
			LOGGER.log(Level.WARNING, "Ignoring Tracetransport transaction failure", ex);
		}
	}
	public List<Tracetransport> getAll(){
		return traceDAO.getAll();
	}
}
