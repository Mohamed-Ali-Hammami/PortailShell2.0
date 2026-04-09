package com.tn.shell.model.paie;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Imageemployee")
public class ImageEmployee   implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
     private Integer id;
 
//	@com.fasterxml.jackson.annotation.JsonIgnore
	 @Lob
	 @Column(name = "image", columnDefinition="BLOB")
	private byte[] image;
	 @Transient
		private String detail;
			public String getDetail() {
				if(image!=null)
				detail=Base64.getMimeEncoder().encodeToString(image);
			 return detail;
			}

		 
	
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
