package br.com.sourcesphere.core.serializacao;

/**
 * RuntimeException para encapsular Exceptions causadas <br>
 * durante a serializacao de algum arquivo.
 * @author Guilherme Dio
 */
@SuppressWarnings("serial")
public final class SerializacaoException extends RuntimeException
{
	public SerializacaoException(String msg)
	{
		super(msg);
	}
}
