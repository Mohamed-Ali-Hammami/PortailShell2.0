package com.tn.shell.model.vetement;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Imagevetement")
public class ImageVetement   implements Serializable{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
     private Integer id;
 
	 
	 @Lob
	 @Column(name = "image", columnDefinition="BLOB")
	private byte[] image;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}
