package br.com.sourcesphere.core.arquivo;

/**
 * Exception de Runtime para handle de diretorios
 * @author Guilherme Dio
 *
 */
public class DiretorioException extends RuntimeException
{
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5541355387769945046L;

	public DiretorioException(String msg) 
	{
		super(msg);
	}
	
	public DiretorioException(String msg, Exception e)
	{
		super(msg,e);
	}
}
