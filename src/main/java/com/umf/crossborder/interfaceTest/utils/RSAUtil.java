package com.umf.crossborder.interfaceTest.utils;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


public class RSAUtil {

	public static X509Certificate getUMPCerInJar() throws Exception{
		
		InputStream in = null; 
		try{
			in = RSAUtil.class.getClassLoader().getResourceAsStream("cer/cert_2d59.crt");
			
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			
			X509Certificate cert = (X509Certificate)cf.generateCertificate(in);
			
			return cert;
			
		}finally{
			in.close();
		}
	}
	
	public static X509Certificate getMERCerInJar() throws Exception{
		
		InputStream in = null; 
		
		try{
			in = RSAUtil.class.getClassLoader().getResourceAsStream("cer/testMer.cert.crt");
			
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			
			X509Certificate cert = (X509Certificate)cf.generateCertificate(in);
			
			return cert;
			
		}finally{
			in.close();
		}
	}
	
	public static String encryptByRsaPublic(String date,PublicKey publicKey) throws Exception{
		
		Cipher cipher = Cipher.getInstance("RSA");  
        
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        
        byte[] dateByte = date.getBytes("UTF-8");
        
        byte[] output = cipher.doFinal(dateByte);
        
        return Base64.encodeBase64String(output);  

	}
	
	
	public static PrivateKey getPrivateUMPKeyInJar() throws Exception{
		byte[] bs = new  byte[4096];
		InputStream in = null;
		try{
			in = RSAUtil.class.getClassLoader().getResourceAsStream("cer/testUmpay.key.p8");
			in.read(bs);
			PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(bs);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = kf.generatePrivate(peks);
			return privateKey;
		}finally{
			in.close();
		}
	}
	
	public static PrivateKey getPrivateMERKeyInJar() throws Exception{
		byte[] bs = new  byte[4096];
		InputStream in = null;
		try {
			in = RSAUtil.class.getClassLoader().getResourceAsStream("cer/3861_.key.p8");
			in.read(bs);
			PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(bs);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = kf.generatePrivate(peks);
			return privateKey;
		}finally{
			in.close();
		}
	}
	
	public static String decryptByRsaPrivate(String date,PrivateKey privateKey) throws Exception{
		Cipher cipher = null;
		byte[] encryptedData;
		byte[] result ;
		String resultStr = null;
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		encryptedData = Base64.decodeBase64(date.getBytes("UTF-8"));
		result = cipher.doFinal(encryptedData);
		resultStr = new String(result,"UTF-8");
		
		return resultStr;
	}
	
	
	
	public static String signForMER(String data, PrivateKey privateKey) throws Exception{
		byte[] dataByte = data.getBytes("UTF-8");
		Signature e = Signature.getInstance("SHA256withRSA");
		e.initSign(privateKey);
		e.update(dataByte);
		byte[] sb = e.sign();
		String base64SB = Base64.encodeBase64String(sb);
		System.out.println(base64SB);
		return base64SB;
	}
	
	
}
