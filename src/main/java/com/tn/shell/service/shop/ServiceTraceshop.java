package com.tn.shell.service.shop;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;

@Service("ServiceTraceshop")
public class ServiceTraceshop {
	private static final Logger LOGGER = Logger.getLogger(ServiceTraceshop.class.getName());
	@Autowired
	TraceshoDAO traceDAO;
	public void save(Traceshop trace){
		try {
			traceDAO.save(trace);
		} catch (RuntimeException ex) {
			LOGGER.log(Level.WARNING, "Ignoring Traceshop transaction failure", ex);
		}
	}
	public List<Traceshop> getAll(){
		return traceDAO.getAll();
	}
}
