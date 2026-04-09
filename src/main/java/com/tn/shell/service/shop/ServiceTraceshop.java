package com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;

@Service("ServiceTraceshop")
public class ServiceTraceshop {
	@Autowired
	TraceshoDAO traceDAO;
	public void save(Traceshop trace){
		traceDAO.save(trace);
	}
	public List<Traceshop> getAll(){
		return traceDAO.getAll();
	}
}
