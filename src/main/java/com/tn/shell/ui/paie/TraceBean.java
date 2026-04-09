package com.tn.shell.ui.paie;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
 

import com.tn.shell.model.paie.*;
import com.tn.shell.service.paie.*;

@ManagedBean(name = "TraceBean")
@SessionScoped
public class TraceBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceTrace}")
	ServiceTrace serviceTrace;
	private Tracepaie trace;
	private List<Tracepaie> listTrace;
	
	public String getAlltrace(){
		listTrace=new ArrayList<Tracepaie>();
		listTrace=serviceTrace.getAll();
		return SUCCESS;
	}

	public ServiceTrace getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTrace serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public Tracepaie getTrace() {
		return trace;
	}

	public void setTrace(Tracepaie trace) {
		this.trace = trace;
	}

	public List<Tracepaie> getListTrace() {
		return listTrace;
	}

	public void setListTrace(List<Tracepaie > listTrace) {
		this.listTrace = listTrace;
	}
	
	
	 
}
