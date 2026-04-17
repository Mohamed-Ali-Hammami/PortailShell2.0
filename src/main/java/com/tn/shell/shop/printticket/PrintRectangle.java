package com.tn.shell.shop.printticket;

import java.awt.Color;
import java.awt.Dimension;
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

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Date;

import javax.swing.JPanel;

public class PrintRectangle  implements Printable {
   
   /** Constructeur par défaut de PrintRectangle */
   public PrintRectangle() {
   }


   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
      // Par défaut, retourne NO_SUCH_PAGE => la page n'existe pas
      int retValue = Printable.NO_SUCH_PAGE;
      switch(pageIndex){
         case 0 : {
            // Dessin de la première page
            // Récupère la dimension de la zone imprimable
            int x  = (int) pageFormat.getImageableX();
            int y   =(int)  pageFormat.getImageableY();
            int w  =(int)  pageFormat.getImageableWidth();
            int h = (int) pageFormat.getImageableHeight();
             
         
           // Graphics2D g2d = (Graphics2D) graphics;
           // g2d.translate(pageFormat.getOrientation(), pageFormat.getImageableY());
           
            String text   = "SHOP SHELL SIJOUMI\n";
            String text2   = "Ticket N°  221543"+"\n   ";
            String text3   = "Date"+new Date()+"\n   ";
            FontMetrics  fm=graphics.getFontMetrics();
            
            int fh=fm.getAscent()+fm.getDescent();
           
             graphics.setFont(new Font("Arial",Font.BOLD ,14));               
             graphics.setColor(Color.BLACK);             
             int marge=fm.getAscent();                       
            // graphics.drawString(text,x,y+fm.getAscent()+fm.getDescent());   
             drawCenteredString("TICKET N° 220589", x, y, graphics);   
             retValue = Printable.PAGE_EXISTS;
             break;
         }
        
      }
      return retValue;
   }

   public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	  }
   
}
