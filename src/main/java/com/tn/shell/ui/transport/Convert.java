package com.tn.shell.ui.transport;

import java.text.*;

import com.tn.shell.model.transport.*;

 
public class Convert {
        private static final String [] dizaineNames = {
		 "",
		 "",
		 "vignt",
		 "trente",
		 "quarante",
		 "cinquante",
		 "soixante",
		 "soixante",		 
		 "quatre-vingt",
		 "quatre-vingt"
		 };
        
        
        public static void main(String[] args){
        	Facture facture =new Facture();facture.setTotalttc((float) 364.9735);
        	Convert c=new Convert();
        	String s="";    
			 double ss=   facture.getTotalttc(); 
				int e = new Float(ss).intValue();		 
				 
				//String tt =  ""+ facture.getTotalttc();					
				int cs=facture.getTotalttcs().indexOf(".");		
				s = facture.getTotalttcs().substring(cs+1,facture.getTotalttcs().length());
		 		/*if(tt.substring(cs+1,tt.length()).length()==3)
				  s = tt.substring(cs+1,tt.length());
				 else if(tt.substring(cs+1,tt.length()).length()==4)
					
					  s = tt.substring(cs+1,tt.length()-1);
				
				else if(tt.substring(cs+1,tt.length()).length()==5)
					
					  s = tt.substring(cs+1,tt.length()-2);
				
				else if(tt.substring(cs+1,tt.length()).length()>5)
					
					  s = tt.substring(cs+1,tt.length()-3);*/
				 facture.setSommes(c.convertt(e) + " dinars et  "
						+ s + " millimes");
				

        }
private static final String [] uniteNames1 = {
"",
"un",
"deux",
"trois",
"quatre",
"cinq",
"six",
"sept",
"huit",
"neuf",
"dix",
"onze",
"douze",
"treize",
"quatorze",
"quinze",
"seize",
"dix-sept",
"dix-huit",
"dix-neuf"};
private static final String [] uniteNames2 = {
"",
"",
"deux",
"trois",
"quatre",
"cinq",
"six",
"sept",
"huit",
"neuf",
"dix"
};
public Convert (){}
public String convertZeroToHundred(int number){
int laDizaine = number/10;
int lUnite = number%10;
String resultat = "";
switch (laDizaine){
case 1:
case 7:
case 9:
 lUnite= lUnite + 10;
 break;
 default:
 }
 String laLiaison= "";
  if(laDizaine>1){
   laLiaison ="-";
   }
   switch (lUnite){
   case 0:
   laLiaison="";
   break;
   case 1:
   if(laDizaine == 8 ){
   laLiaison="-";
   }
   else{
   laLiaison=" et ";
   }
   break;
   case 11:
   if (laDizaine==7){
   laLiaison= " et ";
   } break;
   default:
   }
   //dizaine en lettre
   switch (laDizaine){
   case 0:
   resultat= uniteNames1[lUnite];
   break;
   case 8 :
   if(lUnite==0){
	   resultat= dizaineNames[laDizaine];  
   }
   else{
	   
	   resultat= dizaineNames[laDizaine] + laLiaison + uniteNames1[lUnite];
   }
   break;
   default:
      resultat=dizaineNames[laDizaine]+ laLiaison+ uniteNames1[lUnite];
	  }
	  return resultat;
	  }
public String convertLessThanOneThousand(int number){
	 int lesCentaines = number / 100;
	 int leReste = number%100;
	 String sReste = convertZeroToHundred(leReste);
	 String resultat;
	 switch(lesCentaines){
	 case 0:
	 resultat=sReste; break;
	 case 1: 
	 if(leReste>0){
	 resultat = "cent "+ sReste;
	 }
	 else{
	 resultat= " cent";
	 }
	 break;
	 default:
	 if(leReste>0){
	 resultat = uniteNames2[lesCentaines] + " cent " + sReste;
	 }
	 else{
	 resultat = uniteNames2[lesCentaines] + " cents";
	 }
	 }
	 return resultat;
	 }
	 public   String convertt(long number){
	 
	 if( number == 0 ) { return "zÃ©ro";}
	 String snumber = Long.toString(number);
	 //pad des "0"
	 String mask ="000000000000";
	 DecimalFormat df = new DecimalFormat(mask);
	 snumber = df.format(number);
	 //XXXnnnnnnnnn
	 int lesMilliards = Integer.parseInt(snumber.substring(0,3));
	 //nnnXXXnnnnnn
	 int lesMillions = Integer.parseInt(snumber.substring(3,6));
	 //nnnnnnXXXnnn
	 int lesCentMillions = Integer.parseInt(snumber.substring(6,9));
	 //nnnnnnnnnXXX
	 int lesMille = Integer.parseInt(snumber.substring(9,12));
	 String tradMilliards;
	 switch(lesMilliards){
	 case 0:
	 tradMilliards="";
	 break;
	 case 1 :
	 tradMilliards= convertLessThanOneThousand(lesMilliards)+ " milliards ";
	 break;
	 default:
		 tradMilliards= convertLessThanOneThousand(lesMilliards)+ " milliards ";
	 }
	 String resultat = tradMilliards;
	 String traMillions ;
	 switch (lesMillions){
	 case 0:
	 traMillions= "";
	 break;
	 case 1: traMillions = convertLessThanOneThousand(lesMillions)
	 + " million ";
	 break;
	 default: traMillions= convertLessThanOneThousand(lesMillions)+" millions ";
	 }
	 resultat= resultat + traMillions;
	 String tradCenMille;
	 switch (lesCentMillions){
	 case 0:
		 tradCenMille= "";
	 break;
	 case 1: tradCenMille = convertLessThanOneThousand(lesCentMillions)
	 + " mille ";
	 break;
	 default:  tradCenMille = convertLessThanOneThousand(lesCentMillions)
			 + " mille ";
		 }
	 resultat= resultat +tradCenMille;
    String  tradMille= convertLessThanOneThousand(lesMille);
     resultat = resultat+tradMille;
	 return resultat;
	 }
	 /*public static void main(String [] args) {
		 float f=(float)10873.149;
		 
		  //
		 int e=new Float(f).intValue();
		 float d=f-(new Float(e).floatValue());
		 String s=""+d;
		 
									}*/
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
