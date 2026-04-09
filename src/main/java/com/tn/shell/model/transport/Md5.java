package com.tn.shell.model.transport;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
private String code;
public Md5(String md5){
	Passe(md5);
}
public void Passe(String pass){
	byte[] passBytes=pass.getBytes(); 
	try {
		MessageDigest algo=MessageDigest.getInstance("MD5");
		algo.reset();
		algo.update(passBytes);
	MessageDigest md=MessageDigest.getInstance("MD5");
	byte[] messaggeDiest=md.digest(passBytes);
	BigInteger number=new BigInteger(1,messaggeDiest);
	this.code=number.toString(16);
	} catch (NoSuchAlgorithmException e) {
		 
		throw new Error("invalid JRE: have not 'MD5' impl.",e);
	}
 
}

public String getCode(){
	return code;
}
}
