package com.tn.shell.ui.shop;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.shell.model.lavage.TypeLavage;
import com.tn.shell.model.paie.Employee;
import com.tn.shell.model.shop.*;
import com.tn.shell.service.paie.ServiceEmployee;
import com.tn.shell.service.shop.ServiceLignevente;
import com.tn.shell.service.shop.ServiceProduit;
import com.tn.shell.service.shop.ServiceRendement;
import com.tn.shell.service.shop.ServiceVente;
@ManagedBean(name = "RapportLavage")
@SessionScoped
public class RapportLavage {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{ServiceProduit}")
	ServiceProduit serviceProduit;
	@ManagedProperty(value = "#{ServiceLignevente}")
	ServiceLignevente serviceLignevente;
	@ManagedProperty(value = "#{ServiceRendement}")
    ServiceRendement   serviceRendement;
	 
	private List<Lavage> listlavage;
	private Date date1;
	private List<Vidange> listvidange;
	private Date date2;
	private String mois;
	private Integer annee;
	public String rapportlavage() {
		 
		date1=new Date();
		date2=new Date();
		listlavage=new ArrayList<Lavage>();
		 
		return SUCCESS;
	}
	
	 public String rapportvidange() {
		  
			date1=new Date();
			date2=new Date();
			listvidange=new ArrayList<Vidange>();
			 
		 return SUCCESS;
	 }
	 
	 public String getrapportvidange() {
		 if (date1 == null) {
			 date1 = new Date();
		 }
		 if (date2 == null) {
			 date2 = new Date();
		 }
		 DecimalFormat df=new DecimalFormat("0.000");
			DecimalFormat dfs=new DecimalFormat("0");
			mois=getMoisbyIntger(date1.getMonth()+1);
			annee=date1.getYear()+1900;
			Poste p=Poste.Poste1;
			 double t1=0;double t2=0; 
			 double r1=0;double r2=0; 
				List<String> ld = new ArrayList<String>();
				listvidange=new ArrayList<Vidange>();
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cursor = Calendar.getInstance();
				cursor.setTime(date1);
				Calendar end = Calendar.getInstance();
				end.setTime(date2);
				if (cursor.after(end)) {
					Calendar temp = cursor;
					cursor = end;
					end = temp;
				}
				while (!cursor.after(end)) {
					ld.add(sf.format(cursor.getTime()));
					cursor.add(Calendar.DATE, 1);
				}
				for(String s:ld) {

					//List<Lignevente> listv=new ArrayList<Lignevente>();
				//	listv=serviceLignevente.getAllventeparDate(s);
					double qtej=0;	 double qtepj=0;	 
					double recj=0;double recpj=0; 
					
					qtej=serviceRendement.getnbvBydate2(s, Poste.Poste1,TypeLavage.Vidange);
					recj=serviceRendement.getmontantvBydate2(s, Poste.Poste1,TypeLavage.Vidange);
					qtepj=serviceRendement.getnbvBydate2(s, Poste.Poste2,TypeLavage.Vidange);
					recpj=serviceRendement.getmontantvBydate2(s, Poste.Poste2,TypeLavage.Vidange);
				/*for(Lignevente v:listv) {
					
					if(v.getProduit().getFamille().getCode()==11 && v.getProduit().getNom().contains("VIDANGE")) {
						if( v.getPoste().equals(Poste.Poste1) ){
						 
							qtej=qtej+v.getQuantite();
							recj=recj+v.getTotalttc();
						}
						 
					 
						else {
							 
								qtepj=qtepj+v.getQuantite();
								recpj=recpj+v.getTotalttc();
							 
							 
						}
					}
						
				}*/
				Vidange l1=new Vidange();				 
				l1.setDates(s); 
				l1.setNbvoiturej(dfs.format(qtej));l1.setNbvoiturejp(dfs.format(qtepj));				 
				l1.setRecettej(df.format(recj));l1.setRecettejp(df.format(recpj));				 
				l1.setTotalrecette(df.format(recj+recpj));
				l1.setTotaljournee(dfs.format(qtej+qtepj));
				listvidange.add(l1); 
				t1=t1+qtej;
				t2=t2+qtepj;				 
				r1=r1+recj;
				r2=r2+recpj; 
				}
				Vidange l2=new Vidange();
				l2.setDates("Total");
				l2.setNbvoiturej(dfs.format(t1));				 
				l2.setNbvoiturejp(dfs.format(t2)); 
				l2.setRecettej(df.format(r1)); 	 
				l2.setTotalrecette(df.format(r1+r2));
				l2.setTotaljournee(dfs.format(t1+t2));
				listvidange.add(l2); 
		 return SUCCESS;
	 }
	public String getrapportlavage() {
		if (date1 == null) {
			date1 = new Date();
		}
		if (date2 == null) {
			date2 = new Date();
		}
		DecimalFormat df=new DecimalFormat("0.000");
		DecimalFormat dfs=new DecimalFormat("0");
		mois=getMoisbyIntger(date1.getMonth()+1);
		annee=date1.getYear()+1900;
		Poste p=Poste.Poste1;
		 
	 double t1=0;double t2=0;double t3=0;double t4=0;
	 double r1=0;double r2=0;double r3=0;double r4=0;
		List<String> ld = new ArrayList<String>();
		listlavage=new ArrayList<Lavage>();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cursor = Calendar.getInstance();
		cursor.setTime(date1);
		Calendar end = Calendar.getInstance();
		end.setTime(date2);
		if (cursor.after(end)) {
			Calendar temp = cursor;
			cursor = end;
			end = temp;
		}
		while (!cursor.after(end)) {
			ld.add(sf.format(cursor.getTime()));
			cursor.add(Calendar.DATE, 1);
		}
		for (String s : ld) {
			//List<Lignevente> listv=new ArrayList<Lignevente>();
			//listv=serviceLignevente.getAllventeparDate(s);
			double qtej=0;	double qtem=0;double qtepj=0;	double qtepm=0;
			double recj=0;	double recm=0;double recpj=0;	double recpm=0;
		/*for(Lignevente v:listv) {
			
			if(v.getProduit().getFamille().getCode()==10 && v.getProduit().getNom().contains("LAVAGE")) {
				if( v.getPoste().equals(Poste.Poste1) ){
				if(v.getProduit().getNom().contains("J")) {
					qtej=qtej+v.getQuantite();
					recj=recj+v.getTotalttc();
				}
				else {
					qtem=qtem+v.getQuantite();
					recm=recm+v.getTotalttc();
				}
				}
				else {
					if(v.getProduit().getNom().contains("J")) {
						qtepj=qtepj+v.getQuantite();
						recpj=recpj+v.getTotalttc();
					}
					else {
						qtepm=qtepm+v.getQuantite();
						recpm=recpm+v.getTotalttc();
					}
				}
			}
				
		}*/
			
			
			qtej=serviceRendement.getnbvBydate2(s, Poste.Poste1,TypeLavage.Lavage);
			recj=serviceRendement.getmontantvBydate2(s, Poste.Poste1,TypeLavage.Lavage);
			qtepj=serviceRendement.getnbvBydate2(s, Poste.Poste2,TypeLavage.Lavage);
			recpj=serviceRendement.getmontantvBydate2(s, Poste.Poste2,TypeLavage.Lavage);
		Lavage l1=new Lavage();
		 
		l1.setDates(s); 
		l1.setNbvoiturej(dfs.format(qtej));l1.setNbvoiturejp(dfs.format(qtepj));
		l1.setNbvoiturem(dfs.format(qtem));l1.setNbvoituremp(dfs.format(qtepm));
		l1.setRecettej(df.format(recj));l1.setRecettejp(df.format(recpj));
		l1.setRecettem(df.format(recm));l1.setRecettemp(df.format(recpm));
		l1.setTotalrecette(df.format(recj+recm+recpj+recpm));
		l1.setTotaljournee(dfs.format(qtej+qtepj+qtem+qtepm));
		listlavage.add(l1); 
		t1=t1+qtej;t2=t2+qtem;t3=t3+qtepj;t4=t4+qtepm;
		r1=r1+recj;r2=r2+recm;r3=r3+recpj;r4=r4+recpm;
		}
		Lavage l2=new Lavage();
		l2.setDates("Total");
		l2.setNbvoiturej(dfs.format(t1));l2.setNbvoiturejp(dfs.format(t3));
		l2.setNbvoiturem(dfs.format(t2));l2.setNbvoituremp(dfs.format(t4));
		l2.setRecettej(df.format(r1));l2.setRecettem(df.format(r2));
		l2.setRecettejp(df.format(r3));l2.setRecettemp(df.format(r4));
		l2.setTotalrecette(df.format(r1+r2+r3+r4));
		l2.setTotaljournee(dfs.format(t1+t2+t3+t4));
		listlavage.add(l2); 
		return SUCCESS;
	}
	
