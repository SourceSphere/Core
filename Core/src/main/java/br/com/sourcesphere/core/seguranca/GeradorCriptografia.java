package br.com.sourcesphere.core.seguranca;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class GeradorCriptografia 
{
	public String gerarMD5(String valor) 
	{
		MessageDigest md;
		try 
		{
			md = MessageDigest.getInstance("MD5");
		} 
		catch (NoSuchAlgorithmException e) 
		{
			return "";
		}
		md.update(valor.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) 
		{
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	public String encriptar(String valor) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("UTF-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) 
        {
                keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        final byte[] plainTextBytes = valor.getBytes("UTF-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        return new String(cipherText, Charset.forName("UTF-8"));
    }

    public String decriptar(String valor) throws Exception 
    {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("UTF-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) 
        {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key, iv);
        final byte[] plainTextBytes = valor.getBytes(Charset.forName("UTF-8"));
        //final byte[] plainText = decipher.doFinal(plainTextBytes);
        return new String(plainTextBytes, Charset.forName("UTF-8"));
    }
	
}
