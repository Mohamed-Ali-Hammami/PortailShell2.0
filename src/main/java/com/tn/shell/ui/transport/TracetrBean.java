package com.tn.shell.ui.transport;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.transport.Tracetransport;
import com.tn.shell.service.transport.*;
 

@ManagedBean(name = "TracetrBean")
@SessionScoped
public class TracetrBean {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{ServiceTracetransport}")
	ServiceTracetransport serviceTrace;
	private Tracetransport trace;
	private List<Tracetransport> listTrace;
	
	public String getAlltrace(){
		listTrace=new ArrayList<Tracetransport>();
		listTrace=serviceTrace.getAll();
		return SUCCESS;
	}

	public ServiceTracetransport getServiceTrace() {
		return serviceTrace;
	}

	public void setServiceTrace(ServiceTracetransport serviceTrace) {
		this.serviceTrace = serviceTrace;
	}

	public Tracetransport getTrace() {
		return trace;
	}

	public void setTrace(Tracetransport trace) {
		this.trace = trace;
	}

	public List<Tracetransport> getListTrace() {
		return listTrace;
	}

	public void setListTrace(List<Tracetransport> listTrace) {
		this.listTrace = listTrace;
	}
	
	
	 
}
