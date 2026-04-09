package com.tn.shell.dao.shop;

import java.util.Date;
import java.util.List;

import com.tn.shell.model.shop.*;

 

public interface TicketDAO {
public List<Ticket> getAll();
public List<Ticket> getticketbydate(String d);
  
public void update(Ticket t);
public void save(Ticket t);
public Ticket getbyvaleur(Integer t);
 
}
