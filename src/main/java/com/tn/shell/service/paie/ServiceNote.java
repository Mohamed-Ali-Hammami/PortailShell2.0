package com.tn.shell.service.paie;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.shell.dao.paie.*;
import com.tn.shell.model.paie.*;
 

@Service("ServiceNote")
public class ServiceNote {
	@Autowired
	NoteDAO noteDAO;
	
	public void save(Note note){
    noteDAO.save(note);
	}
	public List<Note> getNotesByEmployer(Employee e){
		return noteDAO.getNotesByEmployer(e);
	}
	public List<Note> getAll(Integer annee,Integer mois){
		return noteDAO.getAll(annee, mois);
	}
	
	public void update(Note note){
		noteDAO.update(note);
	}
	public void delete(Note note){
		noteDAO.detele(note);
	}
	public Note getNotesByEmployee(Employee e,Integer annee){
		return noteDAO.getNotesByEmployee(e, annee);
	}
	
	public Note getMaxPointageconge(){
		return noteDAO.getMaxPointageconge();
	}
}
