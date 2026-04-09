package com.tn.shell.model.paie;

import java.util.List;

public class Historique {

	 private Employee employee;
     private double nbAbsence;
     private double nbPresence;
     private Integer note;
	 private List<Pointage> pointage;
	 private List<Paie> paie;
     private List<Note> listNote;
	 
	 
     public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getNbAbsence() {
		return nbAbsence;
	}
	public void setNbAbsence(double nbAbsence) {
		this.nbAbsence = nbAbsence;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public List<Pointage> getPointage() {
		return pointage;
	}
	public void setPointage(List<Pointage> pointage) {
		this.pointage = pointage;
	}
	public List<Note> getListNote() {
		return listNote;
	}
	public void setListNote(List<Note> listNote) {
		this.listNote = listNote;
	}
	public List<Paie> getPaie() {
		return paie;
	}
	public void setPaie(List<Paie> paie) {
		this.paie = paie;
	}
	public double getNbPresence() {
		return nbPresence;
	}
	public void setNbPresence(double nbPresence) {
		this.nbPresence = nbPresence;
	}
     
     
     
}
