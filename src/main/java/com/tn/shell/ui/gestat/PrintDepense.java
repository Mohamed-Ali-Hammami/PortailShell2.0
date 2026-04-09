package com.tn.shell.ui.gestat;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
 
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.tn.shell.model.gestat.TransactionCredit;
import com.tn.shell.model.gestat.TransactionDepense;
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.model.shop.Ticket;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JPanel;

public class PrintDepense extends JPanel  implements Printable {
	TransactionDepense ticket;
   
   /** Constructeur par defaut de PrintRectangle */
   public PrintDepense() {
	  
   }

     
   public PrintDepense(TransactionDepense ticket2) {
	   ticket =ticket2;
	   
	   
}


public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
     
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 
    Date date = new Date();
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    Date time = new Date();
    String currDate = dateFormat.format(date);
    String currTime = timeFormat.format(time);
     
     
    if (pageIndex < 0 || pageIndex >= 1) {
        return Printable.NO_SUCH_PAGE;
    }
    Graphics2D g2d = (Graphics2D) graphics;
    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    Font font = new Font("Arial",Font.PLAIN, 14);
    
    g2d.setFont(font);
    String text="Station SHELL SIJOUMI";
           /* + "\nTicket Ne "+ticket.getId()
            + "\nDate "+ticket.getDates()+"e"+ticket.getHeure()
            + "\nNom    Qte    Prix U   Montant";*/
    
    drawString(graphics,text  , 20, 20);
    Font font2 = new Font("Arial",Font.ITALIC, 10);
     if(ticket.getId()!=null)
      text="\nNum Bon "+ticket.getId() +"  Pompe :" +ticket.getPompe();
     else 
    	 text="Pompe :" +ticket.getPompe();
    g2d.setFont(font2);
    drawString(graphics,text  , 20, 28);  
   // text="\n  Ticket Ne "+ticket.getId();
    text="\n Vehicule  "+ticket.getVhecule();
    drawString(graphics,text  , 20, 45); 
  if(ticket.getChauffeur()!=null) {
    text="\n Chauffeur "+ticket.getChauffeur();
    if(ticket.getChauffeur()!=null)
    drawString(graphics,text  , 20,65 );  
  }
	DecimalFormat df = new DecimalFormat("0.000");
  if(ticket.getKilometrage()!=null) {
    text="\n Kilometrage "+ticket.getKilometrage() +"  Parcouru: "+ticket.getTrajet() ;
    
     drawString(graphics,text  , 20, 85);
     
     drawString(graphics," Moyenne Consommation  " +df.format(ticket.getPourcentage())  , 20, 110);    
  }
     g2d.setFont(font2);
     text="\n Date  "+dateFormat.format(date)+"  e  "+ticket.getTime();
     //text="\n Date    "+"23/06/2020"+"     e  "+"13:49";
     drawString(graphics,text  , 20, 130);
     font2 = new Font("TimesRoman",Font.ROMAN_BASELINE, 10);
     graphics.setFont(font2);
     int s=155;
   
     drawString(graphics,"\nArticle"  , 20,135);
     drawString(graphics,"\nLitrage"  , 100, 135);
     drawString(graphics,"\nPrix"  , 140, 135);
     
     drawString(graphics,"\nMontant"  , 170, 135);
     String t="";
     font2 = new Font("TimesRoman",Font.ROMAN_BASELINE, 10);
     graphics.setFont(font2);
       
    
    
       
   	   // t="\n"+ticket.getArticle()+"                        "+ticket.getQuantite()+"   "+ticket.getPrix()+"     "+ticket.getMontant();
   		//drawString(graphics,t  , 20, s);
   	     drawString(graphics,"\n"+ticket.getArticle() , 20, s);
         drawString(graphics,"\n"+ticket.getQuantite()  , 100, s);
         drawString(graphics,"\n"+ticket.getPrix() , 140, s);
         drawString(graphics,"\n"+ticket.getMontant()  , 170, s);
         s=s+15;
     if(ticket.getTicket()!=null) {
    	 for(Lignevente ll:ticket.getTicket().getVentes()) {
    	   	 if(ll.getProduit()!=null) {   	
    	   		 s=s+18;
    	   
    	   	   drawString(graphics,"\n"+ll.getProduit().getNom() , 20, s);
    	         drawString(graphics,"\n"+ll.getQuantite()  , 120, s);
    	         drawString(graphics,"\n"+ll.getProduit().getVente()  , 140, s);
    	         drawString(graphics,"\n"+ll.getTotalttcs()  , 170, s);
    	   		 
    	   	 }
     }
     }
     
    s=s+11;
    
    String t1="\nMontant "+df.format(ticket.getTotalttac());
	font2 = new Font("TimesRoman",Font.BOLD, 14);
    g2d.setFont(font2);
  	drawString(graphics,t1  , 20, s);	 
  	
  	
  	text="\n              Cachet et Signature            ";
  	 
  	font2 = new Font("TimesRoman",Font.BOLD, 13);
    g2d.setFont(font2);
    s=s+35;
    drawString(graphics,text  , 20, s);
    		
    text="\n              M    E    R    C    I            ";
	font2 = new Font("TimesRoman",Font.BOLD, 13);
    g2d.setFont(font2);
    s=s+40;
    drawString(graphics,text  , 20, s);
    //drawString(graphics,text  , 20, 20);
   // graphics.setFont(graphics.getFont().deriveFont(20f));
    //drawString(graphics, "part1\npart2", 80, 40);

    return Printable.PAGE_EXISTS;
   }

private void drawString(Graphics g, String text, int x, int y) {
    for (String line : text.split("\n"))
        g.drawString(line, x, y += g.getFontMetrics().getHeight());
}
 
   
}
