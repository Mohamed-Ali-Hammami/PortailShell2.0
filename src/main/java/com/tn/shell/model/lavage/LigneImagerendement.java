package com.tn.shell.model.lavage;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tn.shell.model.shop.Rendement;

@Entity
@Table(name = "Ligneimagerendement")
public class LigneImagerendement    implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	 
	@ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "rendement_id")
	private Rendement rendement;
	private String position;
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "imageid")
	private Imagelavage image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Rendement getRendement() {
		return rendement;
	}

	public void setRendement(Rendement rendement) {
		this.rendement = rendement;
	}

	public Imagelavage getImage() {
		return image;
	}

	public void setImage(Imagelavage image) {
		this.image = image;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
