package br.com.sourcesphere.core.seguranca;

/**
 * ENUM com tipos de altoritmos para uso com a classe {@link Criptografia}
 * @author Guilherme Dio
 *
 */
public enum TipoAlgoritmo 
{	
	MD2("MD2"),
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");
	
	private String algoritmo;
	
	public String algoritmo()
	{
		return this.algoritmo;
	}
	
	private TipoAlgoritmo(String algoritmo) 
	{
		this.algoritmo = algoritmo;
	}
}
