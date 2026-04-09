package com.tn.shell.dao.transport;

import java.util.List;

import com.tn.shell.model.transport.Tracetransport;

 


 
public interface TracetransportDAO {
public void save(Tracetransport trace);
public List<Tracetransport> getAll();

}
