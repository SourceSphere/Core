package br.com.sourcesphere.core.seguranca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
public class Criptografia 
{
	private static TipoAlgoritmo tipo;
	private static final TipoAlgoritmo tipoPadrao = TipoAlgoritmo.MD5;
	private static MessageDigest digest;
	
	private static Logger log = Logger.getLogger(Criptografia.class.getName());
	
	public static void iniciar(TipoAlgoritmo tipo)
	{
		log.info("Iniciando encriptador");
		setTipo(tipo);
		try
		{
			digest = MessageDigest.getInstance(tipo.algoritmo());
		}
		catch(NoSuchAlgorithmException e)
		{
			log.warn("O algoritmo "+tipo.algoritmo()+" n�o esta dispon�vel ou  n�o existe...reiniciando com o algoritmo padr�o "+tipoPadrao.algoritmo(), e);
			limpar();
			iniciar(tipoPadrao);
		}
	}
	
	public static void limpar()
	{
		tipo = null;
		digest = null;
		log.info("O encriptador foi limpo");
	}
	
	private static void setTipo(TipoAlgoritmo tipo) 
	{
		if(tipo == null)
			throw new NullPointerException("O Tipo de Encripta��o esta nulo");
		Criptografia.tipo = tipo;
		log.info("Estabelecido o algoritmo '"+tipo.algoritmo().toUpperCase()+"' para a criptografia");
	}

	public static String gerarHash(String valor)
	{
		log.info("Iniciando a encripta��o do valor '"+valor+"' utilizando o algoritmo '"+tipo.algoritmo()+"'");
		if(digest == null)
		{
			String msg = "O Digest de criptografia n�o foi iniciado. Utilize o m�todo iniciar(TipoAlgoritmo)";
			log.warn(msg);
			throw new IllegalStateException(msg);
		}
		digest.update(valor.getBytes());
		byte byteData[] = digest.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) 
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        String criptografado = sb.toString();
        log.info("O valor '"+valor+"' foi criptografado com sucesso para o seguinte hash: '"+criptografado+"'");
        return criptografado;
	}
}