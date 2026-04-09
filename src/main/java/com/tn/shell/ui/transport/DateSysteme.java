package com.tn.shell.ui.transport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSysteme {
	public String  getDateSysteme(Date dat){
		 
		SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
		String d=s.format(dat);
		return d;
	}
}
