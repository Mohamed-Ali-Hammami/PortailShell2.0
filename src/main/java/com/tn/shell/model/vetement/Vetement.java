package com.tn.shell.model.vetement;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tn.shell.model.paie.Employee;

@Entity
@Table(name = "Vetement")
public class Vetement  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	 
	@ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "imageid")
	private ImageVetement image;
	private String vetement;
private String dates;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ImageVetement getImage() {
		return image;
	}

	public void setImage(ImageVetement image) {
		this.image = image;
	}

	public String getVetement() {
		return vetement;
	}

	public void setVetement(String vetement) {
		this.vetement = vetement;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

  
	@Transient
	private String detail;
		public String getDetail() {
			if(image!=null)
			detail=Base64.getMimeEncoder().encodeToString(image.getImage());
		 return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}
	
	
}
