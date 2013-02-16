package br.com.sourcesphere.core.arquivo;

/**
 * Exception de Runtime para handle de arquivos
 * @author Guilherme Dio
 *
 */
public class ArquivoException extends RuntimeException
{
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -8882113574819766957L;

	public ArquivoException(String msg)
	{
		super(msg);
	}
	
	public ArquivoException(String msg, Exception e)
	{
		super(msg,e);
	}
}
