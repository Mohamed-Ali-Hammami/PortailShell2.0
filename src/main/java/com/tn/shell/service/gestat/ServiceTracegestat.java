package com.tn.shell.service.gestat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.gestat.TracegestatDAO;
import com.tn.shell.dao.shop.*;
import com.tn.shell.model.gestat.Tracegestat;
import com.tn.shell.model.shop.*;

@Service("ServiceTracegestat")
public class ServiceTracegestat {
	@Autowired
	TracegestatDAO traceDAO;
	public void save(Tracegestat trace){
		traceDAO.save(trace);
	}
	public List<Tracegestat> getAll(){
		return traceDAO.getAll();
	}
}
