package br.com.sourcesphere.core.seguranca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * Classe responsável por criptografar dados
 * @author Guilherme Dio
 * @since 1.0
 */
public class Criptografia 
{
	private static TipoAlgoritmo tipo;
	private static final TipoAlgoritmo tipoPadrao = TipoAlgoritmo.MD5;
	private static MessageDigest digest;
	
	private static Logger log = Logger.getLogger(Criptografia.class.getName());
	
	/**
	 * Inicializa o sistema de criptografia
	 * @param tipo - {@link TipoAlgoritmo} a ser usado(Ex: MD5, SHA, ...)
	 */
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
			log.warn("O algoritmo "+tipo.algoritmo()+" nï¿½o esta disponï¿½vel ou  nï¿½o existe...reiniciando com o algoritmo padrï¿½o "+tipoPadrao.algoritmo(), e);
			limpar();
			iniciar(tipoPadrao);
		}
	}
	
	/**
	 * Executa a limpeza do sistema de criptografia,
	 * portanto será necessário inicia-lo novamente
	 * utilizando o método {@link #iniciar(TipoAlgoritmo)}
	 */
	public static void limpar()
	{
		tipo = null;
		digest = null;
		log.info("O encriptador foi limpo");
	}
	
	/**
	 * Define o tipo de algoritmo a ser usado
	 * @param tipo - {@link TipoAlgoritmo} a ser usado(Ex: MD5, SHA, ...)
	 */
	private static void setTipo(TipoAlgoritmo tipo) 
	{
		if(tipo == null)
			throw new NullPointerException("O Tipo de Encriptaï¿½ï¿½o esta nulo");
		Criptografia.tipo = tipo;
		log.info("Estabelecido o algoritmo '"+tipo.algoritmo().toUpperCase()+"' para a criptografia");
	}

	/**
	 * Gera um valor criptografado a partir da String informada como parametro
	 * @param valor - Valor para ser criptografado
	 * @return Valor Criptografado
	 */
	public static String gerarHash(String valor)
	{
		log.info("Iniciando a encriptaï¿½ï¿½o do valor '"+valor+"' utilizando o algoritmo '"+tipo.algoritmo()+"'");
		if(digest == null)
		{
			String msg = "O Digest de criptografia nï¿½o foi iniciado. Utilize o mï¿½todo iniciar(TipoAlgoritmo)";
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