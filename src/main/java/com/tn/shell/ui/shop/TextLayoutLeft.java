package com.tn.shell.ui.shop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextLayoutLeft extends JPanel {
Dimension d;
Font f=new Font("fontname",Font.PLAIN,20);
FontMetrics fm;
int fh,ascent;
int space;
public static void main(String[] a) {
	JFrame f=new JFrame();
	f.setSize(300,300);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.getContentPane().add(new TextLayoutLeft());
	f.setVisible(true);
	
}

public void paint(Graphics g) {
	d=getSize();
	g.setFont(f);
	if(fm==null) {
		fm=g.getFontMetrics();
		ascent=fm.getAscent();
		fh=ascent+fm.getDescent();
		space=fm.stringWidth(" ");		
	}
	g.setColor(Color.black);
	StringTokenizer st=new StringTokenizer("SHOP SHELL SIJOUMI");
	int x=0;
	int nextx=0;
	int y=0;
	String word,sp;
	int wordCount=0;
	String line="";
	while(st.hasMoreTokens()) {
		word=st.nextToken();
		if(word.equals("<BR")) {
			drawString(g,line,y+ascent);
			line="";
			wordCount=0;
			x=0;
			y=y+fh;
		}
		else {
			int w=fm.stringWidth(word);
			if((nextx=(x+space+w))>d.width) {
				drawString(g, line, y+ascent);
				line="";
				wordCount=0;
				x=0;
				y=y+fh;
			}
		 
		if(x!=0) {
			sp=" ";			
		}
		else {
			sp="";
		}
		line=line+sp+word;
		x=x+space+w;
		wordCount++;
	}}
	drawString(g, line, y+ascent);
}

public void drawString(Graphics g,String line,int y) {
	g.drawString(line, 0, y);
}
}
