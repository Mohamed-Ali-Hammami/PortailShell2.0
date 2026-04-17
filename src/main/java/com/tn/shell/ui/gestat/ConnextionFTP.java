package com.tn.shell.ui.gestat;

 

/**
 *
 * @author user16
 */

 
import java.io.BufferedOutputStream;
 
 import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.tn.shell.model.gestat.TransactionCredit;
import com.tn.shell.model.gestat.TransactionDepense;
import com.tn.shell.service.gestat.ServiceJournal;
import com.tn.shell.service.gestat.ServiceJournalDep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnextionFTP {
	 
 
 private     List<TransactionCredit> listjournal; 
 private     List<TransactionDepense> listjournalDep; 
public boolean verifPompe(String pome,List<String>listpompes) {
	int i=0;
	
	while(i<=listpompes.size())
		if(listpompes.get(i).equals(pome))
	return true;
	return false;
}
	public     List<TransactionCredit> selectAlljournal(Date date,String t,String t2,List<String>listpompes,ServiceJournal s) throws ClassNotFoundException {//
		listjournal = new ArrayList<TransactionCredit>();
		try {
		
		String sql = "SELECT Time, Journaltext FROM JournalMaster  where Selection ='DISP HANDLER' and Time BETWEEN  '"+t+"' and '"+t2+"'";
                  String dates="";
                 // Date d = new Date();
  				// d.setMonth(d.getMonth()+1);
  				  SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
  				//if(date.getYear()>new Date().getYear())
  				//date.setYear(date.getYear()-1);
  				  dates = f.format(date);
                  Connection connection = null;
                  Statement statement = null;
                  ResultSet resultSet = null;
                  try {
                	  Class.forName("org.sqlite.JDBC");
                  connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/"+dates+".db3"); 
                  
                  statement = connection.createStatement();
                  resultSet = statement.executeQuery(sql);
    		 int i=1;
    			while (resultSet.next()) {
    				String[] requetes;
    				TransactionCredit j = new TransactionCredit();    
    				 j.setId2(i);
    				String journal = resultSet.getString("Journaltext");
    				j.setRequete(journal);
    				String time = resultSet.getString("Time");
    				requetes = journal.split(" ");    			 
    				DecimalFormat df = new DecimalFormat("0.000");
    				j.setTime(time);
    				j.setDates(f.format(new Date()));
    				j.setDate(new Date());
    			   
    				if(requetes.length>=3)
    				if (requetes[2].contains("PMP") && listpompes.contains(requetes[2]) ) {    					
    					 //-0 PAYABLE PMP:6 CMD:2 NZL:4 FUEL:3 VOL:17.57000 AMT:26.00400 ID:527 FLW:40 PRC:1.480000 PID:20 STRT:0227.000709
    					j.setPompe( requetes[2]);
    					j.setPistolet( requetes[4] );
    					j.setArticle(getArticle(Integer.parseInt(requetes[5].substring(5))));
    					j.setQuantite(Double.parseDouble(requetes[6].substring(4)));
    					j.setMontant(Double.parseDouble(requetes[7].substring(4)));
    					double p = Double.parseDouble(requetes[10].substring(4));
    					j.setPrix(df.format(p));
                        j.setPris(p);
                    if(s.getjournalbytipe(j.getTime(), dates)==null)
    					listjournal.add(j);
    					i=i+1;
    				}

    			 
    				
    			}
    			
                  }
                  catch(SQLException sqlex){
                  }
                  if(listjournal!=null) {
    			for (TransactionCredit e : listjournal) {
    			}
    			}
    		 
	} catch(Exception e) {
                 }

                return listjournal; 
	}

	public     List<TransactionDepense> selectAlljournalDepense(Date date,String t,String t2,List<String>listpompes,ServiceJournalDep s) throws ClassNotFoundException {//
		listjournalDep = new ArrayList<TransactionDepense>();
	//	try {
		
		String sql = "SELECT Time, Journaltext FROM JournalMaster  where Selection ='DISP HANDLER' and Time BETWEEN  '"+t+"' and '"+t2+"'";
                  String dates="";
                 // Date d = new Date();
  				// d.setMonth(d.getMonth()+1);
  				  SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
  				//if(date.getYear()>new Date().getYear())
  				//date.setYear(date.getYear()-1);
  				  dates = f.format(date);
                  Connection connection = null;
                  Statement statement = null;
                  ResultSet resultSet = null;
                  try {
                	  Class.forName("org.sqlite.JDBC");
                  connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/"+dates+".db3"); 
                  
                  statement = connection.createStatement();
                  resultSet = statement.executeQuery(sql);
    		 int i=1;
    			while (resultSet.next()) {
    				String[] requetes;
    				TransactionDepense j = new TransactionDepense();    
    				 j.setId2(i);
    				String journal = resultSet.getString("Journaltext");
    				j.setRequete(journal);
    				String time = resultSet.getString("Time");
    				requetes = journal.split(" ");    			 
    				DecimalFormat df = new DecimalFormat("0.000");
    				j.setTime(time);
    				j.setDates(f.format(new Date()));
    				j.setDate(new Date());
    				if (requetes[2].contains("PMP") && listpompes.contains(requetes[2]) ) {    					
    					 //-0 PAYABLE PMP:6 CMD:2 NZL:4 FUEL:3 VOL:17.57000 AMT:26.00400 ID:527 FLW:40 PRC:1.480000 PID:20 STRT:0227.000709
    					j.setPompe( requetes[2]);
    					j.setPistolet( requetes[4] );
    					j.setArticle(getArticle(Integer.parseInt(requetes[5].substring(5))));
    					j.setQuantite(Double.parseDouble(requetes[6].substring(4)));
    					j.setMontant(Double.parseDouble(requetes[7].substring(4)));
    					double p = Double.parseDouble(requetes[10].substring(4));
    					j.setPrix(df.format(p));
                        j.setPris(p);
                    if(s.getjournalbytipe(j.getTime(), dates)==null)
    					listjournalDep.add(j);
    					i=i+1;
    				}

    			 
    				
    			}
    			
                  }
                  catch(SQLException sqlex){
                  }
                  if(listjournalDep!=null) {
    			for (TransactionDepense e : listjournalDep) {
    			}
    			}
    		 
		/*} catch(Exception e) {
                 }
*/
                return listjournalDep; 
	}
	
	
	private     String getArticle(Integer num) {
		if (num == 2)
			return "SSP";
		else if (num == 3)
			return "GASOIL";
		else if (num == 4)
			return "SSP-V-POWER";
		else if (num == 9)
			return "PETROLE";
		else
			return "GASOIL 50";
	}

	 
	 
	 

	 
	public   void connexionFTP2(Date date) {
		String server = "10.83.196.3";
		int port = 21;
		String username = "supervisor";
		String password = "C70D6240D";
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			reponseServeur(ftpClient);
			int reponse = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reponse)) {
				return;
			}
			 boolean etat = ftpClient.login(username, password);
			reponseServeur(ftpClient);
			if (!etat) {
				return;

			} else { 

				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

				// lecture du fichier num pour obtenir le dernier fichier d index

				// mettre a jour le fichier num
				 
				// d.setMonth(d.getMonth()+1);
				SimpleDateFormat f = new SimpleDateFormat("YYYYMMdd");
			    //date.setYear(date.getYear()-1);
	  			String dates = f.format(date);
	  			 

				 
				String CheminFichierDistant2 = "/JOURNAL/" + dates + ".db3";
				File fichierlocal2 = new File("C://sqlite/db/" + dates + ".db3");
				FTPFile[] files2 = ftpClient.listFiles("/JOURNAL/");
				long size2 = 0;
				for (int i = 0; i < files2.length; i++) {
					if (files2[i].getName().equals(dates + ".db3"))
						// obtenir la taille du fichier
						size2 = files2[i].getSize();
				}

				OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(fichierlocal2));
				InputStream inputStream1 = ftpClient.retrieveFileStream(CheminFichierDistant2);
				byte[] bytesArray = new byte[4096];
				int bytesRead = -1;

				byte[] bytesArray2 = new byte[4096];
				int bytesRead2 = -1;

				// tant qu'on a pas atteint la fin

				int transfere = 0;
				int pourcentage2 = 0;

				while ((bytesRead2 = inputStream1.read(bytesArray2)) != -1) {
					// on ÃƒÂ©crit les octets dans l'emplacement prÃƒÂ©cisÃƒÂ©
					outputStream1.write(bytesArray2, 0, bytesRead2);

					transfere += bytesRead2;
					pourcentage2 = (int) (transfere * 100 / size2);

				} //

				// fermer les flux de lecture de d'ÃƒÂ©criture

				inputStream1.close();
				outputStream1.close();

			 }
		} catch (IOException ex) {
		} finally {
			try {
				if (ftpClient.isConnected()) {
					// fermer la connexion FTP
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
			}

		}
	}

	private void reponseServeur(FTPClient ftpClient) {
		String[] reponses = ftpClient.getReplyStrings();
		if (reponses != null && reponses.length > 0) {
			for (String reponse : reponses) {
			}
		}
	}

}
