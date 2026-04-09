package com.tn.shell.model.gestat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tn.shell.model.paie.Employee;

@Entity
@Table(name = "Pompiste")
public class Pompiste {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "caisseid")
	private Caisse caisse;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "employeeid")
	private Employee employee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public Employee  getEmployee() {
		return employee;
	}

	public void setEmployee(Employee  employee) {
		this.employee = employee;
	}

	 
	
	
}
