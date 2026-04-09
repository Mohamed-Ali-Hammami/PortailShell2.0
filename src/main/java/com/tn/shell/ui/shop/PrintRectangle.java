package com.tn.shell.ui.shop;

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
import com.tn.shell.model.shop.Lignevente;
import com.tn.shell.model.shop.Ticket;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JPanel;

public class PrintRectangle extends JPanel  implements Printable {
   Ticket ticket;
   
   /** Constructeur par défaut de PrintRectangle */
   public PrintRectangle() {
	  
   }

     
   public PrintRectangle(Ticket ticket2) {
	   ticket =ticket2;
	   
	   
}


public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
      // Par défaut, retourne NO_SUCH_PAGE => la page n'existe pas
    /*  int retValue = Printable.NO_SUCH_PAGE;
      switch(pageIndex){
         case 0 : {
            // Dessin de la première page
            // Récupère la dimension de la zone imprimable
            float x  =  (float) pageFormat.getImageableX();
            float y   =(float)   pageFormat.getImageableY();
            float w  =(float)  pageFormat.getImageableWidth();
            float h = (float) pageFormat.getImageableHeight();
             
            
           Graphics2D g = (Graphics2D) graphics;
           
           
            String text   = "SHOP SHELL SIJOUMI\n";
            
            FontMetrics  fm=graphics.getFontMetrics();
            
            float fh=fm.getAscent()+fm.getDescent();
            fm=g.getFontMetrics();
    		float ascent=fm.getAscent();
    		fh=ascent+fm.getDescent();
    		int space=fm.stringWidth(" ");	
           Font fonte = new Font("TimesRoman ",Font.CENTER_BASELINE,10);
          System.out.println("x"+x); System.out.println("y"+y);
          g.setFont(fonte); 
         
         g.drawString(text,x,y+fh);
         y=y+fh;
         g.drawString("Ticket N° "+ticket.getId(),x,y+20); 
         y=y+fh;
         g.drawString("Date "+ticket.getDates()+"à"+ticket.getHeure(),x,y+20); 
         y=y+fh;
         g.drawString("Nom    Qte    Prix U   Montant",x,y+20); 
         y=y+fh;
         for(Lignevente l:ticket.getVentes()) {
        	 if(l.getProduit()!=null) {
        	 g.drawString(l.getProduit().getNom()+"      "+l.getQuantite()+"   "+l.getProduit().getVente()+"   "+l.getTotalttcs(),x,y+20); 
        	 y=y+fh;
        	 }
         }
         g.drawString("Total Ventes "+ticket.getTotal_ventes(),x,y+20); 
         y=y+fh;
         
         g.drawString("Total Recus "+ticket.getTotal_recus(),x,y+20); 
         y=y+fh;
         
         g.drawString("Total Rendu "+ticket.getTotal_rendus(),x,y+20); 
         y=y+fh;
         g.drawString("M E R C I",x,y+20); 
         y=y+fh;
         
         
            retValue = Printable.PAGE_EXISTS;
            break;
         }
        
      }
      return retValue;*/
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
    Font font = new Font("Arial",Font.PLAIN, 12);    
    g2d.setFont(font);
    String text="SHOP SHELL SIJOUMI";
           /* + "\nTicket N° "+ticket.getId()
            + "\nDate "+ticket.getDates()+"à"+ticket.getHeure()
            + "\nNom    Qte    Prix U   Montant";*/
    
    drawString(graphics,text  , 20, 20);
    Font font2 = new Font("Arial",Font.ITALIC, 11);    
    g2d.setFont(font2);
    text="\n  Ticket N° "+ticket.getId();
    drawString(graphics,text  , 20, 22);
     font2 = new Font("TimesRoman",Font.ROMAN_BASELINE, 11);
     g2d.setFont(font2);
     text="\n Date    "+ticket.getDates()+"     à"+ticket.getHeure();
     drawString(graphics,text  , 20, 35);
     font2 = new Font("TimesRoman",Font.ROMAN_BASELINE, 10);
     text="\nNom                         Qte  Prix-U   Montant";
     g2d.setFont(font2);
     drawString(graphics,text  , 20, 53);
     String t="";
     font2 = new Font("TimesRoman",Font.ROMAN_BASELINE, 9);
     g2d.setFont(font2);
     int s=53;
     for(Lignevente l:ticket.getVentes()) {
   	 if(l.getProduit()!=null) {   	
   		 s=s+22;
   	    t="\n"+l.getProduit().getNom()+"                        "+l.getQuantite()+"   "+l.getProduit().getVente()+"     "+l.getTotalttcs();
   		drawString(graphics,t  , 20, s);
   		 
   	 }
   	
   
    } 
      
    s=s+25;
   String t1="\nTotal Ventes "+ticket.getTotal_ventes();
	font2 = new Font("TimesRoman",Font.ROMAN_BASELINE, 13);
    g2d.setFont(font2);
   	drawString(graphics,t1  , 20, s);
    String t2="\nTotal Recus "+ticket.getTotal_recus();
    s=s+24;
	drawString(graphics,t2  , 20, s);
    String t3="\nTotal Rendu "+ticket.getTotal_rendus();
    s=s+22;
	drawString(graphics,t3  , 20, s);
    text="\n              M    E    R    C    I            ";
	font2 = new Font("TimesRoman",Font.BOLD, 13);
    g2d.setFont(font2);
    s=s+22;
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
