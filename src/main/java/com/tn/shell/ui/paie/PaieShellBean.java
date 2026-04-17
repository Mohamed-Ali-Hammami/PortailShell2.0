package com.tn.shell.ui.paie;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "PaieShellBean")
@SessionScoped
public class PaieShellBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String currentView = "/shellpaie/fragments/home.xhtml";

	public String getCurrentView() {
		if (currentView == null || currentView.trim().isEmpty()) {
			currentView = "/shellpaie/fragments/home.xhtml";
		}
		return currentView;
	}

	public void setCurrentView(String currentView) {
		this.currentView = currentView;
	}

	private String show(String view) {
		currentView = view;
		return null;
	}

	public String showHome() {
		return show("/shellpaie/fragments/home.xhtml");
	}

	public String showEmployees() {
		return show("/shellpaie/fragments/listemplyee.xhtml");
	}

	public String showAvances() {
		return show("/shellpaie/fragments/gestion_des_avances.xhtml");
	}

	public String showPointage() {
		return show("/shellpaie/fragments/pointage.xhtml");
	}

	public String showPaieDeclaree() {
		return show("/shellpaie/fragments/paie.xhtml");
	}

	public String showPaieNonDeclaree() {
		return show("/shellpaie/fragments/paieNondeclaree.xhtml");
	}

	public String showFichePaie() {
		return show("/shellpaie/fragments/fiche_de_paie.xhtml");
	}

	public String showGestionElements() {
		return show("/shellpaie/fragments/gestion_des_elements_de_la_paie.xhtml");
	}

	public String showEdition() {
		return show("/shellpaie/fragments/edition.xhtml");
	}

	public String showHistorique() {
		return show("/shellpaie/fragments/historique.xhtml");
	}

	public String showJournalDeclare() {
		return show("/shellpaie/fragments/journal.xhtml");
	}

	public String showJournalNonDeclare() {
		return show("/shellpaie/fragments/journalNondeclaree.xhtml");
	}

	public String showJournalTotal() {
		return show("/shellpaie/fragments/journaltotal.xhtml");
	}

	public String showOrdreVirement() {
		return show("/shellpaie/fragments/ordre_virement.xhtml");
	}

	public String showAdministration() {
		return show("/shellpaie/fragments/categorie.xhtml");
	}

	public String showPointageConge() {
		return show("/shellpaie/fragments/pointageconge.xhtml");
	}

	public String showEtatConge() {
		return show("/shellpaie/fragments/etatconge.xhtml");
	}

	public String showJournalConge() {
		return show("/shellpaie/fragments/journalconge.xhtml");
	}

	public String showNoteAnnuaire() {
		return show("/shellpaie/fragments/note_annuaire.xhtml");
	}

	public String showEtatPrime() {
		return show("/shellpaie/fragments/etatprime.xhtml");
	}

	public String showJournalPrime() {
		return show("/shellpaie/fragments/journalprime.xhtml");
	}
}
