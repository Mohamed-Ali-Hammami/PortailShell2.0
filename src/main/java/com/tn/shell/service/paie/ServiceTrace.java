package com.tn.shell.service.paie;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
@Service("ServiceTrace")
public class ServiceTrace {
	private static final Logger LOGGER = Logger.getLogger(ServiceTrace.class.getName());
	@Autowired
	TraceDAO traceDAO;
	public void save(Tracepaie trace){
		try {
			traceDAO.save(trace);
		} catch (RuntimeException ex) {
			// Trace must never break functional flows (paie/shop/etc.).
			LOGGER.log(Level.WARNING, "Ignoring Tracepaie transaction failure", ex);
		}
	}
	public List<Tracepaie> getAll(){
		return traceDAO.getAll();
	}
}
