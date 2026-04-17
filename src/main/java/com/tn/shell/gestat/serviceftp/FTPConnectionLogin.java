package com.tn.shell.gestat.serviceftp;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPConnectionLogin {

	public static void main(String[] args) {
		try {
			BufferedReader IN = new BufferedReader(new FileReader("D:/FTP_E00/num.txt"));
			String s = IN.readLine();
			BufferedReader IN2 = new BufferedReader(new FileReader("D:/FTP_E00/000" + s + ".P"));

			String ligne;
			String l2;
			String mot = "";
			String[] mots;
			while ((ligne = IN2.readLine()) != null) {
				if (ligne.equals("[START_TOTAL_CALCULATOR_TOTES]")) {
					while ((l2 = IN2.readLine()) != null && !l2.equals("[END_START_TOTAL_CALCULATOR_TOTES]")) {  
						mot = mot + l2 + ";";
					}
				}
			}
			mots = mot.split(";");
			for (int i = 0; i < mots.length; i++) {
				if (mots[i].contains("QUA")) {
					System.out.print(returnPompe(mots[i].substring(0, mots[i].indexOf("=")))+" ");
				}
			}

		} catch (IOException ex) {

		}
	}
public static String returnPompe(String s) {
	 
	if(s.equals("QUA1,1")) {
		return "SUPER A1";
	}
	if(s.equals("QUA1,2")) {
		return"GASOIL 50 A1";
	}
	
	if(s.equals("QUA1,3")) {
		return "GASOIL A1";
	}
	
	if(s.equals("QUA2,1")) {
		return"SUPER A2";
	}
	
	
	if(s.equals("QUA2,2")) {
		return"GASOIL 50 A2";
	}
	
	if(s.equals("QUA2,3")) {
		return"GASOIL A2";
	}
	
	if(s.equals("QUA3,1")) {
		return"SUPER B1";
	}
	
	
	if(s.equals("QUA3,2")) {
		return"GASOIL 50 B1";
	}
	
	if(s.equals("QUA2,3")) {
		return"GASOIL B1";
	}
	
	if(s.equals("QUA4,1")) {
		return"SUPER B2";
	}
	
	
	if(s.equals("QUA4,2")) {
		return"GASOIL 50 B2";
	}
	
	if(s.equals("QUA4,3")) {
		return"GASOIL B2";
	}
	
	if(s.equals("QUA5,1")) {
		return"SUPER 1C";
	}
	
	
	if(s.equals("QUA5,2")) {
		return"GASOIL 50 C1";
	}
	
	if(s.equals("QUA5,3")) {
		return"GASOIL 1C";
	}
	
	
	if(s.equals("QUA6,1")) {
		return"SUPER C2";
	}
	
	
	if(s.equals("QUA6,2")) {
		return"GASOIL 50 C2";
	}
	
	if(s.equals("QUA6,3")) {
		return"GASOIL C2";
	}
	
	if(s.equals("QUA7,2")) {
		return"PETROLE";
	}
	
	if(s.equals("QUA8,1")) {
		return"PETROLE2";
	}
	
	if(s.equals("QUA9,2")) {
		return"GASOIL V11";
	}
	
	if(s.equals("QUA10,1")) {
		return"GASOIL V12";
	}
	
	if(s.equals("QUA11,2")) {
		return"GASOIL 1D";
	}
	if(s.equals("QUA12,1")) {
		return"GASOIL 2D";
	}
	return "";
}
	public void connexionFTP() {
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
				
				//lecture du fichier num pour obtenir le dernier fichier d index
				BufferedReader IN = new BufferedReader(new FileReader("D:/FTP_E00/num.txt"));				
				String ss = IN.readLine();
				IN.close();
				
				//mettre a jour le fichier num
				String nomFichier2 = "000"+ss+".P";
				String CheminFichierDistant = "/EXPORT/FTP_E00/" + nomFichier2;
				File fichierlocal = new File("D:/FTP_E00/"+nomFichier2);
				FileWriter fichier2 = new FileWriter("D:/FTP_E00/num.txt");				
				Integer s = Integer.parseInt(nomFichier2.substring(3, nomFichier2.length() - 2)) + 1;
				fichier2.write(s);
				fichier2.close();
				
				FTPFile[] files1 = ftpClient.listFiles("/EXPORT/FTP_E00/");
				long size = 0;
				for (int i = 0; i < files1.length; i++) {
					if (files1[i].getName().equals(nomFichier2))
						// obtenir la taille du fichier
						size = files1[i].getSize();
				}

				OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(fichierlocal));

				InputStream inputStream2 = ftpClient.retrieveFileStream(CheminFichierDistant);

				byte[] bytesArray = new byte[4096];
				int bytesRead = -1;

				// tant qu'on a pas atteint la fin
				int transfer = 0;
				int pourcentage = 0;
				while ((bytesRead = inputStream2.read(bytesArray)) != -1) {
					// on Ã¯Â¿Â½crit les octets dans l'emplacement prÃ¯Â¿Â½cisÃ¯Â¿Â½
					outputStream2.write(bytesArray, 0, bytesRead);

					transfer += bytesRead;
					pourcentage = (int) (transfer * 100 / size);
				} // outputStream3.write();

				// fermer les flux de lecture de d'Ã¯Â¿Â½criture
				inputStream2.close();
				outputStream2.close();

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

	private static void reponseServeur(FTPClient ftpClient) {
		String[] reponses = ftpClient.getReplyStrings();
		if (reponses != null && reponses.length > 0) {
			for (String reponse : reponses) {
			}
		}
	}

}
