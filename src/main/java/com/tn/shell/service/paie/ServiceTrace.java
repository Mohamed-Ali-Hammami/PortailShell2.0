package com.tn.shell.service.paie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
@Service("ServiceTrace")
public class ServiceTrace {
	@Autowired
	TraceDAO traceDAO;
	public void save(Tracepaie trace){
		traceDAO.save(trace);
	}
	public List<Tracepaie> getAll(){
		return traceDAO.getAll();
	}
}
