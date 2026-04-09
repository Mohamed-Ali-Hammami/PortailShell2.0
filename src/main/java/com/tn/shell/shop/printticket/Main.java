package com.tn.shell.shop.printticket;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.AttributedString;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.PrintService;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

 

public class Main {
	 
	public static void main(String[] args) {
		PrinterJob job = PrinterJob.getPrinterJob();
	      // Définit son contenu à imprimer
		 
	      job.setPrintable(new PrintRectangle());
	       
	      
	      // Affiche une boîte de choix d'imprimante
	     // if (job.printDialog()){
	         try {
	            // Effectue l'impression	        	 
	            job.print();
	         } catch (PrinterException ex) {
	            ex.printStackTrace();
	         }
	     // }
	}
}
