package com.tn.shell.ui.gestat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	 
	public static void main(String[] args)   {
		String ss= "1554";		
		
		String ss4= "1854";		
		  String ch=ss.substring(0,2)+":"+ss.substring(2)+":00";	
		  String ch2=ss4.substring(0,2)+":"+ss4.substring(2)+":00";	
		  
		 
		  try {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			    Date parsedDate = dateFormat.parse(ch);
			    Date parsedDate2 = dateFormat.parse(ch2);
			    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			    Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
			    
			} catch(Exception e) { //this generic but you can control another types of exception
			}
		  
		  
		  
       }
	}
