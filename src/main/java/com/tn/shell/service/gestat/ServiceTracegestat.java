package com.tn.shell.service.gestat;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.TracegestatDAO;
import com.tn.shell.dao.shop.*;
import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.shop.*;

@Service("ServiceTracegestat")
public class ServiceTracegestat {
	private static final Logger LOGGER = Logger.getLogger(ServiceTracegestat.class.getName());
	@Autowired
	TracegestatDAO traceDAO;
	public void save(Tracegestat trace){
		try {
			traceDAO.save(trace);
		} catch (RuntimeException ex) {
			LOGGER.log(Level.WARNING, "Ignoring Tracegestat transaction failure", ex);
		}
	}
	public List<Tracegestat> getAll(){
		return traceDAO.getAll();
	}
}
