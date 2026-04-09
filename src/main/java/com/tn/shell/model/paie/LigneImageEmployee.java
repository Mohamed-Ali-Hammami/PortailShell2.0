package  com.tn.shell.model.paie;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tn.shell.model.paie.Employee;

@Entity
@Table(name = "Ligneimageemployee")
public class LigneImageEmployee    implements Serializable{
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
	private String position;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "imageid")
	private ImageEmployee image;

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

	public ImageEmployee getImage() {
		return image;
	}

	public void setImage(ImageEmployee image) {
		this.image = image;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
	
}
