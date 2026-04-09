package com.tn.shell.model.transport;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tracetransport")
public class Tracetransport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String action;
	private Date date;
	private String dates;
	 
	 
	
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDates() {
		SimpleDateFormat s = new SimpleDateFormat();
		dates = s.format(date);
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}
 

	 

	 
 
	
	
}
