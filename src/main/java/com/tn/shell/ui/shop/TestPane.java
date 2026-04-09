package com.tn.shell.ui.shop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;

public class TestPane extends JPanel implements Printable {

    private String text1;

    private String text2;
    public TestPane() {
        text1 = "SHOP SHELL SIJOUMI\n";
        text2 = "Ticket N° 222285";
    }
    
  
     

     
    


 
	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
		
		 int retValue = Printable.NO_SUCH_PAGE;
	      switch(pageIndex){
	         case 0 : {
		 super.paintComponent(g);
	        //Graphics2D g2d = (Graphics2D) g.create();

	        g.setColor(Color.RED);
	      //  g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	       // g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

	        Font font = new Font("Arial", Font.BOLD,12);
	        g.setFont(font);
	        FontMetrics fm = g.getFontMetrics();
	        int x = ((getWidth() - fm.stringWidth("SHOP SHELL SIJOUMI")) / 2);
	        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

	        g.setColor(Color.BLACK);
	        g.drawString("SHOP SHELL SIJOUMI\n", x, y+50);
	      //  g.drawString(text2, x, y+50);
	        retValue = Printable.PAGE_EXISTS;
            break;
         }
	      }
	      return retValue;
	}
}

