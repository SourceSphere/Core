package br.com.sourcesphere.core.serializacao;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe que encapsula toda a serializa��o.
 * @author Guilherme Dio
 *
 */
public final class Serializador 
{
	private final Transformador transformador;
	private Class<?> classe;

	/**
	 * 
	 * @param transformador Transformador utilizado para {@link Transformador}
	 */
	public Serializador(Transformador transformador) 
	{
		this.transformador = transformador;
	}
	
	/**
	 * Serializa o arquivo
	 * @param arquivo Arquivo a ser serializado
	 * @return {@link Object}: Objeto serializado a partir do arquivo
	 */
	public Object serializa(File arquivo)
	{
		if(arquivo == null) throw new IllegalArgumentException("O arquivo informado esta nulo !");
		if(classe == null) throw new IllegalArgumentException("A classe para serializa��o n�o foi informada !");
		try
		{
			return this.transformador.transforma(this.classe, arquivo);
		}
		catch(IllegalAccessException e)
		{
			throw new SerializacaoException("O Arquivo est� com acesso negado !");
		}
		catch(InstantiationException e)
		{
			throw new SerializacaoException("A classe informada � inv�lida !");
		}
		catch(FileNotFoundException e)
		{
			throw new SerializacaoException("O arquivo n�o existe !");
		}
		catch(IllegalStateException e)
		{
			throw new SerializacaoException("O arquivo esta em formato inv�lido !");
		}
	}
	
	/**
	 * Deserializa o objeto para uma String utilizando o transformador parametrizado
	 * @param objeto - Instancia a ser deserializada
	 * @return Objeto deserializado
	 */
	public String deserializa(Object objeto)
	{
		if(objeto == null) throw new IllegalArgumentException("O objeto não pode ser nulo !");
		try
		{
			return this.transformador.retransforma(objeto);
		} 
		catch(InstantiationException e)
		{
			throw new SerializacaoException("A classe informada é inválida !");
		}
	}
	
	/**
	 * Set da classe a qual deverá ser utilizada na serialização
	 * @param classe
	 */
	public void setClasse(Class<?> classe)
	{
		if(classe == null) throw new IllegalArgumentException("A classe para serializa��o n�o foi informada !");
		this.classe = classe;
	}
	
	public Class<?> getClasse() 
	{
		return this.classe;
	}
	
}
