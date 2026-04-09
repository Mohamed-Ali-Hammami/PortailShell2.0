package  com.tn.shell.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tn.shell.dao.shop.*;
import com.tn.shell.model.shop.*;


 

@Service("ServiceTicket")
public class ServiceTicket {
	@Autowired
	TicketDAO TicketDAO;

	public List<Ticket> getAll() {
		return TicketDAO.getAll();
	}
	public List<Ticket> getticketbydate(String d){
		return TicketDAO.getticketbydate(d);
	}
	public void update(Ticket t) {
		TicketDAO.update(t);
	}
	
	public void save(Ticket t){
		TicketDAO.save(t);
	}
	public Ticket getbyvaleur(Integer t){
		return TicketDAO.getbyvaleur(t);
	}
	 
	 
}
