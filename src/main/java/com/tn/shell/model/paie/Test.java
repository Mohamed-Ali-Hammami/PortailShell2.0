package com.tn.shell.model.paie;
import java.text.SimpleDateFormat;
import java.util.*;
 
public class Test {
	 
    public static int nbOfMonthsBetweenTwoDates(String dateString1, String dateString2) throws Exception  {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sdf.parse(dateString1);
        GregorianCalendar gc1 = new GregorianCalendar();
        gc1.setTime(date1);
        Date date2 = sdf.parse(dateString2);
        GregorianCalendar gc2 = new GregorianCalendar();
        gc2.setTime(date2);
        int gap = 0;
        gc1.add(GregorianCalendar.MONTH, 1);
        while(gc1.compareTo(gc2)<=0) {
            gap++;
            gc1.add(GregorianCalendar.MONTH, 1);
        }
        return gap;
    }
 
    public static void main(String[] args) {
        try {
        	int nba=nbOfMonthsBetweenTwoDates("1/5/2017", "1/5/2019") ;
        } catch (Exception e) {
        }
    }
}
