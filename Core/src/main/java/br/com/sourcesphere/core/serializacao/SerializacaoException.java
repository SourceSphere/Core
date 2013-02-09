package br.com.sourcesphere.core.serializacao;

/**
 * RuntimeException para encapsular Exceptions causadas <br>
 * durante a serialização de algum arquivo.
 * @author Guilherme Dio
 */
@SuppressWarnings("serial")
public class SerializacaoException extends RuntimeException
{
	public SerializacaoException(String msg)
	{
		super(msg);
	}
}
