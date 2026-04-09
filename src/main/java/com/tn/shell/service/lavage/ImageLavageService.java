package com.tn.shell.service.lavage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.lavage.ImagelavageDAO;
import com.tn.shell.model.lavage.Imagelavage;
import com.tn.shell.model.lavage.Marque;
import com.tn.shell.model.shop.Produit;

@Service("ImagelavageService")
public class ImageLavageService {
	
	@Autowired
	ImagelavageDAO imagelavageDAO;
	
	public List<Imagelavage> getAll(){
		return imagelavageDAO.getAll();
	}
	public void save(Imagelavage c) {
		imagelavageDAO.save(c);
	}
	public Integer getmaxcode() {
		return imagelavageDAO.getmaxcode();
	}
	public void update(Imagelavage c) {
		imagelavageDAO.update(c);
	}
	public Imagelavage getImagelavagebyid(Integer id) {
		return imagelavageDAO.getImagelavagebyid(id);
	}
	public List<Imagelavage> getImagelavagebyMarque(Marque f){
		return imagelavageDAO.getImagelavagebyMarque(f);
	}
	public List<Imagelavage> getImagelavagebyArticle(Produit f){
		return imagelavageDAO.getImagelavagebyArticle(f);
	}
}
