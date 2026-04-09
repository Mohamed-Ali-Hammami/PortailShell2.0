package com.tn.shell.model.gestat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public enum Getformanumber {
	espece(new DecimalFormatSymbols(Locale.FRANCE),new DecimalFormat("0.000",new DecimalFormatSymbols(Locale.FRANCE)));
	  DecimalFormatSymbols dfs;
	  DecimalFormat df;
	  
	  Getformanumber(DecimalFormatSymbols id, DecimalFormat value){
	        this.dfs = id;
	        this.df= value;
	}
	public DecimalFormatSymbols getDfs() {
		return dfs;
	}
	public void setDfs(DecimalFormatSymbols dfs) {
		this.dfs = dfs;
	}
	public DecimalFormat getDf() {
		return df;
	}
	public void setDf(DecimalFormat df) {
		this.df = df;
	}
	
	public static String  format(double nb) {
		DecimalFormatSymbols dfs=new DecimalFormatSymbols(Locale.FRANCE);
	dfs.setGroupingSeparator(' ');
	 
	 String res="";
	if(nb>=0 && nb<1000) {
		DecimalFormat df=new DecimalFormat("0.000",dfs);
		res=df.format(nb);
	}
	
	if(nb>=1000 && nb<10000) {
		DecimalFormat df=new DecimalFormat("0,000",dfs);
		res=df.format(nb);
	}
	if(nb>=10000 && nb<100000) {
		DecimalFormat df=new DecimalFormat("0,000.000",dfs);
		res=df.format(nb);
	}
	return res;
	}
	
	/*
	 * DecimalFormatSymbols dfs=new DecimalFormatSymbols(Locale.FRANCE);
		dfs.setGroupingSeparator(' ');
		 
		DecimalFormat df = new DecimalFormat("0,000.000",dfs);
		totalventeMagasins = df.format(totalventemagasin);
	 */
}