	private String getMoisbyIntger(Integer moi) {
		String m = "";
		if (moi == 1)
			m = "Janvier";
		else if (moi == 2)
			m = "Fevrier";
		else if (moi == 3)
			m = "Mars";
		else if (moi == 4)
			m = "Avril";
		else if (moi == 5)
			m = "Mai";
		else if (moi == 6)
			m = "Juin";
		else if (moi == 7)
			m = "Juillet";
		else if (moi == 8)
			m = "aout";
		else if (moi == 9)
			m = "SÃ©ptembre";
		else if (moi == 10)
			m = "Octobre";
		else if (moi == 11)
			m = "Novembre";
		else if (moi == 12)
			m = "DÃ©cembre";
		return m;
	}


	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public ServiceProduit getServiceProduit() {
		return serviceProduit;
	}

	public void setServiceProduit(ServiceProduit serviceProduit) {
		this.serviceProduit = serviceProduit;
	}

	public ServiceLignevente getServiceLignevente() {
		return serviceLignevente;
	}

	public void setServiceLignevente(ServiceLignevente serviceLignevente) {
		this.serviceLignevente = serviceLignevente;
	}

	public List<Lavage> getListlavage() {
		if (listlavage == null) {
			date1 = new Date();
			date2 = new Date();
			getrapportlavage();
		}
		return listlavage;
	}

	public void setListlavage(List<Lavage> listlavage) {
		this.listlavage = listlavage;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public List<Vidange> getListvidange() {
		if (listvidange == null) {
			date1 = new Date();
			date2 = new Date();
			getrapportvidange();
		}
		return listvidange;
	}

	public void setListvidange(List<Vidange> listvidange) {
		this.listvidange = listvidange;
	}

	public ServiceRendement getServiceRendement() {
		return serviceRendement;
	}

	public void setServiceRendement(ServiceRendement serviceRendement) {
		this.serviceRendement = serviceRendement;
	}

 
	
	
}
